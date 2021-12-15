package com.thiccWallet.FCL.common.exception;

public class DuplicateLoginAttemptException extends RuntimeException {
    public DuplicateLoginAttemptException(String msg) {
        super(msg);
    }
}
