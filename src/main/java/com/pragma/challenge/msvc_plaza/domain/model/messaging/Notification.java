package com.pragma.challenge.msvc_plaza.domain.model.messaging;

public class Notification {

    private String receiver;
    private String message;

    public Notification(NotificationBuilder builder) {
        this.receiver = builder.receiver;
        this.message = builder.message;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static NotificationBuilder builder(){
        return new NotificationBuilder();
    }

    public static class NotificationBuilder{
        private String receiver;
        private String message;

        public NotificationBuilder receiver(String receiver) {
            this.receiver = receiver;
            return this;
        }

        public NotificationBuilder message(String message) {
            this.message = message;
            return this;
        }

        public Notification build(){
            return new Notification(this);
        }
    }
}
