package com.pragma.challenge.msvc_plaza.application.dto.request;

import com.pragma.challenge.msvc_plaza.application.util.ApplicationConstants;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeRequest {
    @NotNull(message = ApplicationConstants.EMPLOYEE_ID_FIELD_NOT_NULL)
    private Long id;

    @NotNull(message = ApplicationConstants.RESTAURANT_ID_FIELD_NOT_NULL)
    private String restaurantId;

}
