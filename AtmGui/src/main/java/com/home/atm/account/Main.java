package com.home.atm.account;

import com.home.atm.account.panel.AccountPanel;
import com.home.atm.currency.panel.CurrencyPanel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] {"/atmGui.xml"});
        CurrencyPanel currencyPanel = new CurrencyPanel(applicationContext);
        AccountPanel accountPanel = new AccountPanel(applicationContext);
        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab("Account", accountPanel);
        jTabbedPane.addTab("Currency", currencyPanel);
        JFrame jFrame = new JFrame();
        jFrame.setSize(150, 200);
        jFrame.setContentPane(jTabbedPane);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
