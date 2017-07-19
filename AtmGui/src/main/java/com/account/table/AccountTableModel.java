package com.account.table;

import com.home.atm.account.Account;
import org.springframework.stereotype.Component;
import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.List;
import static com.account.table.AccountTableModel.ACCOUNT_TABLE_MODEL_BEAN_NAME;

@Component(ACCOUNT_TABLE_MODEL_BEAN_NAME)
public class AccountTableModel extends AbstractTableModel {

    private List<String> columnName = Arrays.asList(new String[]{"id", "Account name"});
    private List<Account> accountList;
    public static final String ACCOUNT_TABLE_MODEL_BEAN_NAME = "accountTableModel";

    @Override
    public int getRowCount() {
        return accountList.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Account account = accountList.get(rowIndex);
        if (columnIndex == 0) {
           return account.getAccountId();
        }
        if (columnIndex == 1) {
           return account.getAccountName();
        }
        throw new IllegalArgumentException("Wrong column index" + columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0){
            return columnName.get(0);
        }
        if (column == 1) {
            return  columnName.get(1);
        }
        throw new IllegalArgumentException("Wrong column index" + column);
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
