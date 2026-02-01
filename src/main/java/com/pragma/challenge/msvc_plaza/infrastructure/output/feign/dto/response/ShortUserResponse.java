package com.pragma.challenge.msvc_plaza.infrastructure.output.feign.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShortUserResponse {
    private String name;
    private String lastname;
    private String phone;
}
