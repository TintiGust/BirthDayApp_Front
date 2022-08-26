package com.example.birthdayapp_front.adaptaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birthdayapp_front.R;
import com.example.birthdayapp_front.models.Birthdays;
import com.example.birthdayapp_front.utils.Util;

import java.util.ArrayList;

public class BirthDayAdaptater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private ArrayList<ListItem> mListItems;
    private final ArrayList<Birthdays> mListItems2;


    public BirthDayAdaptater(Context mContext, ArrayList<Birthdays> listItems) {
        this.mContext = mContext;
        this.mListItems2 = listItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v1 = LayoutInflater.from(mContext).inflate(R.layout.item_birthday, parent, false);
        return new BirthDayViewHolder(v1);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Birthdays birthday = (mListItems2.get(position));
        BirthDayViewHolder birthDayViewHolder = (BirthDayViewHolder) viewHolder;
        birthDayViewHolder.mTextViewName.setText(birthday.firstname + " " + birthday.lastname);
        birthDayViewHolder.mTextViewDate.setText(birthday.date.getDate()+"");
        birthDayViewHolder.mTextViewAge.setText(Util.getAge(birthday.date) + " ans");
    }

    @Override
    public int getItemCount() {
        return mListItems2.size();
    }

    public class BirthDayViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextViewDate;
        private final TextView mTextViewName;
        private final TextView mTextViewAge;

        public BirthDayViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewDate = itemView.findViewById(R.id.text_view_item_date);
            mTextViewName = itemView.findViewById(R.id.text_view_item_name);
            mTextViewAge = itemView.findViewById(R.id.text_view_item_age);
        }
    }
}
