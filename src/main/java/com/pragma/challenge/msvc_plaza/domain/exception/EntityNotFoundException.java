package com.pragma.challenge.msvc_plaza.domain.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String className, String id){
        super(String.format(
                "Entity of type '%s' with id '%s' has not been found",
                className,
                id
        ));
    }
}
