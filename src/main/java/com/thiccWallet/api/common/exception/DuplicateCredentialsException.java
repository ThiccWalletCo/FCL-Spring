package com.thiccWallet.api.common.exception;

public class DuplicateCredentialsException extends RuntimeException {
    public DuplicateCredentialsException(String msg) {
        super(msg);
    }
}
