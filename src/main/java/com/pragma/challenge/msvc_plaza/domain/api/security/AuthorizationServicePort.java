package com.pragma.challenge.msvc_plaza.domain.api.security;

import com.pragma.challenge.msvc_plaza.domain.model.security.AuthorizedUser;

public interface AuthorizationServicePort {
    AuthorizedUser authorize(String token);
}
