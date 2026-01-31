package com.pragma.challenge.msvc_plaza.infrastructure.output.feign.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationRequest {
    private String receiver;
    private String message;
}
