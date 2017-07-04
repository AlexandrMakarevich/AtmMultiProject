package com.home.atm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.sql.SQLException;

public class AtmSpringLauncher {

    public static void main(String[] args) throws SQLException {

        String[] configs = {"/atm_terminal.xml"};
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configs);
        ServiceAccount serviceAccount = (ServiceAccount) applicationContext.getBean("serviceAccount");
        serviceAccount.launchAccount();
    }
}