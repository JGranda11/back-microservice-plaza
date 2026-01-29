package com.pragma.challenge.msvc_plaza.application.dto.response.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDishResponse {
    Long dishId;
    Integer quantity;
}
