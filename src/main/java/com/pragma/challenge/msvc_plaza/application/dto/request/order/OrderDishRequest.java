package com.pragma.challenge.msvc_plaza.application.dto.request.order;

import com.pragma.challenge.msvc_plaza.application.util.ApplicationConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDishRequest {
    @NotNull(message = ApplicationConstants.DISH_ID_FIELD_NOT_NULL)
    Long dishId;

    @Positive(message = ApplicationConstants.QUANTITY_MUST_BE_POSITIVE)
    @NotNull(message = ApplicationConstants.QUANTITY_FIELD_NOT_NULL)
    Integer quantity;
}
