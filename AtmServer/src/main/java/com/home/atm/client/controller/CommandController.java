package com.home.atm.client.controller;

import com.home.atm.client.CommandBean;
import com.home.atm.client.validator.CommandValidator;
import com.home.atm.command.Command;
import com.home.atm.command.CommandName;
import com.home.atm.command.PrintBalance;
import com.home.atm.command.parser_command.DelegatedInputParser;
import com.home.atm.exception.AtmException;
import com.home.atm.exception.ErrorCodes;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import static com.home.atm.client.controller.AccountController.ACCOUNT_ID_ATTRIBUTE_NAME;
import static com.home.atm.client.controller.AccountController.ACCOUNT_NAME_MODEL_ATTRIBUTE;

@Controller("commandController")
@RequestMapping("/command")
@Transactional
public class CommandController {

    @Resource(name = "commandValidator")
    private CommandValidator commandValidator;
    private DelegatedInputParser delegatedInputParser;
    private static final String COMMAND_PAGE_NAME = "command";
    private static final String COMMAND_ATTRIBUTE_NAME = "commandBean";
    private Map<CommandName, String> commandPages;

    @InitBinder(COMMAND_ATTRIBUTE_NAME)
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(commandValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showCommand(ModelMap model) {
        CommandBean commandBean = new CommandBean();
        commandBean.setAccountId((Integer) model.get(ACCOUNT_ID_ATTRIBUTE_NAME));
        commandBean.setAccountName((String) model.get(ACCOUNT_NAME_MODEL_ATTRIBUTE));
        model.addAttribute(COMMAND_ATTRIBUTE_NAME, commandBean);
        model.addAttribute("commandNameEnum", "");
        return COMMAND_PAGE_NAME;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processCommand(@Valid CommandBean commandBean1, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return COMMAND_PAGE_NAME;
        }
        model.addAttribute("errorCode", "");
        Command command = delegatedInputParser.defaultParseInput(commandBean1.getCommandName());
        try {
            List<PrintBalance> resultList = command.executeDb(commandBean1.getAccountId());
            model.addAttribute("operationResult", resultList);
        } catch (AtmException errors) {
            model.addAttribute("errorCode", errors.getErrorCodes());
        }
        if (commandPages.get(command.getCommandOperation()) == null) {
            throw new AtmException(ErrorCodes.ERROR_CONFIGURATIONS);
        }
        model.addAttribute("commandNameEnum", command.getCommandOperation());
        return commandPages.get(command.getCommandOperation());
    }

    @ExceptionHandler(AtmException.class)
    public ModelAndView handlerCustomException(AtmException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", ex.getErrorCodes());
        return modelAndView;
    }

    @Resource(name = "mappingResultPageAndName")
    public void setCommandPages(Map<CommandName, String> commandPages) {
        this.commandPages = commandPages;
    }

    @Resource(name = "delegatedInputParser")
    public void setDelegatedInputParser(DelegatedInputParser delegatedInputParser) {
        this.delegatedInputParser = delegatedInputParser;
    }
}
