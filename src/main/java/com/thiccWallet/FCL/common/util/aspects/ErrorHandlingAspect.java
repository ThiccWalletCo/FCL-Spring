package com.thiccWallet.FCL.common.util.aspects;

import com.thiccWallet.FCL.common.dtos.ErrorResponse;
import com.thiccWallet.FCL.common.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
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

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({NotLoggedInException.class, DuplicateLoginAttemptException.class})
    public ErrorResponse handleUnauthorizedRequests(Exception e) {
        return new ErrorResponse(401, e);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchUserException.class})
    public ErrorResponse handleNotFoundRequests(Exception e) {
        return new ErrorResponse(404, e);
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ErrorResponse handleUnsupportedMediaRequests(Exception e) {
        return new ErrorResponse(415, e);
    }


}
