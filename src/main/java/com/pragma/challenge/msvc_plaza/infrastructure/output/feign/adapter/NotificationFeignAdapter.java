package com.pragma.challenge.msvc_plaza.infrastructure.output.feign.adapter;

import com.pragma.challenge.msvc_plaza.domain.model.messaging.Notification;
import com.pragma.challenge.msvc_plaza.domain.spi.messaging.NotificationSenderPort;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.client.NotificationFeign;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.dto.request.NotificationRequest;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.mapper.request.NotificationRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationFeignAdapter implements NotificationSenderPort {
    private final NotificationFeign notificationFeign;
    private final NotificationRequestMapper notificationRequestMapper;

    @Override
    public boolean sendNotification(Notification notification) {
        NotificationRequest request = notificationRequestMapper.toRequest(notification);
        return notificationFeign.sendNotification(request).isSent();
    }
}
