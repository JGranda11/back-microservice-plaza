package com.pragma.challenge.msvc_plaza.domain.usecase.security;

import com.pragma.challenge.msvc_plaza.domain.api.security.AuthorizationServicePort;
import com.pragma.challenge.msvc_plaza.domain.exception.NotAuthorizedException;
import com.pragma.challenge.msvc_plaza.domain.model.security.AuthorizedUser;
import com.pragma.challenge.msvc_plaza.domain.spi.security.AuthorizationSecurityPort;

public class AuthorizationUseCase implements AuthorizationServicePort {

    private final AuthorizationSecurityPort authorizationSecurityPort;

    public AuthorizationUseCase(AuthorizationSecurityPort authorizationSecurityPort) {
        this.authorizationSecurityPort = authorizationSecurityPort;
    }

    @Override
    public AuthorizedUser authorize(String token) {
        try{
            return authorizationSecurityPort.authorize(token);
        } catch (Exception e){
            throw new NotAuthorizedException();
        }
    }
}
