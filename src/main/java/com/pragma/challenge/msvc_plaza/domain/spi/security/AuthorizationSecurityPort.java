package com.pragma.challenge.msvc_plaza.domain.spi.security;

import com.pragma.challenge.msvc_plaza.domain.model.security.AuthorizedUser;

public interface AuthorizationSecurityPort {
    AuthorizedUser authorize(String token);
}
