package com.pragma.challenge.msvc_plaza.domain.util;

public class TokenHolder {
    private TokenHolder(){
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        TokenHolder.token = token;
    }
}
