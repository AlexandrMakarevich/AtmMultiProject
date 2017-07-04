package com.home.atm.output;

import com.home.atm.command.PrintBalance;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("printOutputResult")
public class PrintOutputResult implements OutputResult {

    @Override
    public void printResult(List<PrintBalance> printBalances) {
        if (printBalances.isEmpty()) {
            System.out.println("You don't have money on balance!");
            return;
        }
        for (PrintBalance printBalance : printBalances) {
            String formattedString = String.format("On your balance is %d in currency %s.",
                    printBalance.getBalance(), printBalance.getCurrency());
            System.out.println(formattedString);
        }
    }
}
