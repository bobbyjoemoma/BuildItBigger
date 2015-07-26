package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.tim.backend.myApi.MyApi;
import com.tim.joketeller.JokeTeller;

import java.io.IOException;

/**
 * Created by TIM on 7/25/2015.
 */
public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private Callback mCallback;

    EndpointsAsyncTask(Callback callback){
        mCallback = callback;
    }

    public interface Callback{
        void onEndpointsAsyncTackResponse(String result);
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://builditbigger-1017.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getJoke();
        } catch (IOException e) {
            Log.e("error in GCE",e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mCallback.onEndpointsAsyncTackResponse(result);
    }
}