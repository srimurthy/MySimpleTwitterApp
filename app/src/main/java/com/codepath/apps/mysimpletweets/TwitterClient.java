package com.codepath.apps.mysimpletweets;

import android.content.Context;
import android.util.Log;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;
    public static final String REST_URL = "https://api.twitter.com/1.1";
    public static final String REST_CONSUMER_KEY = "6Ezary9aGVkcfjnbGN5Kxg";
    public static final String REST_CONSUMER_SECRET = "zY95qZJaHcdtpU3p8ErG15piesi4CSvSvDSWGxlj0c";
    public static final String REST_CALLBACK_URL = "oauth://cpsimpletweets";

    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }

    public void getAppUser(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("users/show.json");
        RequestParams params = new RequestParams();
        params.put("screen_name", "srinivasa_msb");
        client.get(apiUrl, params, handler);
    }

    public void getOwnTimeline(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/user_timeline.json");
        RequestParams params = new RequestParams();
        params.put("screen_name", "srinivasa_msb");
        params.put("count", 10);
        client.get(apiUrl, params, handler);
    }

    public void getHomeTimeline(AsyncHttpResponseHandler handler, int startIndex) {
        String apiUrl = getApiUrl("statuses/home_timeline.json");
        RequestParams params = new RequestParams();
        params.put("since_id", startIndex);
        params.put("count", 7);
        client.get(apiUrl, params, handler);
    }

    public void postTweet(AsyncHttpResponseHandler handler, String tweetText) {
        String apiUrl = getApiUrl("statuses/update.json");
        RequestParams params = new RequestParams();
        params.put("status",tweetText);
        Log.d("DEBUG", apiUrl + " *** "+ params);
        client.post(apiUrl, params, handler);
    }
}