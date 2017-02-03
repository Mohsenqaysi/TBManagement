package com.example.mohsenqaysi.tbmanagement;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button sendloginUserEmail_ID;
    private EditText email;
    private ProgressDialog progressDialog;
    private String TAG = "Status: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaset_password);
        hideNavigationBar();


        mAuth = FirebaseAuth.getInstance();
        sendloginUserEmail_ID = (Button) findViewById(R.id.sendloginUserEmail_ID);
        email = (EditText) findViewById(R.id.resetEmail_ID);
        progressDialog = new ProgressDialog(this);


        sendloginUserEmail_ID.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String resetEmail = email.getText().toString();
                Log.w(TAG, "reset email: " + resetEmail);
                if (!resetEmail.isEmpty()) {
                    progressDialog.setMessage("Sending an email...");
                    progressDialog.show();
                    mAuth.sendPasswordResetEmail(resetEmail)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "Email sent.");
                                        progressDialog.hide();
                                        showToast("Email sent");
//                                    progressDialog.
//                                    LogInActivityPage();
                                    } else {
                                        progressDialog.hide();
                                        showToast("Please enter a valid email");
                                    }
                                }
                            });
                } else {
                    showToast("Please enter an email");
                }
            }
        });

    }

    private void hideNavigationBar() {
        // Hide the action bar and set the screen size to full
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void LogInActivityPage() {
        startActivity(new Intent(this, Login.class));
        finish();
    }

    // Display a toast message
    private void showToast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}