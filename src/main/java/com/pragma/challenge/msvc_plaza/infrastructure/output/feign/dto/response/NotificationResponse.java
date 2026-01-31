package com.pragma.challenge.msvc_plaza.infrastructure.output.feign.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationResponse {
    private boolean sent;
    private String message;
}
