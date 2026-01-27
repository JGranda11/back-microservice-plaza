package com.pragma.challenge.msvc_plaza.infrastructure.output.feign.dto.response;

import com.pragma.challenge.msvc_plaza.domain.util.enums.RoleName;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthorizationResponse {
    private String token;
    private RoleName role;
    private Long id;
}
