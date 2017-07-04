package com.home.atm.command;

import java.util.List;

public interface Command {

    List<PrintBalance> executeDb(int accountName);

    CommandName getCommandOperation();
}
