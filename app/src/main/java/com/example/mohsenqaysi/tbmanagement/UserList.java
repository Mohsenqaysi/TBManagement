package com.example.mohsenqaysi.tbmanagement;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserList extends AppCompatActivity {

    private ListView mListView;
    TextView textView;
    private String USER_URL = "https://tbmanagement-aff8e.firebaseio.com/users";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        mListView = (ListView) findViewById(R.id.user_list_ID);
//        progressDialog = new ProgressDialog(this);
//        progressDialog.show();


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(USER_URL);
        databaseReference.keepSynced(true); // keep the offline data up to date

        FirebaseListAdapter<String> listAdapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                android.R.layout.simple_expandable_list_item_1,
                databaseReference
        ) {
            @Override
            protected void populateView(View v, String model, int position) {
                textView = (TextView) v.findViewById(android.R.id.text1);
                textView.setText(model);
            }
        };


        // pass the data to the adapter
        mListView.setAdapter(listAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String ItemSelectedFromList = (String) mListView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"You clicked on -> " + ItemSelectedFromList,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
