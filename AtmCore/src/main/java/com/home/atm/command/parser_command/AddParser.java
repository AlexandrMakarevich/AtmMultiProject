package com.home.atm.command.parser_command;


import com.home.atm.command.AddCommand;
import com.home.atm.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("addParser")
public class AddParser implements InputParser {

    private Pattern adвPattern = Pattern.compile("^\\+ ([a-z]{3}) ([0-9]{1,10})$");
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public boolean commandMatch(String inputString) {
        Matcher add = adвPattern.matcher(inputString);
        return add.matches();
    }

    @Override
    public Command parseInput(String inputString) {
        Matcher add = adвPattern.matcher(inputString);
        if(add.matches()) {
            String currency = add.group(1);
            Integer amount = Integer.parseInt(add.group(2));
            return new AddCommand(namedParameterJdbcTemplate, currency, amount);
        }
        throw new IllegalArgumentException("Wrong command : " + inputString );
    }
}
