package com.example.mohsenqaysi.tbmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;


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
        writeNewPost(userIDInFireBase, "Mohsen Barri Qaysi","Al-gisi@hotmail.com");

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

              Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
//                String users = (String) map.get("Al-gisi@hotmail.com");
//                String email = (String) map.get("email");
//                 String All_users = dataSnapshot.getRef().getKey();
//                String id = map.keySet().toString();
//                String object_Value = map.get(id).toString();
                for (Map.Entry<String, Object> child : ((Map<String, Object>) dataSnapshot.getValue()).entrySet()) {
                    System.out.println("child: " + child.getValue().+"\n");

                }
//                Log.w(TAG, "All_users: " + id );
//                Log.w(TAG, "object_value: " + object_Value);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    // [START write_fan_out]
    private void writeNewPost(String userIDInFireBase, String username_id, String email) {
        User user = new User(username_id,email);
        mDatabase.child("FR").child("users").child(userIDInFireBase).setValue(user);
//        mDatabase.child("FR").child("users").child(userIDInFireBase).child("Data Of Diagnosis").setValue(user);
//        mDatabase.child("FR").child("users").push({"name: Mohsen"});


//        mDatabase.setValue(user);
    }
    // [END write_fan_out]
}
