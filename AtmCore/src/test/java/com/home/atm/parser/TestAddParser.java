package com.home.atm.parser;

import com.home.atm.command.AddCommand;
import com.home.atm.command.Command;
import com.home.atm.command.parser_command.AddParser;
import com.home.atm.command.parser_command.InputParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.io.IOException;

public class TestAddParser extends AbstractInputParserTest {

    private AddParser addParser;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Before
    public void init() throws IOException {
        addParser = new AddParser();
    }

    @Test
    public void testDbAddRub10() {
        createCommand("+ rub 10", new AddCommand(namedParameterJdbcTemplate,"rub", 10));
    }

    public void createCommand(String inputCommand, Command expectedResult) {
        Command actualResult = addParser.parseInput(inputCommand);
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }

    @Override
    public InputParser getParser() {
        return addParser;
    }
}
