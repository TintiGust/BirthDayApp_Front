package com.example.birthdayapp_front.utils;

import android.content.Context;
import android.util.Patterns;

import com.example.birthdayapp_front.models.Users;
import org.json.JSONException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private static final String PREF_FILE = "pref_file";
    private static final String USER = "user";

    public static void setUser(Context context, String json) {

        // TODO : sauvegarder
    }

    public static Users getUser(Context context) throws JSONException, ParseException {
        // TODO : restaurer
        return null;
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        if (pattern.matcher(email).matches()&& email.length()<=50) {
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean isPasswordValid(String password) {
        if (password.length()>=5 && password.length()<=30){
            return true;
        }
        else {
            return false;
        }
    }
}
