package com.home.atm.output;

import com.home.atm.command.PrintBalance;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("withdrawOutputResult")
public class WithdrawOutputResult implements  OutputResult {

    @Override
    public void printResult(List<PrintBalance> printBalances) {
        if (printBalances.isEmpty()) {
            System.out.println("You don't have money on balance!");
            return;
        }
        PrintBalance printBalance = printBalances.get(0);
        String formattedString = String.format("Removed from your account %d %s.", printBalance.getBalance(),
                printBalance.getCurrency());
        System.out.println(formattedString);
    }
}
