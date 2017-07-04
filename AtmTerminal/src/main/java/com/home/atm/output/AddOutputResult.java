package com.home.atm.output;

import com.home.atm.command.PrintBalance;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("addOutputResult")
public class AddOutputResult implements OutputResult {

    @Override
    public void printResult(List<PrintBalance> printBalances) {
        if (printBalances.isEmpty()) {
            System.out.println("You don't have money!");
            return;
        }
        PrintBalance printBalance = printBalances.get(0);
        String formattedString = String.format("Added %d in currency %s.", printBalance.getBalance(),
                printBalance.getCurrency());
        System.out.println(formattedString);
    }
}
