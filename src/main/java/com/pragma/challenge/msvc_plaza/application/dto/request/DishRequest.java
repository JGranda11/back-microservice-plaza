package com.pragma.challenge.msvc_plaza.application.dto.request;

import com.pragma.challenge.msvc_plaza.application.util.ApplicationConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DishRequest {
    @NotNull(message = ApplicationConstants.RESTAURANT_ID_FIELD_NOT_NULL)
    private Long restaurantId;

    @NotNull(message = ApplicationConstants.NAME_FIELD_NOT_NULL)
    private String name;

    @NotNull(message = ApplicationConstants.DESCRIPTION_FIELD_NOT_NULL)
    private String description;

    @NotNull(message = ApplicationConstants.PRICE_FIELD_NOT_NULL)
    @Positive(message = ApplicationConstants.PRICE_MUST_BE_POSITIVE)
    private Long price;

    @NotNull(message = ApplicationConstants.CATEGORY_FIELD_NOT_NULL)
    private String category;

    @NotNull(message = ApplicationConstants.IMAGE_URL_FIELD_NOT_NULL)
    private String imageUrl;
}
