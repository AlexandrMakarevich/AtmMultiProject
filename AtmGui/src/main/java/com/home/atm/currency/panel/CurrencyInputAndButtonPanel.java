package com.home.atm.currency.panel;

import com.home.atm.currency.listener.AddCurrencyButtonListener;
import com.home.atm.currency.listener.DeleteCurrencyButtonListener;
import org.springframework.context.ApplicationContext;
import javax.swing.*;
import java.awt.*;

public class CurrencyInputAndButtonPanel extends JPanel {

    private JButton currencyButton = new JButton("Add currency");
    private JButton deleteButton = new JButton("Delete");
    private JLabel currencyLabel = new JLabel("Enter currency name");
    private FlowLayout currencyLayout = new FlowLayout();
    private JTextField input = new JTextField("", 5);
    private AddCurrencyButtonListener addCurrencyButtonListener;
    private DeleteCurrencyButtonListener deleteCurrencyButtonListener;

    public CurrencyInputAndButtonPanel(ApplicationContext applicationContext) {
        addComponentsOnPanel();
        addCurrencyButtonListener = (AddCurrencyButtonListener)
                applicationContext.getBean("addCurrencyButtonListener");
        deleteCurrencyButtonListener = (DeleteCurrencyButtonListener)
                applicationContext.getBean("deleteCurrencyButtonListener");
     initAndAddListener();
    }

    public void addComponentsOnPanel() {
        setLayout(currencyLayout);
        add(currencyLabel);
        add(input);
        add(currencyButton);
        add(deleteButton);
    }

    public void initAndAddListener() {
        addCurrencyButtonListener.setInput(input);
        currencyButton.addActionListener(addCurrencyButtonListener);
        deleteButton.addActionListener(deleteCurrencyButtonListener);
    }

    public AddCurrencyButtonListener getAddCurrencyButtonListener() {
        return addCurrencyButtonListener;
    }

    public DeleteCurrencyButtonListener getDeleteCurrencyButtonListener() {
        return deleteCurrencyButtonListener;
    }
}
