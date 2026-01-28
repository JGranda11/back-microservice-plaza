package com.pragma.challenge.msvc_plaza.application.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponse {
    private Long id;
    private String restaurantId;
}
