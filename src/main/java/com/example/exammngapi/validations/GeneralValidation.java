package com.example.exammngapi.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralValidation {
    public static boolean validateEmail(String email){
        final String EMAIL_REGEX =
                "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        final Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
