package com.thiccWallet.api.util.common.exception;

public class DuplicateLoginAttemptException extends RuntimeException {
    public DuplicateLoginAttemptException(String msg) {
        super(msg);
    }
}
