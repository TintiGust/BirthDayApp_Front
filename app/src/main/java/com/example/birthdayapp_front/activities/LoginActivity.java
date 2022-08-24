package com.example.birthdayapp_front.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.birthdayapp_front.databinding.ActivityLoginBinding;
import com.example.birthdayapp_front.utils.ApiCallback;
import com.example.birthdayapp_front.utils.Util;

public class LoginActivity extends AppCompatActivity implements ApiCallback {

    private ActivityLoginBinding binding;

    private EditText mEmailView;
    private EditText mPasswordView;
    private ProgressBar mLoadingProgressBar;
    private Button mLoginButton;

    public Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        handler = new Handler();

        mEmailView = binding.editTextEmail;
        mPasswordView = binding.password;
        mLoginButton = binding.login;
        mLoadingProgressBar = binding.loading;

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();

                mLoginButton.setEnabled(Util.isEmailValid(email) && Util.isPasswordValid(password));
            }
        };

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingProgressBar.setVisibility(View.VISIBLE);
                //loginViewModel.login(usernameEditText.getText().toString(),
                 //       passwordEditText.getText().toString());
            }
        });
    }

    @Override
    public void fail(String json) {
        //TODO
    }

    @Override
    public void success(String json) {
        //TODO
    }
}