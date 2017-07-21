package com.home.atm.currency.listener;

import com.home.atm.currency.CurrencyDao;
import com.home.atm.currency.table.CurrencyTable;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component("deleteCurrencyButtonListener")
public class DeleteCurrencyButtonListener implements ActionListener {

    private CurrencyDao currencyDao;
    private CurrencyTable currencyTable;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int currencyId = validateAndCreate
                    ((Integer)currencyTable.getValueAt(currencyTable.getSelectedRow(), 0));
            currencyDao.deleteCurrency(currencyId);
            currencyTable.refreshModel();
        } catch (ArrayIndexOutOfBoundsException e1) {
            JOptionPane.showMessageDialog(null, "You did not choose what to delete!");
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
    }

    public Integer validateAndCreate(Integer currencyId) {
        if (currencyId == null) {
            throw new IllegalArgumentException("Column is empty!");
        }
        return currencyId;
    }

    @Resource(name = "restCurrencyDaoImpl")
    public void setCurrencyDao(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }

    public void setCurrencyTable(CurrencyTable currencyTable) {
        this.currencyTable = currencyTable;
    }
}
