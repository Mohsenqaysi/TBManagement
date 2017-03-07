package com.example.mohsenqaysi.tbmanagement.Helper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.mohsenqaysi.tbmanagement.R;

public class TermsAndConditions extends AppCompatActivity implements View.OnClickListener {
    WebView webView;
    Button agree;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);

        agree = (Button) findViewById(R.id.termsAndConditions_ID);
        agree.setOnClickListener(this);
        checkBox = (CheckBox) findViewById(R.id.checkBox_ID);
        disableSignInButton();
        checkBox.setOnClickListener(this);

        webView = (WebView) findViewById(R.id.webView_ID);
// path /Users/mohsenqaysi/Documents/TBManagement/app/src/main/java/com/example/mohsenqaysi/tbmanagement/Helper/web.html
        webView.loadUrl("file:///android_asset/web.html");

    }

    @Override
    public void onClick(View view) {

        if (view == checkBox){

            if (checkBox.isChecked()){
                enableSignInButton();
            } else {
                disableSignInButton();
            }
        }
    }

    public void disableSignInButton() {
        agree.setClickable(false);
        agree.setAlpha(.5f);
    }

    public void enableSignInButton() {
        agree.setClickable(true);
        agree.setAlpha(1);
    }


}
