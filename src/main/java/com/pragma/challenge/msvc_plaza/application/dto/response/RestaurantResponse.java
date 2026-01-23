package com.pragma.challenge.msvc_plaza.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Objeto de respuesta que representa un restaurante")
public class RestaurantResponse {

    @Schema(
            description = "NIT unico de el restaurante",
            example = "900123456"
    )
    private String nit;

    @Schema(
            description = "Nombre del restaurante",
            example = "Pragma Food"
    )
    private String name;
}
