package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tim.JokeSupplier;
import com.tim.joketeller.JokeTeller;

public class MainActivity extends FragmentActivity implements EndpointsAsyncTask.Callback{
    private JokeSupplier jokeSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jokeSupplier = new JokeSupplier();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
        new EndpointsAsyncTask(this).execute();
        Toast.makeText(this, jokeSupplier.getJoke(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onEndpointsAsyncTackResponse(String result) {
        if(result == null){
            Toast.makeText(this, "error processing joke request", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, JokeTeller.class);
            intent.putExtra(JokeTeller.JOKE_STRING_EXTRA, result);
            this.startActivity(intent);
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        }
    }
}
