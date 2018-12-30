package com.svit.epolice.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.svit.epolice.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

public class TwitterActivity extends AppCompatActivity {

    private RecyclerView userTimelineRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TweetTimelineRecyclerViewAdapter adapter;

    private WebView twitterWebView;
    private WebSettings twitterWebSettings;
    private static final String HTML = "<a class=\"twitter-timeline\" href=\"https://twitter.com/Vadcitypolice?ref_src=twsrc%5Etfw\">Tweets by Vadcitypolice</a> <script async src=\"https://platform.twitter.com/widgets.js\" charset=\"utf-8\"></script>";
    private static final String MIME_TYPE = "text/html";
    private static final String ENCODING = "UTF-8";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);
        swipeRefreshLayout = findViewById(R.id.user_swipe_refresh_layout);
        userTimelineRecyclerView = findViewById(R.id.user_timeline_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);//it should be Vertical only
        userTimelineRecyclerView.setLayoutManager(linearLayoutManager);
        UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("Vadcitypolice")//any screen name
                .includeReplies(true)//Whether to include replies. Defaults to false.
                .includeRetweets(true)//Whether to include re-tweets. Defaults to true.
                .maxItemsPerRequest(50)//Max number of items to return per request
                .build();

        adapter = new TweetTimelineRecyclerViewAdapter.Builder(this)
                .setTimeline(userTimeline)//set the created timeline
                //action callback to listen when user like/unlike the tweet
                .setOnActionCallback(new Callback<Tweet>() {
                    @Override
                    public void success(Result<Tweet> result) {
                        //do something on success response
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        //do something on failure response
                    }
                })
                //set tweet view style
                .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                .build();

        //finally set the created adapter to recycler view
        userTimelineRecyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                //return if adapter is null
                if (adapter == null)
                    return;

                //make set refreshing true
                swipeRefreshLayout.setRefreshing(true);
                adapter.refresh(new Callback<TimelineResult<Tweet>>() {
                    @Override
                    public void success(Result<TimelineResult<Tweet>> result) {
                        //on success response make refreshing false
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(TwitterActivity.this, "Tweets refreshed.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        // Toast or some other action
                        Toast.makeText(TwitterActivity.this, "Failed to refresh tweets.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
/*
        twitterWebView = findViewById(R.id.twitterWebView);
        twitterWebSettings = twitterWebView.getSettings();
        twitterWebSettings.setJavaScriptEnabled(true);
        twitterWebSettings.setLoadsImagesAutomatically(true);
        twitterWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        twitterWebView.setWebViewClient(new WebViewClient());
//        twitterWebView.loadUrl("https://twitter.com/Vadcitypolice");
//twitterWebView.loadUrl("https://twitter.com/TwitterDev?ref_src=twsrc%5Etfw");
        twitterWebView.loadData(HTML, MIME_TYPE, ENCODING);*/
}
