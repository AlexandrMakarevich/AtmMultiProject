package com.home.atm.exception;

public class AtmException extends RuntimeException {

    private ErrorCodes errorCodes;

    public AtmException(ErrorCodes errorCodes) {
        super(errorCodes.getErrorMessage());
        this.errorCodes = errorCodes;
    }

    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }
}
