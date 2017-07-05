package com.home.atm.client.exception;

public class CustomAtmException extends RuntimeException {

    private String errorMessage;

    public CustomAtmException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}