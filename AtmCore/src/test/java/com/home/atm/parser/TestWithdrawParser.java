package com.home.atm.parser;

import com.home.atm.command.Command;
import com.home.atm.command.WithdrawCommand;
import com.home.atm.command.parser_command.InputParser;
import com.home.atm.command.parser_command.WithdrawParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.io.IOException;

public class TestWithdrawParser extends AbstractInputParserTest {

    private WithdrawParser withdrawParser;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Before
    public void init() throws IOException {
        withdrawParser = new WithdrawParser();
    }

    @Test
    public void testDbWithdrawCommand() {
        createCommand("- usd 10", new WithdrawCommand(namedParameterJdbcTemplate,"usd", 10));
    }

    public void createCommand(String inputCommand, Command expectedResult) {
        Command actualResult = withdrawParser.parseInput(inputCommand);
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }

    @Override
    public InputParser getParser() {
        return withdrawParser;
    }
}
