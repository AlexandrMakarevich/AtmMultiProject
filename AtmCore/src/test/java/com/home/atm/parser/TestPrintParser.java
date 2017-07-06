package com.home.atm.parser;

import com.home.atm.command.Command;
import com.home.atm.command.PrintBalanceCommand;
import com.home.atm.command.parser_command.InputParser;
import com.home.atm.command.parser_command.PrintParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.io.IOException;

public class TestPrintParser extends AbstractInputParserTest {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private PrintParser printParser;

    @Before
    public void init() throws IOException {
        printParser = new PrintParser();
    }

    @Test
    public void testDbExitCommand() {
        createCommand("balance", new PrintBalanceCommand(namedParameterJdbcTemplate));
    }

    public void createCommand(String inputCommand, Command expectedResult) {
        Command actualResult = printParser.parseInput(inputCommand);
        Assert.assertEquals("Actual result must be expected", PrintBalanceCommand.class, actualResult.getClass());
    }

    @Override
    public InputParser getParser() {
        return printParser;
    }

}
