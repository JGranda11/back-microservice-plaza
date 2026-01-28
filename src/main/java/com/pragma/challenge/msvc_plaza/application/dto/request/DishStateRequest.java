package com.pragma.challenge.msvc_plaza.application.dto.request;

import com.pragma.challenge.msvc_plaza.domain.util.enums.DishState;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DishStateRequest {
    @NotNull
    private DishState state;
}
