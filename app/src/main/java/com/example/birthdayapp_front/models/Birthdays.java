package com.example.birthdayapp_front.models;

import com.example.birthdayapp_front.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Date;

public class Birthdays {

        public Date date;
        public String firstname;
        public String lastname;

        //    {
        //            "date": "1988-02-02",
        //            "firstName": "Peter",
        //            "lastName": "Bardu"
        //        }

    public Birthdays(String json) throws JSONException, ParseException {
            JSONObject jsonObject = new JSONObject(json);

            date = Util.initDateFromDB(jsonObject.getString("date"));
            firstname = jsonObject.getString("firstName");
            lastname = jsonObject.getString("lastName");
    }

    public Birthdays(Date date, String firstname, String lastname) {
            this.date = date;
            this.firstname = firstname;
            this.lastname = lastname;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put("date", Util.printDate(date));
            json.put("firstname", firstname);
            json.put("lastname", lastname);
        } catch (JSONException e) {
        }
        return json;
    }

}
