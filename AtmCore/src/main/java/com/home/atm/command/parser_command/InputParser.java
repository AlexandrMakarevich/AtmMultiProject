package com.home.atm.command.parser_command;

import com.home.atm.command.Command;

public interface InputParser {

    boolean commandMatch(String inputString);

    Command parseInput(String inputString);
}
