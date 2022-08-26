package com.example.birthdayapp_front.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birthdayapp_front.R;
import com.example.birthdayapp_front.adaptaters.BirthDayAdaptater;
import com.example.birthdayapp_front.adaptaters.ListItem;
import com.example.birthdayapp_front.databinding.ActivityMainBinding;
import com.example.birthdayapp_front.models.Users;
import com.example.birthdayapp_front.utils.Util;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public ActivityMainBinding binding;
    public TextView mTextView;
    public Users mUserLog;
    public Handler handler;
    private BirthDayAdaptater mBirthdayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        String jsonUser = bundle.getString("jsonUser");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        handler = new Handler();

        try {
            mUserLog = new Users(jsonUser);
        } catch (Exception e) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        ArrayList<ListItem> listItems = Util.createListItems(mUserLog.birthdays);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view_home);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mBirthdayAdapter = new BirthDayAdaptater(this, mUserLog.birthdays);
        recyclerView.setAdapter(mBirthdayAdapter);

    }

}