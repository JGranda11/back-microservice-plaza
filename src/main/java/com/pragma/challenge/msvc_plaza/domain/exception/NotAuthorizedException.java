package com.pragma.challenge.msvc_plaza.domain.exception;

public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException() {
        super("User has not authorization over the action");
    }
}
