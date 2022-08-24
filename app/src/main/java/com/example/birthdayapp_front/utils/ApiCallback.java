package com.example.birthdayapp_front.utils;

public interface ApiCallback {

    void fail(String json);
    void success(String json);
}
