package com.thiccWallet.api.util.common.exception;

public class NotLoggedInException extends RuntimeException {
    public NotLoggedInException(String msg) {
        super(msg);
    }
}
