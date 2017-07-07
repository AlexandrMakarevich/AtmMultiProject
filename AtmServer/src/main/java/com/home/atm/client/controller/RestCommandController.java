package com.home.atm.client.controller;

import com.home.atm.client.CommandBean;
import com.home.atm.command.Command;
import com.home.atm.command.PrintBalance;
import com.home.atm.command.parser_command.DelegatedInputParser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@RestController("restCommandController")
@RequestMapping("/restCommand")
public class RestCommandController {

    private DelegatedInputParser delegatedInputParser;

    @RequestMapping(method = RequestMethod.POST)
    public List<PrintBalance> processCommand(@RequestBody CommandBean commandBean) {
        Command command = delegatedInputParser.defaultParseInput(commandBean.getCommandName());
        return command.executeDb(commandBean.getAccountId());
    }

    @Resource(name = "delegatedInputParser")
    public void setDelegatedInputParser(DelegatedInputParser delegatedInputParser) {
        this.delegatedInputParser = delegatedInputParser;
    }
}
