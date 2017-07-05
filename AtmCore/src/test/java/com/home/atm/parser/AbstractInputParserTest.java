package com.home.atm.parser;

import com.home.atm.command.parser_command.InputParser;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public abstract class AbstractInputParserTest {

    @Rule
    public ExpectedException testRuleException = ExpectedException.none();

    @Test
    public void testWrongCommand() {
        String input = "+usd100";
        this.testRuleException.expect(IllegalArgumentException.class);
        this.testRuleException.expectMessage("Wrong command : " + input);
        getParser().parseInput(input);
    }

    public abstract InputParser getParser();
}
