package com.example.mohsenqaysi.tbmanagement.Authatications;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mohsenqaysi.tbmanagement.Helper.SnackBarMessages;
import com.example.mohsenqaysi.tbmanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    //get an instance of the SncakBarMessages Class
    SnackBarMessages snackBarMessages = new SnackBarMessages();
    private FirebaseAuth mAuth;
    private Button sendloginUserEmail_ID;
    private EditText email;
    private ProgressDialog progressDialog;
    private String TAG = "Status: ";
    private ConstraintLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaset_password);
        parentLayout = (ConstraintLayout) findViewById(R.id.activity_reaset_password_ID);


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
                                        snackBarMessages.SnackBarMessages(parentLayout,R.string.Email_Sent );
                                        snackBarMessages.showToast();
//                                        showToast(parentLayout,"Email sent");
//                                    progressDialog.
//                                    LogInActivityPage();
                                    } else {
                                        progressDialog.hide();
                                        snackBarMessages.SnackBarMessages(parentLayout, R.string.error_email_does_not_exist);
                                        snackBarMessages.showToast();
                                    }
                                }
                            });
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(resetEmail).matches()) {
                    snackBarMessages.SnackBarMessages(parentLayout, R.string.invalid_email_address);
                    snackBarMessages.showToast();
                } else {
                    snackBarMessages.SnackBarMessages(parentLayout, R.string.invalid_email_address);
                    snackBarMessages.showToast();
                }
            }
        });

    }

    private void LogInActivityPage() {
        startActivity(new Intent(this, Login.class));
        finish();
    }

}
