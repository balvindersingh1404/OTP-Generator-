package com.example.smssending.otpsms.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.smssending.otpsms.R;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class SendMessageActivity extends AppCompatActivity {

    Button sendButton;
    TextView messageText;
    String phoneNo;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        init();
        setMessageText();
        registerListener();


    }

    // Initialize data
    public void init() {
        messageText = (TextView) findViewById(R.id.messageText);
        sendButton = (Button) findViewById(R.id.sendButton);
        Intent intent = getIntent();
        phoneNo = intent.getStringExtra("phone_no");
    }

    //Register Listeners
    public void registerListener() {
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS(phoneNo, messageText.getText() + "");
            }
        });

    }

    //Generate OTP Message
    public void setMessageText() {
        Random rand = new Random();
        num = rand.nextInt(900000) + 100000;
        String message = "“Hi. Your OTP is: " + num;
        messageText.setText(message);
    }

    //Send SMS OTP
    public void sendSMS(String phoneNumber, String message) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PERMISSION_GRANTED) {
            insertIntoDB();
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(this, "OTP Send Successfully!",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), ContactListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
            Toast.makeText(this, "Please Enable Send Message Permission",
                    Toast.LENGTH_LONG).show();
        }
    }

    //Save the data into the Database
    protected void insertIntoDB() {

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("first_name");
        String lastName = intent.getStringExtra("last_name");
        String time = DateFormat.getDateTimeInstance().format(new Date());
        String otp = num + "";
        //  String phoneNumber=intent.getStringExtra("phone_no");

        String query = "INSERT INTO messageInfo (firstName,lastName,time,otp) VALUES('" + firstName + "', '" + lastName + "','" + time + "', '" + otp + "');";
        ContactListActivity.db.execSQL(query);
    }
}
