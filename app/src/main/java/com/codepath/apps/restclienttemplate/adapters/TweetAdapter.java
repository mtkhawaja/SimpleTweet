package com.codepath.apps.restclienttemplate.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetAdapter extends RecyclerView.Adapter <TweetAdapter.ViewHolder> {

    private Context         context ;
    private List <Tweet>     tweets ;

    public TweetAdapter(Context context, List<Tweet> tweets) {
        this.context    = context ;
        this.tweets     = tweets  ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Tweet tweet = tweets.get(position);
        String imgURL = tweet.user.profileImageURL;

        viewHolder.tvBody.setText(tweet.body);
        viewHolder.tvScreenName.setText(tweet.user.screenName);
        viewHolder.tvBody.setText(tweet.body);
        Glide.with(context)
                .load(imgURL)
                .into(viewHolder.ivProfileImage);
        // Toast.makeText(context, tweet.user.profileImageURL, Toast.LENGTH_SHORT).show();
    }

    public  void clear(){
        tweets.clear();
        notifyDataSetChanged();
    }

    public  void addTweets(List <Tweet> tweetsList){
        tweets.addAll(tweetsList);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return tweets.size();
    }

    /* ViewHolder Class */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView    ivProfileImage  ;
        public TextView     tvScreenName    ;
        public TextView     tvBody          ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivProfileImage =    itemView.findViewById(R.id.ivProfileImage);
            this.tvScreenName   =    itemView.findViewById(R.id.tvScreenName  );
            this.tvBody         =    itemView.findViewById(R.id.tvBody       );
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
