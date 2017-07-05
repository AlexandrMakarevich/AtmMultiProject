package com.home.atm.parser;

import com.home.atm.command.Command;
import com.home.atm.command.ExitCommand;
import com.home.atm.command.parser_command.ExitParser;
import com.home.atm.command.parser_command.InputParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

public class TestExitParser extends AbstractInputParserTest {

    private ExitParser exitParser;

    @Before
    public void init() throws IOException {
        exitParser = new ExitParser();
    }

    @Test
    public void testDbExitCommand() {
        createCommand("exit", new ExitCommand());
    }

    public void createCommand(String inputCommand, Command expectedResult) {
        Command actualResult = exitParser.parseInput(inputCommand);
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }
    @Override
    public InputParser getParser() {
        return exitParser;
    }
}
