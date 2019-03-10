package com.codepath.apps.restclienttemplate;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.adapters.TweetAdapter;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.github.scribejava.core.model.Response;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {
    private TwitterClient client;
    private RecyclerView rv;
    private TweetAdapter tweetAdapter;
    private List<Tweet>  tweets;
    private SwipeRefreshLayout swipeContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        client = TwitterApplication.getRestClient(this);
        swipeContainer = findViewById(R.id.swipeContainer);
        rv = findViewById(R.id.rvTweets);
        tweets = new ArrayList<>();
        tweetAdapter = new TweetAdapter(this, tweets);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(tweetAdapter);
        populateHomeTimeline();

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("TwitterClient","content is being refreshed");
                populateHomeTimeline();
            }
        });
    }
    private void populateHomeTimeline(){
        client.getHomeTimeline(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                List <Tweet> tweetsToAdd = new ArrayList<>();
                for (int i = 0; i < response.length(); i++){
                    try {
                       JSONObject jsonTweetObject = response.getJSONObject(i);
                        Tweet tweet = Tweet.fromJSON(jsonTweetObject );
                        tweets.add(tweet);

                        tweetsToAdd.add(tweet);

                        tweetAdapter.notifyItemInserted(tweets.size());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                tweetAdapter.clear();
                tweetAdapter.addTweets(tweetsToAdd);
              //  Log.d("TwitterClient", response.toString());
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.e("JSON Array Err.", errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e("JSON Obj. Err. ", errorResponse.toString());
            }
        });
    }
}
