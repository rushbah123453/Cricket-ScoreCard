package com.cricket.cricketscorecard.exception;

public class CricketScoreBoardException extends  RuntimeException{
    private ExceptionType exceptionType;
    private String message;

    public CricketScoreBoardException(ExceptionType exceptionType, String message) {
        this.exceptionType = exceptionType;
        this.message = message;
    }

    public CricketScoreBoardException(String message, ExceptionType exceptionType, String message1) {
        super(message);
        this.exceptionType = exceptionType;
        this.message = message1;
    }

    public CricketScoreBoardException(String message, Throwable cause, ExceptionType exceptionType, String message1) {
        super(message, cause);
        this.exceptionType = exceptionType;
        this.message = message1;
    }

    public CricketScoreBoardException(Throwable cause, ExceptionType exceptionType, String message) {
        super(cause);
        this.exceptionType = exceptionType;
        this.message = message;
    }

    public CricketScoreBoardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ExceptionType exceptionType, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionType = exceptionType;
        this.message = message1;
    }
}
