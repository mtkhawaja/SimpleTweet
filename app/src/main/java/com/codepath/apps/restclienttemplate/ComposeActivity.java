package com.codepath.apps.restclienttemplate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {
    private EditText etCompose;
    private Button btnTweet;
    private TextView charCount;
    private TwitterClient client;
    private static final int MAX_TWEET_LENGTH = 140;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        etCompose   = findViewById(R.id.etCompose);
        btnTweet    = findViewById(R.id.btnTweet );
        charCount   = findViewById(R.id.charCount);

        client      = TwitterApplication.getRestClient(this);
        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tweetContent = etCompose.getText().toString();
                // Error Handling
                if(tweetContent.isEmpty()) {
                    Toast.makeText(ComposeActivity.this, "Your Tweet is Empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (tweetContent.length() > MAX_TWEET_LENGTH) {
                    Toast.makeText(ComposeActivity.this, "Your Tweet is to Long!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //If tweet is alright, make API call.
                Toast.makeText(ComposeActivity.this,etCompose.getText(), Toast.LENGTH_SHORT).show();
                client.composeTweet(tweetContent, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Log.d("tweet-success", "tweet was a success" + response.toString());
                        try {
                            Tweet tweet = Tweet.fromJSON(response);
                            Intent data = new Intent();
                            data.putExtra("tweet", Parcels.wrap(tweet));
                            setResult(RESULT_OK);
                            // Closes activity
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Log.e("tweet-Failed", "POSTing failed" + errorResponse.toString());
                    }
                });

            } // OnClick Override
        }); // btnTweet.setOnClickListener

        etCompose.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                charCount.setText(Integer.toString(140 - etCompose.getText().length())) ;
            }
        });


    } // On-Create
} // ComposeActivity Class
