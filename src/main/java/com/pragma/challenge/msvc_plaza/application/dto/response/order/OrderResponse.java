package com.pragma.challenge.msvc_plaza.application.dto.response.order;

import com.pragma.challenge.msvc_plaza.domain.util.enums.OrderState;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private String customerId;
    private String restaurantId;
    private List<OrderDishResponse> dishes;
    private OrderState state;
    private String assignedEmployeeId;
    private String securityPin;
}
