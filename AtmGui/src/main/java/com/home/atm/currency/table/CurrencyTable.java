package com.home.atm.currency.table;

import com.home.atm.currency.CurrencyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import static com.home.atm.currency.table.CurrencyTable.CURRENCY_TABLE;

@Component(CURRENCY_TABLE)
public class CurrencyTable extends JTable {

    private CurrencyTableModel currencyTableModel;
    private CurrencyDao currencyDao;
    public static final String CURRENCY_TABLE = "currencyTable";

    public void refreshModel() {
        currencyTableModel.setCurrencyList(currencyDao.getAllCurrency());
        currencyTableModel.fireTableDataChanged();
    }

    @Autowired
    public void setCurrencyTableModel(CurrencyTableModel currencyTableModel) {
        this.currencyTableModel = currencyTableModel;
    }

    @Autowired
    public void setCurrencyDao(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }
}
