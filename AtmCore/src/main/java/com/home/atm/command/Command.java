package com.home.atm.command;

import java.sql.SQLException;

public interface Command {

    void executeDb(int accountName) throws SQLException;
}
