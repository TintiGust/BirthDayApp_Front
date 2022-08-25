package com.example.birthdayapp_front.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        if ( sharedPref.getBoolean("logBool", false)) {
            startActivity(new Intent(this, MainActivity.class));
        }
        else{
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
