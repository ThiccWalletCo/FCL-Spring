package com.thiccWallet.FCL.common.util.aspects;

import com.thiccWallet.FCL.common.dtos.ErrorResponse;
import com.thiccWallet.FCL.common.exception.DuplicateCredentialsException;
import com.thiccWallet.FCL.common.exception.InvalidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlingAspect {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidRequestException.class, DuplicateCredentialsException.class, MethodArgumentNotValidException.class,MethodArgumentNotValidException.class})
    public ErrorResponse handleBadRequests(Exception e) {
        return new ErrorResponse(400, e);
    }

}
