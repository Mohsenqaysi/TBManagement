package com.example.mohsenqaysi.tbmanagement;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
    //
    private Button signIn;
    private EditText email;
    private EditText userPassword;
    private ProgressDialog progressDialog;
    private String TAG = "Status: ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // init the inputs fields
        signIn = (Button)findViewById(R.id.loginUser_ID);
        email = (EditText)findViewById(R.id.userName_ID);
        userPassword = (EditText)findViewById(R.id.userNamePassword_ID);
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
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out");
            }
            // ...
        }
    };
}

    @Override
    public void onStart(){
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
        return email.contains("@");
    }
    private boolean isPasswordValid(String password){
        return password.length() >= 6;
    }

    // Display a toast message
    private void showToast(String text ){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void signInIntoFirebase() {

        String email = this.email.getText().toString().trim();
        String  password = this.userPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            showToast("Please enter an email");
            // stop function execution
            return;
        }
        if (!isEmailValid(email)){
            showToast("Please enter a valid email");
            // stop function execution
            return;
        }
        if(TextUtils.isEmpty(password)){
            showToast("Please enter a password");
            return;
        }
        if (!isPasswordValid(password)){
            showToast("Please enter a password bigger the 6 digits");
            return;
        }

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
//                Toast.makeText(EmailPasswordActivity.this, R.string.auth_failed,
//                        Toast.LENGTH_SHORT).show();
                showToast("Log in failed");
                progressDialog.hide();

            } else {
                showToast("Log in successful");
                progressDialog.hide();
            }

        }
    });
}

    @Override
    public void onClick(View view) {
        if (view == signIn ){
            signInIntoFirebase();
        }

    }


}
