package com.example.smssending.otpsms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.smssending.otpsms.R;

public class ContactDetailActivity extends AppCompatActivity {

    TextView firstName, lastName, phoneNumber;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        init();
        registerListener();
    }

    // Initialize data
    public void init(){
        firstName = (TextView) findViewById(R.id.firstName);
        lastName = (TextView) findViewById(R.id.lastName);
        phoneNumber = (TextView) findViewById(R.id.phoneNumber);
        sendButton = (Button) findViewById(R.id.sendButton);

        //Get previous intent items
        Intent intent = getIntent();
        firstName.setText(intent.getStringExtra("first_name"));
        lastName.setText(intent.getStringExtra("last_name"));
        phoneNumber.setText(intent.getStringExtra("phone_no"));
    }

    //Register Listeners
    public void registerListener(){
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SendMessageActivity.class);

                intent.putExtra("first_name", firstName.getText());
                intent.putExtra("last_name", lastName.getText());
                intent.putExtra("phone_no", phoneNumber.getText());
                startActivity(intent);
            }
        });
    }
}
