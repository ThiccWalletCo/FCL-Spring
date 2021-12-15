package com.thiccWallet.api.util.common.exception;

public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException(String msg) {
        super(msg);
    }
}
