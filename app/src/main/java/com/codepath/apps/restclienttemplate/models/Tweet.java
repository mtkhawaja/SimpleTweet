package com.codepath.apps.restclienttemplate.models;

import com.codepath.apps.restclienttemplate.helper.TimeFormatter;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Tweet {
    public String body;
    public String uid;
    public String createdAt;
    public String relativeTime;
    public User user;

    public Tweet(){//For Parceler
    }

    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException {
        Tweet tweet     = new Tweet();
        tweet.body      = jsonObject.getString("text");
        tweet.uid       = jsonObject.getString("id");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.relativeTime  = getFormattedCreatedAt(tweet.createdAt);
        tweet.user      = User.fromJson(jsonObject.getJSONObject("user"));
        return tweet;
    }

    static public String getFormattedCreatedAt(String createdAt) {
        String formattedTime = TimeFormatter.getTimeDifference(createdAt);
        return formattedTime ;
    }
}
