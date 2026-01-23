package com.pragma.challenge.msvc_plaza.domain.exception;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    public EntityAlreadyExistsException(String className, String id){
        super(String.format(
                "An entity of type '%s' with id '%s' already exists",
                className,
                id
        ));
    }
}
