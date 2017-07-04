package com.home.atm.command;

import java.sql.SQLException;
import java.util.List;

public interface Command {

    List<PrintBalance> executeDb(int accountName) throws SQLException;

    CommandName getCommandOperation();
}
