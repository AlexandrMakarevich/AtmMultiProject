package com.home.atm.command.parser_command;

import com.home.atm.command.Command;
import com.home.atm.command.ExitCommand;
import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("exitParser")
public class ExitParser implements InputParser {

    private Pattern exitPattern = Pattern.compile("^exit$");

    @Override
    public boolean commandMatch(String inputString) {
        Matcher exit = exitPattern.matcher(inputString);
        return exit.matches();
    }

    @Override
    public Command parseInput(String inputString) {
        Matcher exit = exitPattern.matcher(inputString);
        if (exit.find()) {
            return new ExitCommand();
        }
        throw new IllegalArgumentException("Wrong command : " + inputString);
    }
}
