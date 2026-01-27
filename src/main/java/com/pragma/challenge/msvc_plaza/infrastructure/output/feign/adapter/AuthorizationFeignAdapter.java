package com.pragma.challenge.msvc_plaza.infrastructure.output.feign.adapter;

import com.pragma.challenge.msvc_plaza.domain.model.security.AuthorizedUser;
import com.pragma.challenge.msvc_plaza.domain.spi.security.AuthorizationSecurityPort;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.client.AuthFeign;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.mapper.response.AuthorizationResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationFeignAdapter implements AuthorizationSecurityPort {

    private final AuthFeign authFeign;
    private final AuthorizationResponseMapper authorizationResponseMapper;

    @Override
    public AuthorizedUser authorize(String token) {
        return authorizationResponseMapper.toResponse(
                authFeign.authorize(token)
        );
    }
}
