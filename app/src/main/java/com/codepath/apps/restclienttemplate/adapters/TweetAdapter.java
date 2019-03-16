package com.codepath.apps.restclienttemplate.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.ComposeActivity;
import com.codepath.apps.restclienttemplate.DetailActivity;
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
        viewHolder.tvScreenName.setText("@" + tweet.user.screenName);
        viewHolder.tvBody.setText(tweet.body);
        viewHolder.createdAt.setText(tweet.relativeTime);
        viewHolder.name.setText(tweet.user.name);
        Glide.with(context)
                .load(imgURL)
                .into(viewHolder.ivProfileImage);
        // Toast.makeText(context, tweet.user.profileImageURL, Toast.LENGTH_SHORT).show();

        //Detailed Tweet View Activity
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailActivity.class);
                context.startActivity(i);
            }
        });
    } // On Bind View Holder

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
        ImageView    ivProfileImage  ;
        TextView     tvScreenName    ;
        TextView     name            ;
        TextView     tvBody          ;
        TextView     createdAt       ;
        RelativeLayout container;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivProfileImage =    itemView.findViewById(R.id.ivProfileImage);
            this.tvScreenName   =    itemView.findViewById(R.id.tvScreenName  );
            this.name           =    itemView.findViewById(R.id.name          );
            this.tvBody         =    itemView.findViewById(R.id.tvBody        );
            this.createdAt      =    itemView.findViewById(R.id.createdAt     );
            this.container      =    itemView.findViewById(R.id.tweet         );
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
