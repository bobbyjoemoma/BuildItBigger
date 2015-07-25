package com.tim.joketeller;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class JokeTeller extends AppCompatActivity {

    public static final String JOKE_STRING_EXTRA = "joke_string_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joketeller_main);
        this.setTitle(R.string.app_name);

        //retrieve intent
        Intent intent = getIntent();

        if (intent != null && intent.getStringExtra(JOKE_STRING_EXTRA) != null) {
            ((TextView) findViewById(R.id.joke_textview)).setText(intent.getStringExtra(JOKE_STRING_EXTRA));
        } else {
            ((TextView) findViewById(R.id.joke_textview)).setText(getString(R.string.joke_error));
        }

    }
}
