package com.svit.epolice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TwitterActivity extends AppCompatActivity {

    private WebView twitterWebView;
    private WebSettings twitterWebSettings;
    private static final String HTML = "<a class=\"twitter-timeline\" href=\"https://twitter.com/Vadcitypolice?ref_src=twsrc%5Etfw\">Tweets by Vadcitypolice</a> <script async src=\"https://platform.twitter.com/widgets.js\" charset=\"utf-8\"></script>";
    private static final String MIME_TYPE = "text/html";
    private static final String ENCODING = "UTF-8";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);
        twitterWebView = findViewById(R.id.twitterWebView);
        twitterWebSettings = twitterWebView.getSettings();
        twitterWebSettings.setJavaScriptEnabled(true);
        twitterWebSettings.setLoadsImagesAutomatically(true);
        twitterWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        twitterWebView.setWebViewClient(new WebViewClient());
//        twitterWebView.loadUrl("https://twitter.com/Vadcitypolice");
//twitterWebView.loadUrl("https://twitter.com/TwitterDev?ref_src=twsrc%5Etfw");
        twitterWebView.loadData(HTML, MIME_TYPE, ENCODING);
    }
}
