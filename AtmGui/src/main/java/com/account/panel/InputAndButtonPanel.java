package com.account.panel;

import com.account.listener.AddAccountButtonListener;
import com.account.listener.DeleteAccountButtonListener;
import org.springframework.context.ApplicationContext;
import javax.swing.*;
import java.awt.*;
import static com.account.listener.AddAccountButtonListener.ADD_ACCOUNT_BUTTON_LISTENER_BEAN_NAME;
import static com.account.listener.DeleteAccountButtonListener.DELETE_ACCOUNT_BUTTON_LISTENER_BEAN;

public class InputAndButtonPanel extends JPanel {

    private FlowLayout accountLayout = new FlowLayout();
    private JLabel accountLabel = new JLabel("Enter account name");
    private JTextField accountInput = new JTextField("", 5);
    private JButton addAccountButton = new JButton("Add account");
    private JButton deleteAccountButton = new JButton("Delete");
    private AddAccountButtonListener addAccountButtonListener;
    private DeleteAccountButtonListener deleteAccountButtonListener;

    public InputAndButtonPanel(ApplicationContext applicationContext) {
        addComponentsOnPanel();
        addAccountButtonListener = (AddAccountButtonListener)
                applicationContext.getBean(ADD_ACCOUNT_BUTTON_LISTENER_BEAN_NAME);
        deleteAccountButtonListener =
                (DeleteAccountButtonListener) applicationContext.getBean(DELETE_ACCOUNT_BUTTON_LISTENER_BEAN);
        initAndAddListener();
    }

    public void addComponentsOnPanel() {
        setLayout(accountLayout);
        add(accountLabel);
        add(accountInput);
        add(addAccountButton);
        add(deleteAccountButton);
    }

    public void initAndAddListener() {
        addAccountButtonListener.setInput(accountInput);
        addAccountButton.addActionListener(addAccountButtonListener);
        deleteAccountButton.addActionListener(deleteAccountButtonListener);
    }

    public AddAccountButtonListener getAddAccountButtonListener() {
        return addAccountButtonListener;
    }

    public DeleteAccountButtonListener getDeleteAccountButtonListener() {
        return deleteAccountButtonListener;
    }
}
