package com.example.mohsenqaysi.tbmanagement;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
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
    private String userFirebaseAuth_ID = "";

    //init
    private Button signIn;
    private EditText email;
    private EditText password;

    private String userEmail = "";
    private String userPassword = "";
    private ProgressDialog progressDialog;
    private String TAG = "Status: ";
    private String resetemailAddress = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide the top part of the screen
        hideNavigationBar();
        setContentView(R.layout.activity_login);

        // init the inputs fields
        signIn = (Button) findViewById(R.id.loginUser_ID);
        email = (EditText) findViewById(R.id.userName_ID);
        password = (EditText) findViewById(R.id.userNamePassword_ID);
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

                    // TODO: Check is the user is logied in for the first time and ask the to fill their details
                    MainActivityPage();
                } else {
                    // User is signed out
                    Log.e(TAG, "onAuthStateChanged:signed_out");
                    signInIntoFirebase();
                }
            }
        };

    }



    private void hideNavigationBar() {
        // Hide the action bar and set the screen size to full
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Clear all value here
        email.setText("");
        password.setText("");
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onPause(){
        super.onPause();
        // Clear all value here
        email.setText("");
        password.setText("");
    }

    @Override
    public void onResume(){
        super.onResume();
        // Clear all value here
        email.setText("");
        password.setText("");
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

         userEmail = this.email.getText().toString().trim();
         userPassword = this.password.getText().toString().trim();

        if (TextUtils.isEmpty(userEmail) || !isEmailValid(userEmail)) {
            errorEmail.setError("Please enter a valid email");
            // stop function execution
            return;
        } else {
            errorEmail.setErrorEnabled(false);
        }
        if (TextUtils.isEmpty(userPassword) || !isPasswordValid(userPassword)) {
            errorPassword.setError("Please enter a valid password");
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
        progressDialog.setTitle("Connecting");
        progressDialog.setMessage("Login in user...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(userEmail, userPassword)
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
        startActivity(new Intent(this,MainActivity.class).putExtra("ID", userFirebaseAuth_ID));
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

    // reset user password
    public void resetPassword(View view) {
        // goto the reset activity page
        startActivity(new Intent(this,ResetPassword.class));
    }
}