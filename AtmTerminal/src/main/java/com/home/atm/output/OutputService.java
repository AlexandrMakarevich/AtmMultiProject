package com.home.atm.output;

import com.home.atm.command.CommandName;
import com.home.atm.command.PrintBalance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("outputService")
public class OutputService {

    private Map<CommandName, OutputResult> outputResultMap;

    @Resource(name = "mapResultCommand")
    public void setOutputResultMap(Map<CommandName, OutputResult> outputResultMap) {
        this.outputResultMap = outputResultMap;
    }

    public void processCommand(List<PrintBalance> printBalances, CommandName commandName) {
        OutputResult outputResult = outputResultMap.get(commandName);
        if (outputResult == null) {
            throw new IllegalArgumentException("The handler for this command was not found!");
        }
        outputResult.printResult(printBalances);
    }
}
