package com.example.birthdayapp_front.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

public class Users {

    public Long id;
    public String username;
    public String password;
    public String email;
    public ArrayList<Birthdays> birthdays;

    public String strJson;



    public Users(String json) throws JSONException, ParseException {
        strJson = json;

        JSONObject jsonObject = new JSONObject(json);
        id = jsonObject.getLong("id");
        username = jsonObject.getString("username");
        password = jsonObject.getString("password");
        email = jsonObject.getString("email");
        birthdays = new ArrayList<>();

        JSONArray jsonArray = jsonObject.getJSONArray("birthdays");
        for (int i = 0; i < jsonArray.length(); i++) {
            birthdays.add(new Birthdays(jsonObject.getJSONArray("birthdays").getJSONObject(i).toString()));
        }



    }


    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthdays=" + birthdays +
                ", strJson='" + strJson + '\'' +
                '}';
    }
}
