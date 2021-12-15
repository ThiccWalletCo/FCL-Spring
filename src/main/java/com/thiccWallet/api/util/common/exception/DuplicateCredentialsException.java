package com.thiccWallet.api.util.common.exception;

public class DuplicateCredentialsException extends RuntimeException {
    public DuplicateCredentialsException(String msg) {
        super(msg);
    }
}
