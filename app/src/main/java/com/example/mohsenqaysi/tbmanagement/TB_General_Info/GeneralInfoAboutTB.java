package com.example.mohsenqaysi.tbmanagement.TB_General_Info;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.mohsenqaysi.tbmanagement.R;

public class GeneralInfoAboutTB extends AppCompatActivity {

    private TextView info;
    private TextView title_text;
    private String title = "";
    private String description = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_info_about_tb);

        title = getIntent().getExtras().getString("title");
        description = getIntent().getExtras().getString("info");
        title_text = (TextView) findViewById(R.id.InfoTitle_ID);
        info = (TextView) findViewById(R.id.tb_info_ID);

        info.setMovementMethod(new ScrollingMovementMethod());
        if (description != null) {
            info.setText(description);
            title_text.setText(title);

        } else {
            info.setText("Sorry there is no description for this topic!");
            title_text.setText("Sorry there is no title for this topic!");

        }
    }
}
