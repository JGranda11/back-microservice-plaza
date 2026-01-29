package com.pragma.challenge.msvc_plaza.domain.util;

public class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility Class");
    }

    // Fields
    public static final String NAME_FIELD = "name";

    // Pagination
    public static final Integer PAGE_SIZE = 10;


    public static final String TOKEN_PREFIX = "Bearer ";
}
