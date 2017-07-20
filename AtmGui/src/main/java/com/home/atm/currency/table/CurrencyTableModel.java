package com.home.atm.currency.table;

import com.home.atm.currency.Currency;
import org.springframework.stereotype.Component;
import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.List;
import static com.home.atm.currency.table.CurrencyTableModel.CURRENCY_TABLE_MODEL;

@Component(CURRENCY_TABLE_MODEL)
public class CurrencyTableModel extends AbstractTableModel {

    private List<Currency> currencyList;
    private List<String> columnsName = Arrays.asList(new String[] {"id", "Currency name"});
    public static final String CURRENCY_TABLE_MODEL = "currencyTableModel";

    @Override
    public int getRowCount() {
        return currencyList.size();
    }

    @Override
    public int getColumnCount() {
        return columnsName.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Currency currency = currencyList.get(rowIndex);
        if (columnIndex == 0) {
            return currency.getId();
        }
        if(columnIndex == 1) {
            return currency.getName();
        }
        throw new IllegalArgumentException("Wrong column index" + columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return columnsName.get(0);
        }
        if (column == 1) {
            return columnsName.get(1);
        }
        throw new IllegalArgumentException("Wrong column index" + column);
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }
}
