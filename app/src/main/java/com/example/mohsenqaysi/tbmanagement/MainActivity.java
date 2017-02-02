package com.example.mohsenqaysi.tbmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private  String userIDInFireBase ="";
    private String TAG = "Status: ";


    Login LoginObject = new Login();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the passed Uid for the user in FireBase
         userIDInFireBase = new String(getIntent().getExtras().getString("ID").toString());
        TextView userIDLabel = (TextView) findViewById(R.id.userAuth_ID);
        userIDLabel.setText("user ID: "+ userIDInFireBase);
        Log.e(TAG, "user ID: " + userIDInFireBase);



        // Test the database - real time database
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public void signOut(View view) {
        LoginObject.sigOutUSer();
        startActivity(new  Intent(this,Login.class));
        finish();
    }

    public void SaveUserDateToFirebase(View view) {

        mDatabase.child("users").child(userIDInFireBase).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // make the user data object ready to be saved
                User user = dataSnapshot.getValue(User.class);

                // [START_EXCLUDE]
                if (user == null) {
                    // User is null, error out
                    // TODO: // FIXME: 2/2/17
                    Log.w(TAG, "User " + userIDInFireBase + "" );
                    Toast.makeText(MainActivity.this,
                            "Error: could not fetch user.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // Write new post
                    writeNewPost(userIDInFireBase, user.username_ID);
                }

                // Finish this Activity, back to the stream
                finish();
                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "getUser:onCancelled", databaseError.toException());
            }
        }
    );
    }

    // [START write_fan_out]
    private void writeNewPost(String userIDInFireBase, String username_id) {
        User user = new User(username_id);
        mDatabase.child("users").child(userIDInFireBase).setValue(user);
    }
    // [END write_fan_out]
}
