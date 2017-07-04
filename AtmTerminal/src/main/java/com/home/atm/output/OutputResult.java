package com.home.atm.output;

import com.home.atm.command.PrintBalance;
import java.util.List;

public interface OutputResult {

    void printResult(List<PrintBalance> printBalances);
}
