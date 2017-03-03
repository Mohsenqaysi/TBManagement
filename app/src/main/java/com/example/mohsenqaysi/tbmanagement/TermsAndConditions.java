package com.example.mohsenqaysi.tbmanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TermsAndConditions extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);

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
}
