package com.thiccWallet.api.common.exception;

public class NotLoggedInException extends RuntimeException {
    public NotLoggedInException(String msg) {
        super(msg);
    }
}
