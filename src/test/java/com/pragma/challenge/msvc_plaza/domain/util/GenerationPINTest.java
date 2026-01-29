package com.pragma.challenge.msvc_plaza.domain.util;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GenerationPINTest {
    private static final int PIN_LENGTH = DomainConstants.PIN_LENGTH;
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static final String GENERATED_PIN_SHOULD_NOT_BE_NULL = "Generated PIN should not be null";
    public static final String GENERATED_PIN_SHOULD_HAVE_THE_CORRECT_LENGTH = "Generated PIN should have the correct length";
    public static final String GENERATED_PIN_CONTAINS_INVALID_CHARACTER = "Generated PIN contains invalid character: ";
    public static final String GENERATED_PINS_SHOULD_NOT_ALL_BE_IDENTICAL = "Generated PINs should not all be identical";


    @Test
    void shouldGeneratePinOfCorrectLength() {
        String pin = GenerationPIN.generateRandomSecurityPin();
        assertNotNull(pin, GENERATED_PIN_SHOULD_NOT_BE_NULL);
        assertEquals(PIN_LENGTH, pin.length(), GENERATED_PIN_SHOULD_HAVE_THE_CORRECT_LENGTH);
    }

    @Test
    void shouldOnlyContainValidCharacters() {
        String pin = GenerationPIN.generateRandomSecurityPin();
        for (char c : pin.toCharArray()) {
            assertTrue(LETTERS.indexOf(c) >= 0, GENERATED_PIN_CONTAINS_INVALID_CHARACTER + c);
        }
    }


}