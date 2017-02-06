package com.example.mohsenqaysi.tbmanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserList extends AppCompatActivity {

    private ListView mListView;
    private String USER_URL = "https://tbmanagement-aff8e.firebaseio.com/users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        mListView = (ListView) findViewById(R.id.user_list_ID);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(USER_URL);

        FirebaseListAdapter<String> listAdapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                android.R.layout.simple_expandable_list_item_1,
                databaseReference
        ) {
            @Override
            protected void populateView(View v, String model, int position) {

            }
        };


    }
}
