package com.pragma.challenge.msvc_plaza.domain.exception;

public class NotificationWasNotSentException extends RuntimeException{

    public NotificationWasNotSentException(){
        super("Notification service is not available, something wrong occurred, order status was not changed");
    }

}
