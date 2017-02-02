package com.example.mohsenqaysi.tbmanagement;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    //Fire base auth
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mUser;
    private String userFirebaseAuth_ID = "";

    //init
    private TextInputLayout emailWrapper;
    private TextInputLayout passwordWrapper;
    private Button signIn;
    private EditText email;
    private EditText userPassword;
    private ProgressDialog progressDialog;
    private String TAG = "Status: ";
    private String resetemailAddress = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide the action bad and set the screen size to full
        getSupportActionBar().hide();
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        // init the inputs fields
        signIn = (Button) findViewById(R.id.loginUser_ID);
        email = (EditText) findViewById(R.id.userName_ID);
        userPassword = (EditText) findViewById(R.id.userNamePassword_ID);
        // init the progressDialog object
        progressDialog = new ProgressDialog(this);

        signIn.setOnClickListener(this);

        //init FirebseAuth
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    userFirebaseAuth_ID = user.getUid();
                    Log.e(TAG, "onAuthStateChanged:signed_in:" + userFirebaseAuth_ID);
                    MainActivityPage();
                } else {
                    // User is signed out
                    Log.e(TAG, "onAuthStateChanged:signed_out");
                    signInIntoFirebase();
                }
            }
        };

//        // Get the user authantication token form the server
//        mUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        mUser.getToken(false)
//                .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
//                    public void onComplete(@NonNull Task<GetTokenResult> task) {
//                        if (task.isSuccessful()) {
//                            String idToken = task.getResult().getToken();
//                            Log.e(TAG, "idToken: "+idToken);
//                            // Send token to your backend via HTTPS
//                            // ...
//                        } else {
//                            task.getException();
//                        }
//                    }
//                });


//        mAuth.signInWithCustomToken(userFirebaseAuth_ID)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Log.d(TAG, "signInWithCustomToken:onComplete:" + task.isSuccessful());
//
//                        // If sign in fails, display a message to the user. If sign in succeeds
//                        // the auth state listener will be notified and logic to handle the
//                        // signed in user can be handled in the listener.
//                        if (!task.isSuccessful()) {
//                            showToast("custom token: failed to log you in...");
//                        }
//                    }
//                });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private boolean isEmailValid(String email) {
        Log.e("email value: ", email);
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    // Display a toast message
    private void showToast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void signInIntoFirebase() {
        final TextInputLayout errorEmail = (TextInputLayout) findViewById(R.id.emailWrapper_ID);
        errorEmail.setErrorEnabled(true);
        final TextInputLayout errorPassword = (TextInputLayout) findViewById(R.id.passwordWrapper_ID);
        errorPassword.setErrorEnabled(true);

        String email = this.email.getText().toString().trim();
        String password = this.userPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email) || !isEmailValid(email)) {
            errorEmail.setError("Please enter a valid");
            // stop function execution
            return;
        } else {
            errorEmail.setErrorEnabled(false);
        }
        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            errorPassword.setError("Password is too short");
            // stop function execution
            return;
        } else {
            errorPassword.setErrorEnabled(false);
        }
        errorEmail.setError(null);
        errorPassword.setError(null);
        errorEmail.setErrorEnabled(false);
        errorPassword.setErrorEnabled(false);

        // if the validation is ok ... Do this ->
        progressDialog.setMessage("Login in user...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            showToast("Log in failed");
                            Log.w(TAG,task.getException().fillInStackTrace());
                            progressDialog.hide();
                            errorEmail.setError("Email or Password is wrong");
                            errorPassword.setError("Email or Password is wrong");
                        } else {
//                            showToast("Log in successful");
                            progressDialog.dismiss();
                            MainActivityPage();
                        }
                    }
                });
    }

    private void MainActivityPage() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("ID", userFirebaseAuth_ID);
        startActivity(intent);
        finish();
    }

    public void sigOutUSer(){
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    public void onClick(View view) {
        if (view == signIn) {
            signInIntoFirebase();
        }
    }

    // reaset user password
    public void reasetPassword(View view) {
        // Display input dialog to get the email
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter your emial");
        // get the input string
        final EditText input = new EditText(this);
        //set the input type
        input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        builder.setView(input);

        // send buttons
        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetemailAddress = input.getText().toString().trim();
                // Sens the reset password to the user email
                mAuth.sendPasswordResetEmail(resetemailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "Email sent.");
                                    showToast("Email sent");
                                } else {
                                    showToast("Please enter a correct email");
                                }
                            }
                        });
            }
        });

        // Cancel buttons
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}