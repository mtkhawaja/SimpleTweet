package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class User {
    public String name;
    public Long uid;
    public String screenName;
    public String profileImageURL;

    public User (){//parceler
    }

    public static User fromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.name               = jsonObject.getString("name");
        user.uid                = jsonObject.getLong("id");
        user.screenName         = jsonObject.getString("screen_name");

        // Will not work unless https Key is used. If it is not used, the correct image URL is loaded, however it does not load in the ImageView.
        user.profileImageURL    = jsonObject.getString("profile_image_url_https");
        return user;
    }
}
