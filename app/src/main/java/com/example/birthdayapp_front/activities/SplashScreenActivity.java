package com.example.birthdayapp_front.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        Log.d("lol", "splash screen activity" + sharedPref.getBoolean("logBool", false));

        if ( sharedPref.getBoolean("logBool", false)) {
            Log.d("lol", "if splashscreen");
            startActivity(new Intent(this, MainActivity.class));
        }
        else{
            Log.d("lol", "else splashscreen ");
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }

}
