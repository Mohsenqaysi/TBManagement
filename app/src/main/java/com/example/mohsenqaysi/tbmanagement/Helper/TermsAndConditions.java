package com.example.mohsenqaysi.tbmanagement.Helper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
        checkBox.setOnClickListener(this);

        webView = (WebView) findViewById(R.id.webView_ID);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String   failingUrl) {

            }
        });
        webView.loadUrl("http://mohsenqaysi.github.io/webpage/Terms.html");
//        webView.loadUrl("http://www.ucd.ie/cookie-policy/");


//        String summary = "";
//        webView.loadData(summary, "text/html", null);

    }








    @Override
    public void onClick(View view) {
        if (view == agree && checkBox.isChecked()){
            // TODO: save date
        } else {
            // TODO: do something
        }
    }


}
