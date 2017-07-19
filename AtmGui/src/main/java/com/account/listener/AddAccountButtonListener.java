package com.account.listener;

import com.account.table.AccountTable;
import com.home.atm.account.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static com.account.listener.AddAccountButtonListener.ADD_ACCOUNT_BUTTON_LISTENER_BEAN_NAME;

@Component(ADD_ACCOUNT_BUTTON_LISTENER_BEAN_NAME)
public class AddAccountButtonListener implements ActionListener {

    private JTextField input;
    private AccountTable accountTable;
    private AccountDao accountDao;
    public static final String ADD_ACCOUNT_BUTTON_LISTENER_BEAN_NAME = "addAccountButtonListener";

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            accountDao.createAccount(validateAndGet());
            accountTable.refreshModel();

        } catch(IllegalArgumentException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
        catch (Exception e1) {
            String formattedString = String.format("Account %s can not be created.May by it exist", input.getText());
            JOptionPane.showMessageDialog(null, formattedString);
        }
    }

    public String validateAndGet() {
        if (input.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("You don't enter account name!");
        }
        return input.getText();
    }

    public void setInput(JTextField input) {
        this.input = input;
    }

    public void setAccountTable(AccountTable accountTable) {
        this.accountTable = accountTable;
    }

    @Autowired
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
