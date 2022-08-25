package com.example.birthdayapp_front.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.birthdayapp_front.R;
import com.example.birthdayapp_front.databinding.ActivityLoginBinding;
import com.example.birthdayapp_front.utils.ApiCallback;
import com.example.birthdayapp_front.utils.Util;
import com.example.birthdayapp_front.utils.UtilApi;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements ApiCallback {

    private ActivityLoginBinding binding;

    private EditText mUsername;
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

        mUsername = binding.editTextUsername;
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
                String username = mUsername.getText().toString();
                String password = mPasswordView.getText().toString();

                mLoginButton.setEnabled(Util.isUsernameValid(username) && Util.isPasswordValid(password));
            }
        };

        mUsername.addTextChangedListener(textWatcher);
        mPasswordView.addTextChangedListener(textWatcher);

        mPasswordView.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // TODO : appeler la méthode pour tenter le login
                attemptLogin();
            }
            return false;
        });

        mLoginButton.setOnClickListener(v -> {
            // TODO : appeler la méthode pour tenter le login
            attemptLogin();
        });
    }

    private void attemptLogin() {

        mUsername.setError(null);
        mPasswordView.setError(null);

        String username = mUsername.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!Util.isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(username)) {
            mUsername.setError(getString(R.string.error_field_required));
            focusView = mUsername;
            cancel = true;
        } else if (!Util.isUsernameValid(username)) {
            mUsername.setError(getString(R.string.invalid_username));
            focusView = mUsername;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);

            Map<String, String> map = new HashMap<>();
            map.put("username", username);
            map.put("password", password);

            // TODO : Appeler la méthode permettant de faire un appel API via POST
            UtilApi.post(UtilApi.URL_LOGIN, map,this );

        }
    }

    private void showProgress(boolean visible) {
        mLoadingProgressBar.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void fail(final String json) {
        mLoadingProgressBar.setVisibility(View.INVISIBLE);
        handler.post(() -> {
            Log.d("lol", "fail: " + json);
            // TODO : Etablisser un comportement lors d'un fail
            Toast.makeText(this, json, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void success(final String json) {
        handler.post(() -> {
            Log.d("lol", "success: " + json);
            // TODO : Etablisser un comportement lors d'un success
            // TODO : Faites la redirection
            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("logBool", true);
            editor.apply();
            startActivity( new Intent(this, MainActivity.class));
        });
    }
}