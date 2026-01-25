package com.pragma.challenge.msvc_plaza.application.dto.request;

import com.pragma.challenge.msvc_plaza.application.util.ApplicationConstants;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatchDishRequest {
    private String description;

    @Positive(message = ApplicationConstants.PRICE_MUST_BE_POSITIVE)
    private Long price;

}
