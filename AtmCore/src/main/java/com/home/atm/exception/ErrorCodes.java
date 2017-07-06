package com.home.atm.exception;

public enum ErrorCodes {

    NOT_ENOUGH_MONEY("Not enough money on the account!"),
    NO_CURRENCY("You don't have money on currency"),
    NO_CHANGES("No column has been changed!Currency doesn't exist."),
    NO_MONEY_ON_THIS_CURRENCY("You don't have money on this currency!"),
    ERROR_CONFIGURATIONS("Error configurations.Map not initialized.");

    private String errorMessage;

    ErrorCodes(String message) {
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
