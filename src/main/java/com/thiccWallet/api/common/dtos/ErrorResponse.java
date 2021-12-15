package com.thiccWallet.api.common.dtos;

import java.time.LocalDateTime;

public class ErrorResponse {
    private int statusCode;
    private String message;
    private String cause;
    private String datetime;

    public ErrorResponse() {
        super();
        this.datetime = LocalDateTime.now().toString();
    }

    public ErrorResponse(int statusCode, Throwable t){
        this();
        this.statusCode = statusCode;
        this.message = t.getMessage();
        this.cause = t.getClass().getSimpleName();
    }

    public ErrorResponse(int code, String msg, Throwable t) {
        this(code, t);
        this.message = msg;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", cause='" + cause + '\'' +
                ", datetime='" + datetime + '\'' +
                '}';
    }
}
