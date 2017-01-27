package com.example.mohsenqaysi.tbmanagement;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText userName;
    private EditText userNamePassword;
    String LOG = "Login OutPut";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void printLoginOutput(View view) {
        userName = (EditText)findViewById(R.id.userName_ID);
        userNamePassword = (EditText)findViewById(R.id.userNamePassword_ID);

        if (isEmailValid(String.valueOf(userName.getText()))) {
            Log.e(LOG, userName.getText() + " " + userNamePassword.getText());
        } else {
           showToast("Please enter a correct email format");
//            Log.e(LOG, "Please enter a correct email format");
        }
    }

    private boolean isEmailValid(String email) {
        Log.e("email value: ", email);
        return email.contains("@");
    }

    // Display a toast message
    private void showToast(String text ){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
