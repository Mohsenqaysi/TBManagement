package com.example.mohsenqaysi.tbmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private String userIDInFireBase = "";
    private String TAG = "Status: ";

    Map<String, Object> childUpdates = new HashMap<>();
    Login LoginObject = new Login();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the passed Uid for the user in FireBase
        userIDInFireBase = new String(getIntent().getExtras().getString("ID").toString());
        TextView userIDLabel = (TextView) findViewById(R.id.userAuth_ID);
        userIDLabel.setText("user ID: " + userIDInFireBase);
        Log.e(TAG, "user ID: " + userIDInFireBase);

        mDatabase = FirebaseDatabase.getInstance().getReference();


    }

    public void signOut(View view) {
        LoginObject.sigOutUSer();
        startActivity(new Intent(this, Login.class));
        finish();
    }

    public void SaveUserDateToFirebase(View view) {

        // Test the database - real time database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
//        writeNewPost(userIDInFireBase, "Mohsen Barri Qaysi", "Al-gisi@hotmail.com");
        String[] array = new String[]
                {"Abbott", "Abbott", "Acevedo","Acosta", "Adams","Daenerys Targaryen", "Jon Snow",
                        " Tyrion Lannister", " Joffrey Baratheon"};
        for (String name : array) {
            writeNewPost(userIDInFireBase, name);
        }



        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Log.e("onChildAdded","----------------------------------");
//                User users = dataSnapshot.getValue(User.class);
//                childUpdates.put("name",users.name);
//                childUpdates.put("email",users.email);
//
//                Log.e(TAG,"Name: " + users.name);
//                Log.e(TAG,"Email: " + users.email);
//                Log.e(TAG,"prevChildKey: " + s);

                fireBaseUsersInTheHashMap();


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
//                Log.e("onChildChanged","----------------------------------");
//                User users = dataSnapshot.getValue(User.class);
//                childUpdates.put("name",users.name);
//                childUpdates.put("email",users.email);
//                Log.e(TAG,"Name: " + users.name);
//                Log.e(TAG,"Email: " + users.email);
//                Log.e(TAG,"prevChildKey: " + prevChildKey);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.e(TAG,"Error" + databaseError.getDetails());
            }
        });


    }

//    private void writeNewPost(String userIDInFireBase, String name, String email) {
//        User user = new User(name, email);
////        mDatabase.child(userIDInFireBase).setValue(user);
//        mDatabase.push().setValue()
//    }

    private void writeNewPost(String userIDInFireBase, String name) {
//        mDatabase.child(userIDInFireBase).setValue(user);
        mDatabase.push().setValue(name);
    }

    private void fireBaseUsersInTheHashMap(){

        for (Object object : childUpdates.values()) {

            Log.w(TAG,"object: " + object);

        }
    }

    public void showUsersList(View view) {
        startActivity(new Intent(this,UserList.class));
//        finish();
    }

    public void showWebView(View view) {
        startActivity(new Intent(this,showWebView.class));
    }
}
