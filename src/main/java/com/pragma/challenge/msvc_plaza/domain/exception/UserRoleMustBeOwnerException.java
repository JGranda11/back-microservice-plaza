package com.pragma.challenge.msvc_plaza.domain.exception;

public class UserRoleMustBeOwnerException extends RuntimeException {
    public UserRoleMustBeOwnerException() {
        super("A Restaurant Owner must have OWNER as his role");
    }
}
