package com.home.atm.currency.listener;


import com.home.atm.currency.CurrencyDao;
import com.home.atm.currency.table.CurrencyTable;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component("addCurrencyButtonListener")
public class AddCurrencyButtonListener implements ActionListener {

    private JTextField input;
    private CurrencyTable currencyTable;
    private CurrencyDao currencyDao;

    @Override
    public void actionPerformed(ActionEvent e) {
       try{
           currencyDao.addCurrency(validateAndGet(input.getText()));
           currencyTable.refreshModel();
       }catch(IllegalArgumentException e1) {
           JOptionPane.showMessageDialog(null, e1.getMessage());
       }
       catch (Exception e1) {
           String formattedString = String.format("Currency %s can not be created.May by it exist", input.getText());
           JOptionPane.showMessageDialog(null, formattedString);
       }
    }

    public String validateAndGet(String input) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("You don't enter account name!");
        }
        return input;
    }

    @Resource(name = "restCurrencyDaoImpl")
    public void setCurrencyDao(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }

    public void setCurrencyTable(CurrencyTable currencyTable) {
        this.currencyTable = currencyTable;
    }

    public void setInput(JTextField input) {
        this.input = input;
    }
}
