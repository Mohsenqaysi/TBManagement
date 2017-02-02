package com.example.mohsenqaysi.tbmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;



public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    Login LoginObject = new Login();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the passed Uid for the user in FireBase
        String userIDInFireBase = new String(getIntent().getExtras().getString("ID").toString());
        TextView userIDLabel = (TextView) findViewById(R.id.userAuth_ID);
        userIDLabel.setText("user ID: "+ userIDInFireBase);
    }


    public void signOut(View view) {
        LoginObject.sigOutUSer();
        startActivity(new  Intent(this,Login.class));
        finish();
    }
}
