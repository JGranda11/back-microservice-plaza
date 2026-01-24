package com.pragma.challenge.msvc_plaza.application.dto.response;

import com.pragma.challenge.msvc_plaza.domain.util.enums.DishState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Respuesta con la información del plato al cliente")
public class DishResponse {

    private Long id;
    private String restaurantId;
    private String name;
    private String description;
    private Long price;
    private String category;
    private String imageUrl;
    private DishState state;
}
