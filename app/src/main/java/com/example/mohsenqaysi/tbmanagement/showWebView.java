package com.example.mohsenqaysi.tbmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class showWebView extends AppCompatActivity {

    private ListView Info_ListView;
    TextView textView;
    private String FIREBASE_URL_PATH = "https://tbmanagement-aff8e.firebaseio.com/info";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_web_view);
//        webView = (WebView) findViewById(R.id.webView_ID);
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webView.loadUrl(url);
//        webView.setWebViewClient(new WebViewClient());



        /* TODO: Read data from a local JSON file into a string
           TODO: populate a list view with Title as the cell name and
           TODO pass the description in and intent put extra to another view for the user to read it
        */


        Info_ListView = (ListView) findViewById(R.id.Info_ListView_ID);

        final DatabaseReference  ref = FirebaseDatabase.getInstance().getReferenceFromUrl(FIREBASE_URL_PATH);
        ref.keepSynced(true);

        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                android.R.layout.simple_list_item_1,
                ref
        ) {
            @Override
            protected void populateView(View v, String model, int position) {
                textView = (TextView) v.findViewById(android.R.id.text1);
                // Get the key of the item at @position
                DatabaseReference itemRef = getRef(position);
                String itemKey = itemRef.getKey();
                textView.setText(itemKey);
            }
        };


        // pass the data to the adapter
        Info_ListView.setAdapter(firebaseListAdapter);

        Info_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedFromList = (String) Info_ListView.getItemAtPosition(position);
//                Toast.makeText(getApplicationContext(),"You clicked on -> " + itemSelectedFromList,Toast.LENGTH_SHORT).show();

                Log.w("Info: ", itemSelectedFromList);
                // GOTO the info activity to show more detail about the TB
//                startActivity(new Intent(showWebView.this,TBINFO.class).putExtra("info",itemSelectedFromList));
                Intent intent = new Intent(getApplicationContext(), TBINFO.class);
                intent.putExtra("info", itemSelectedFromList);  // pass your values and retrieve them in the other Activity using keyName
                startActivity(intent);
            }
        });

    }
}
