package com.pragma.challenge.msvc_plaza.domain.exception;

public class ErrorDuringReportingException extends RuntimeException {
    public ErrorDuringReportingException(String message) {
        super(message);
    }
}
