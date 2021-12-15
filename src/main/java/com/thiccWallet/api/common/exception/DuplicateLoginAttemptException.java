package com.thiccWallet.api.common.exception;

public class DuplicateLoginAttemptException extends RuntimeException {
    public DuplicateLoginAttemptException(String msg) {
        super(msg);
    }
}
