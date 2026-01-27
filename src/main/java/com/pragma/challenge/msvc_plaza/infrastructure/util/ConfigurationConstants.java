package com.pragma.challenge.msvc_plaza.infrastructure.util;

public class ConfigurationConstants {
    private ConfigurationConstants() {
        throw new IllegalStateException("Utility Class");
    }

    // Security
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String TOKEN_PREFIX = "Bearer ";
}
