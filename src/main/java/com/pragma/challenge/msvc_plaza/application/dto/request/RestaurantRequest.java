package com.pragma.challenge.msvc_plaza.application.dto.request;

import com.pragma.challenge.msvc_plaza.application.util.ApplicationConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantRequest {

    @NotNull(message = ApplicationConstants.NIT_FIELD_NOT_NULL)
    @Pattern(regexp = ApplicationConstants.NIT_REGEX, message = ApplicationConstants.WRONG_NIT_FORMAT)
    private String nit;

    @NotNull(message = ApplicationConstants.OWNER_ID_FIELD_NOT_NULL)
    private Long ownerId;

    @NotNull(message = ApplicationConstants.NAME_FIELD_NOT_NULL)
    @Pattern(regexp = ApplicationConstants.NAME_REGEX, message = ApplicationConstants.WRONG_NAME_FORMAT)
    private String name;

    @NotNull(message = ApplicationConstants.ADDRESS_FIELD_NOT_NULL)
    private String address;

    @NotNull(message = ApplicationConstants.PHONE_FIELD_NOT_NULL)
    @Pattern(regexp = ApplicationConstants.PHONE_NUMBER_REGEX, message = ApplicationConstants.WRONG_PHONE_FORMAT)
    private String phone;

    @NotNull(message = ApplicationConstants.LOGO_URL_FIELD_NOT_NULL)
    private String logoUrl;

}
