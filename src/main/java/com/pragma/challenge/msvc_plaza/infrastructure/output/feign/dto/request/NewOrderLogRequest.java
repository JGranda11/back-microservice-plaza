package com.pragma.challenge.msvc_plaza.infrastructure.output.feign.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewOrderLogRequest {
    private Long orderId;
    private String customerId;
    private String restaurantId;
}
