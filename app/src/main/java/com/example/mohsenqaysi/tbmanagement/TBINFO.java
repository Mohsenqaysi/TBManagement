package com.example.mohsenqaysi.tbmanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TBINFO extends AppCompatActivity {

    private TextView info;
    private String description = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tbinfo);

        description = getIntent().getExtras().getString("info");
        info = (TextView) findViewById(R.id.tb_info_ID);


        if (description != null) {
            info.setText(description);
        } else {
            info.setText("Sorry there is no description for this topic!");

        }
    }
}
