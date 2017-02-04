package com.example.mohsenqaysi.tbmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button sendloginUserEmail_ID;
    private EditText email;
    private ProgressDialog progressDialog;
    private String TAG = "Status: ";
    private ConstraintLayout parentLayout;

    //get an instance of the SncakBarMessages Class
    SnackBarMessages snackBarMessages = new SnackBarMessages();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaset_password);
        parentLayout = (ConstraintLayout) findViewById(R.id.activity_reaset_password_ID);
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
                if (!resetEmail.isEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(resetEmail).matches()) {
                    progressDialog.setMessage("Sending an email...");
                    progressDialog.show();
                    mAuth.sendPasswordResetEmail(resetEmail)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "Email sent.");
                                        progressDialog.hide();
                                        snackBarMessages.SnackBarMessages(parentLayout,"Email sent");
                                        snackBarMessages.showToast();
//                                        showToast(parentLayout,"Email sent");
//                                    progressDialog.
//                                    LogInActivityPage();
                                    } else {
                                        progressDialog.hide();
                                        snackBarMessages.SnackBarMessages(parentLayout,"Sorry, the email does not exists");
                                        snackBarMessages.showToast();
                                    }
                                }
                            });
                } else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(resetEmail).matches()) {
                    snackBarMessages.SnackBarMessages(parentLayout,"Please enter a valid email");
                    snackBarMessages.showToast();
                }else {
                    snackBarMessages.SnackBarMessages(parentLayout,"Please enter an email");
                    snackBarMessages.showToast();
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

//    //show messages in a SnackBar
//    private void showToast(String text) {
//        Snackbar.make(parentLayout, text, Snackbar.LENGTH_LONG).show();
//    }
}
