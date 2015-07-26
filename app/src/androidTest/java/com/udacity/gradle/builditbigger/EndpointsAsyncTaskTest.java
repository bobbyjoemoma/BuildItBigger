package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class EndpointsAsyncTaskTest extends AndroidTestCase implements EndpointsAsyncTask.Callback{
    private String expectedJoke = "A guy walks into a bar...";

    public EndpointsAsyncTaskTest() {
        new EndpointsAsyncTask(this).execute();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEndpointsAsyncTackResponse(String result) {
        assertNotNull("result is null",result);
        assertEquals("result is not expected",expectedJoke,result);
    }
}