package com.pragma.challenge.msvc_plaza.domain.util;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenerationPIN {

    private GenerationPIN(){
        throw new IllegalStateException("Utility class");
    }

    private static final String LETTERS ="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static String generateRandomSecurityPin(){
        ThreadLocalRandom random = ThreadLocalRandom.current();

        return IntStream.range(0, DomainConstants.PIN_LENGTH)
                .mapToObj(i -> String.valueOf(LETTERS.charAt(random.nextInt(LETTERS.length()))))
                .collect(Collectors.joining());
    }
}
