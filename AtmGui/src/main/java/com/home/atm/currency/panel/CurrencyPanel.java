package com.home.atm.currency.panel;

import com.home.atm.currency.table.CurrencyTable;
import com.home.atm.currency.table.CurrencyTableModel;
import org.springframework.context.ApplicationContext;
import javax.swing.*;
import java.awt.*;
import static com.home.atm.currency.table.CurrencyTable.CURRENCY_TABLE;
import static com.home.atm.currency.table.CurrencyTableModel.CURRENCY_TABLE_MODEL;

public class CurrencyPanel extends JPanel {

    private BorderLayout borderLayout = new BorderLayout();
    private CurrencyInputAndButtonPanel currencyInputAndButtonPanel;
    private CurrencyTable currencyTable;
    private CurrencyTableModel currencyTableModel;

    public CurrencyPanel (ApplicationContext applicationContext) {
        setLayout(borderLayout);
        currencyTable = (CurrencyTable) applicationContext.getBean(CURRENCY_TABLE);
        currencyTableModel = (CurrencyTableModel) applicationContext.getBean(CURRENCY_TABLE_MODEL);
        currencyTable.setModel(currencyTableModel);
        currencyTable.refreshModel();
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(currencyTable);
        add(jScrollPane, BorderLayout.CENTER);
        currencyInputAndButtonPanel = new CurrencyInputAndButtonPanel(applicationContext);
        currencyInputAndButtonPanel.getAddCurrencyButtonListener().setCurrencyTable(currencyTable);
        currencyInputAndButtonPanel.getDeleteCurrencyButtonListener().setCurrencyTable(currencyTable);
        add(currencyInputAndButtonPanel, BorderLayout.PAGE_START);
    }
}
