package com.example.smssending.otpsms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.smssending.otpsms.R;
import com.example.smssending.otpsms.model.User;

import java.util.ArrayList;

public class SentMessageAdapter extends ArrayAdapter<User> {
    public SentMessageAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sent_message_item, parent, false);
        }
        TextView fName = (TextView) convertView.findViewById(R.id.fName);
        TextView lName = (TextView) convertView.findViewById(R.id.lName);
        TextView phoneNo = (TextView) convertView.findViewById(R.id.time);
        TextView otp = (TextView) convertView.findViewById(R.id.otp);

        fName.setText(user.firstName);
        lName.setText(user.lastName);
        phoneNo.setText(user.phoneNo);
        otp.setText("OTP : "+user.otp);

        return convertView;
    }
}