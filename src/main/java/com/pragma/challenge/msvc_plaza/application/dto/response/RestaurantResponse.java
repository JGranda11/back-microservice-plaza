package com.pragma.challenge.msvc_plaza.application.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantResponse {
    private String nit;
    private String name;
}
