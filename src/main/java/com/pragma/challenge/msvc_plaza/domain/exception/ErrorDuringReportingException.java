package com.pragma.challenge.msvc_plaza.domain.exception;

public class ErrorDuringReportingException extends RuntimeException {
    public ErrorDuringReportingException() {
        super("An error during the process of making the report has been occurred");
    }
}
