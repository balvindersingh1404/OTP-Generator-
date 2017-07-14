package com.example.smssending.otpsms.fragment;

import android.Manifest;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.smssending.otpsms.R;
import com.example.smssending.otpsms.activity.ContactListActivity;
import com.example.smssending.otpsms.adapter.SentMessageAdapter;
import com.example.smssending.otpsms.model.User;

import java.util.ArrayList;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class SentMessagesFragment extends Fragment {
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sent_messages_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       listView = (ListView) view.findViewById(R.id.sentMessage);
        openAndQueryDatabase();

    }


    private void openAndQueryDatabase() {
        ArrayList<User> arrayOfUsers = new ArrayList<User>();

        Cursor c = ContactListActivity.db.rawQuery("SELECT firstName,lastName,time, otp FROM messageInfo ORDER BY id DESC", null);
            if (c != null ) {
                if  (c.moveToFirst()) {
                    do {
                        String firstName=c.getString(c.getColumnIndex("firstName"));
                        String lastName=c.getString(c.getColumnIndex("lastName"));
                        String phoneNumber=c.getString(c.getColumnIndex("time"));
                        String otp=c.getString(c.getColumnIndex("otp"));

                        User user =new User(firstName,lastName,phoneNumber,otp);

                        arrayOfUsers.add(user);

                    }while (c.moveToNext());
                }
            }
        SentMessageAdapter adapter = new SentMessageAdapter(getContext(), arrayOfUsers);
        listView.setAdapter(adapter);

    }

}