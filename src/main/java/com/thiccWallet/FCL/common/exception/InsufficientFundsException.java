package com.thiccWallet.FCL.common.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String msg) {
        super(msg);
    }
}
