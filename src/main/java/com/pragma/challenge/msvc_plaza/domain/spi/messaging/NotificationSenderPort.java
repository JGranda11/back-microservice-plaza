package com.pragma.challenge.msvc_plaza.domain.spi.messaging;

import com.pragma.challenge.msvc_plaza.domain.model.messaging.Notification;

public interface NotificationSenderPort {
    boolean sendNotification(Notification notification);
}
