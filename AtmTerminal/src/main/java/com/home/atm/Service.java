package com.home.atm;

import com.home.atm.command.Command;
import com.home.atm.command.PrintBalance;
import com.home.atm.command.parser_command.DelegatedInputParser;
import com.home.atm.output.OutputService;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@org.springframework.stereotype.Service("service")
@Transactional
public class Service {

    @Resource(name = "delegatedInputParser")
    public void setInputParser(DelegatedInputParser inputParser) {
        this.inputParser = inputParser;
    }

    private DelegatedInputParser inputParser;
    private OutputService outputService;

    @Resource(name = "outputService")
    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }

    public void procesInput(int accountId) throws SQLException {
        System.out.println("Enter operation :");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        Command command = inputParser.defaultParseInput(input);
        if (command == null) {
            System.out.println("You enter wrong command!\nTry again :");
        } else {
            List<PrintBalance> list = command.executeDb(accountId);
            outputService.processCommand(list, command.getCommandOperation());
        }
    }
}
