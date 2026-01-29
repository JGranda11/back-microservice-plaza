package com.pragma.challenge.msvc_plaza.application.dto.request.order;

import com.pragma.challenge.msvc_plaza.application.util.ApplicationConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderRequest {
    @NotNull(message = ApplicationConstants.RESTAURANT_ID_FIELD_NOT_NULL)
    private Long restaurantId;

    @Min(value = ApplicationConstants.MIN_ORDER_DISHES, message = ApplicationConstants.AT_LEAST_ONE_DISH_IN_ORDER)
    private List<OrderDishRequest> dishes;
}
