package com.svit.epolice.utilities;

import android.util.Patterns;

import java.util.regex.Pattern;

public class DataValidation {

    public static boolean isEmpty(String str) {
        return str.equals("");
    }

    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        boolean check = true;
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            if (phone.length() != 10) {
                check = false;
            }
        }
        return check;
    }

}
