package com.example.mohsenqaysi.tbmanagement.TB_General_Info;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mohsenqaysi.tbmanagement.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GeneralInfoAboutTBList extends AppCompatActivity {

    private ListView Info_ListView;
    private TextView TB_TopicsTitles;
    private DatabaseReference  ref;
    private String FIREBASE_URL_PATH = "https://tbmanagement-aff8e.firebaseio.com/info";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_info_about_tb_list);


        Info_ListView = (ListView) findViewById(R.id.Info_ListView_ID);


        ref = FirebaseDatabase.getInstance().getReferenceFromUrl(FIREBASE_URL_PATH);
        ref.keepSynced(true);

        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                android.R.layout.simple_list_item_1,
                ref
        ) {
            @Override
            protected void populateView(View v, String model, int position) {
                TB_TopicsTitles = (TextView) v.findViewById(android.R.id.text1);
                // Get the key of the item at @position
                DatabaseReference itemRef = getRef(position);
                String itemKey = itemRef.getKey();
                TB_TopicsTitles.setText(itemKey);
            }
        };


        // pass the data to the adapter
        Info_ListView.setAdapter(firebaseListAdapter);

        Info_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String itemSelectedFromListTitle = ((TextView)view).getText().toString();
                String itemSelectedFromListDescription = (String) Info_ListView.getItemAtPosition(position);

                Log.w("title: ", itemSelectedFromListTitle);
                Log.w("Info: ", itemSelectedFromListDescription);

                // GOTO the info activity to show more detail about the TB
                Intent intent = new Intent(getApplicationContext(), GeneralInfoAboutTB.class);
                intent.putExtra("title",itemSelectedFromListTitle);
                intent.putExtra("info", itemSelectedFromListDescription);  // pass your values and retrieve them in the other Activity using keyName
                startActivity(intent);
            }
        });


    }
}
