package com.pragma.challenge.msvc_plaza.application.util;

public class ApplicationConstants {
    private ApplicationConstants(){
        throw new IllegalStateException("Utility Class");
    }

    // Regex
    public static final String PHONE_NUMBER_REGEX = "^(\\+\\d{2})?\\d{10}$";
    public static final String NIT_REGEX = "^\\d{6,16}";
    public static final String NAME_REGEX = "^(?!\\d+$)[A-Za-z0-9\\s]+$";

    // Not null messages
    public static final String NIT_FIELD_NOT_NULL = "'nit' field must not be null";
    public static final String OWNER_ID_FIELD_NOT_NULL = "'Owner's id' field must not be null";
    public static final String NAME_FIELD_NOT_NULL = "'name' field must not be null";
    public static final String ADDRESS_FIELD_NOT_NULL = "'address' field must not be null";
    public static final String PHONE_FIELD_NOT_NULL = "'phone' field must not be null";
    public static final String LOGO_URL_FIELD_NOT_NULL = "'urlLogo' field must not be null";
    public static final String DESCRIPTION_FIELD_NOT_NULL = "'description' field must not be null";
    public static final String RESTAURANT_ID_FIELD_NOT_NULL = "'Restaurant's id' field must not be null";
    public static final String PRICE_FIELD_NOT_NULL = "'price' field must not be null";
    public static final String CATEGORY_FIELD_NOT_NULL = "'category' field must not be null";
    public static final String IMAGE_URL_FIELD_NOT_NULL = "'category' field must not be null";


    // Wrong pattern messages
    public static final String WRONG_PHONE_FORMAT = "Given phone number does not match with expected pattern";
    public static final String WRONG_NAME_FORMAT = "A name of a Restaurant must have A least one letter";
    public static final String WRONG_NIT_FORMAT = "A document must be a number and be between 6 and 16";

    // Positive number
    public static final String PRICE_MUST_BE_POSITIVE = "'price' must be a positive number";
}
