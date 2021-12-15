package com.thiccWallet.api.common.exception;

public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException(String msg) {
        super(msg);
    }
}
