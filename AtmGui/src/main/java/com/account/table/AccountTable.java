package com.account.table;

import com.home.atm.account.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import static com.account.table.AccountTable.ACCOUNT_TABLE_BEAN_NAME;

@Component(ACCOUNT_TABLE_BEAN_NAME)
public class AccountTable extends JTable {

    private AccountTableModel accountTableModel;
    private AccountDao accountDao;
    public static final String ACCOUNT_TABLE_BEAN_NAME = "accountTable";

    public void refreshModel() {
        accountTableModel.setAccountList(accountDao.getAllAccounts());
        accountTableModel.fireTableDataChanged();
    }

    @Autowired
    public void setAccountTableModel(AccountTableModel accountTableModel) {
        this.accountTableModel = accountTableModel;
    }

    @Autowired
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
