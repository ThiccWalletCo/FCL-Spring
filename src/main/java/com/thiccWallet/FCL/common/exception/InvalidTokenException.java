package com.thiccWallet.FCL.common.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String msg) {
        super(msg);
    }
    public InvalidTokenException(){ super(); };
}