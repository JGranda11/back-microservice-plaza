package com.pragma.challenge.msvc_plaza.infrastructure.output.feign.client;

import com.pragma.challenge.msvc_plaza.infrastructure.configuration.feign.FeignClientConfiguration;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.dto.request.NotificationRequest;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.dto.response.NotificationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "NOTIFICATION-CLIENT",
        url = "http://localhost:8083/v1/notifications" ,
        configuration = FeignClientConfiguration.class
)
public interface NotificationFeign {

    @PostMapping
    NotificationResponse sendNotification(@RequestBody NotificationRequest request);
}
