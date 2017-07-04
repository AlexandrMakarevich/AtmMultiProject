package com.home.atm.command.parser_command;

import com.home.atm.command.Command;
import com.home.atm.command.PrintBalanceCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("printParser")
public class PrintParser implements InputParser {

    private Pattern dbPrintPattern = Pattern.compile("^balance$");
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public boolean commandMatch(String inputString) {
        Matcher print = dbPrintPattern.matcher(inputString);
        return print.matches();
    }

    @Override
    public Command parseInput(String inputString) {
        Matcher print = dbPrintPattern.matcher(inputString);
        if (print.find()) {
            return new PrintBalanceCommand(namedParameterJdbcTemplate);
        }
        throw new IllegalArgumentException("Wrong command : " + inputString);
    }
}
