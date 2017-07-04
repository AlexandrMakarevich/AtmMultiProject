package com.home.atm.output;

import com.home.atm.command.PrintBalance;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("exitOutputResult")
public class ExitOutputResult implements OutputResult {

    @Override
    public void printResult(List<PrintBalance> printBalances) {
        if (printBalances.isEmpty()) {
            System.out.println("Get command exit!");
            System.exit(0);
        }
    }
}
