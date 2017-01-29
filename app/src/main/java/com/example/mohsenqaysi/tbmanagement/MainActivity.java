package com.example.mohsenqaysi.tbmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String userIDInFireBase = intent.getExtras().getString("ID");
        TextView userIDLabel = (TextView) findViewById(R.id.userAuth_ID);
        userIDLabel.setText("user ID: "+userIDInFireBase);
    }

}
