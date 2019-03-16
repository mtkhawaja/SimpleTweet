package com.codepath.apps.restclienttemplate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcel;
import org.parceler.Parcels;


public class DetailActivity extends AppCompatActivity {
    /********************************** Instance Variables **************************************/

    private Tweet tweet;
    ImageView    detailProfileImage  ;
    TextView     detailScreenName    ;
    TextView     detailName          ;
    TextView     detailBody          ;
    TextView     detailCreatedAt     ;
    TextView     retweetCout         ;
    TextView     favCount            ;
    public  DetailActivity(){}
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));

        this.detailProfileImage  =    findViewById(R.id.detailProfileImage  );
        this.detailScreenName    =    findViewById(R.id.detailScreenName    );
        this.detailName          =    findViewById(R.id.detailName          );
        this.detailBody          =    findViewById(R.id.detailBody          );
        this.detailCreatedAt     =    findViewById(R.id.detailCreatedAt     );
        this.favCount            =    findViewById(R.id.fav_count           );
        this.retweetCout         =    findViewById(R.id.retweetCount        );
        bind();

    }// On Create

    @SuppressLint("SetTextI18n")
    private void bind (){
        String imgURL = tweet.user.profileImageURL;
        detailScreenName.setText(tweet.user.screenName);
        detailName.setText      (tweet.user.name);
        detailBody.setText      (tweet.body);
        detailCreatedAt.setText (tweet.relativeTime);
        favCount.setText        ("This tweet has been favorited " + tweet.favoriteCount + " times");
        retweetCout.setText     ("This tweet has been retweeted " + tweet.retweetCount  + " times");
        Glide.with(this)
                .load(imgURL)
                .into(detailProfileImage);
    }

}// Activity
