package com.home.atm.account.panel;

import com.home.atm.account.table.AccountTable;
import com.home.atm.account.table.AccountTableModel;
import org.springframework.context.ApplicationContext;
import javax.swing.*;
import java.awt.*;
import static com.home.atm.account.table.AccountTable.ACCOUNT_TABLE_BEAN_NAME;
import static com.home.atm.account.table.AccountTableModel.ACCOUNT_TABLE_MODEL_BEAN_NAME;

public class AccountPanel extends JPanel {

    private BorderLayout borderLayout = new BorderLayout();
    private AccountTable accountTable;
    private AccountTableModel accountTableModel;
    private InputAndButtonPanel inputAndButtonPanel;

    public AccountPanel(ApplicationContext applicationContext) {
        setLayout(borderLayout);
        accountTable = (AccountTable) applicationContext.getBean(ACCOUNT_TABLE_BEAN_NAME);
        accountTableModel = (AccountTableModel) applicationContext.getBean(ACCOUNT_TABLE_MODEL_BEAN_NAME);
        accountTable.setModel(accountTableModel);
        accountTable.refreshModel();
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(accountTable);
        add(jScrollPane, BorderLayout.CENTER);
        inputAndButtonPanel = new InputAndButtonPanel(applicationContext);
        inputAndButtonPanel.getAddAccountButtonListener().setAccountTable(accountTable);
        inputAndButtonPanel.getDeleteAccountButtonListener().setAccountTable(accountTable);
        add(inputAndButtonPanel,BorderLayout.PAGE_START);
    }
}
