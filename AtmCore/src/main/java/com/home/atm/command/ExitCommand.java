package com.home.atm.command;

import org.apache.log4j.Logger;
import java.sql.SQLException;

public class ExitCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ExitCommand.class);

    @Override
    public void executeDb(int accountName) throws SQLException {
        System.out.println("Get command close.");
        LOGGER.info("Get command close.");
        System.exit(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

}
