package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Tweet {
    public String body;
    public String uid;
    public String createdAt;
    public User user;
    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException {
        Tweet tweet     = new Tweet();
        tweet.body      = jsonObject.getString("text");
        tweet.uid       = jsonObject.getString("id");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user      = User.fromJson(jsonObject.getJSONObject("user"));
        return tweet;
    }
}
