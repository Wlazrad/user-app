package com.example.userapp.commons;

public interface ValidationPatterns {
    String EMAIL = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    String MOBILE_PHONE = "[1-9]{1}[0-9]{8}";
    String ONLY_DIGITS_LETTERS_AND_DASHES = "^[A-Za-z0-9-]*$";
    String POSTAL_CODE = "[0-9]{2}-[0-9]{3}";
}
