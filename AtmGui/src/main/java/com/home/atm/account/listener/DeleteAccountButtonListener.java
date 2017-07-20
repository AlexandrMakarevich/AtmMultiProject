package com.home.atm.account.listener;

import com.home.atm.account.table.AccountTable;
import com.home.atm.account.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static com.home.atm.account.listener.DeleteAccountButtonListener.DELETE_ACCOUNT_BUTTON_LISTENER_BEAN;

@Component(DELETE_ACCOUNT_BUTTON_LISTENER_BEAN)
public class DeleteAccountButtonListener implements ActionListener {

    private AccountTable accountTable;
    private AccountDao accountDao;
    public static final String DELETE_ACCOUNT_BUTTON_LISTENER_BEAN = "deleteAccountButtonListener";

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int accountId = validateAndCreate();
            accountDao.deleteAccount(accountId);
            accountTable.refreshModel();
        } catch (ArrayIndexOutOfBoundsException e1) {
            JOptionPane.showMessageDialog(null, "You did not choose what to delete!");
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
    }

    public int validateAndCreate() {
        if (accountTable.getValueAt(accountTable.getSelectedRow(), 0) == null) {
            throw new IllegalArgumentException("Column is empty!");
        }
        return (Integer) accountTable.getValueAt(accountTable.getSelectedRow(), 0);
    }

    public void setAccountTable(AccountTable accountTable) {
        this.accountTable = accountTable;
    }

    @Autowired
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
