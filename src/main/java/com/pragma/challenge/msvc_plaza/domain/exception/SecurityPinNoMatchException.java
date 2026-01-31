package com.pragma.challenge.msvc_plaza.domain.exception;

public class SecurityPinNoMatchException extends RuntimeException {
    public SecurityPinNoMatchException() {
        super("A Restaurant onwer must have Owner a his role");
    }
}
