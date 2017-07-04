package com.home.atm.command;

import org.apache.log4j.Logger;
import java.util.Collections;
import java.util.List;

public class ExitCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ExitCommand.class);

    @Override
    public List<PrintBalance> executeDb(int accountName) {
        LOGGER.info("Get command exit.");
        return Collections.emptyList();
    }

    @Override
    public CommandName getCommandOperation() {
        return CommandName.EXIT;
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
