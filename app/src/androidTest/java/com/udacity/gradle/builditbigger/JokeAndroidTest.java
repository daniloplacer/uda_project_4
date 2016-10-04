package com.udacity.gradle.builditbigger;


import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Pair;

import java.util.concurrent.CountDownLatch;

/**
 * Created by daniloplacer on 10/2/16.
 */

public class JokeAndroidTest extends AndroidTestCase {

    String mResult = null;
    Exception mError = null;
    CountDownLatch signal = null;

    public void testVerifyEndpoitsResponse() throws InterruptedException {

        EndpointsAsyncTask task = new EndpointsAsyncTask();

        task.setListner(new EndpointsAsyncTask.StringGetTaskListener(){
            @Override
            public void onComplete(String result, Exception e) {

                mResult = result;
                mError = e;
                signal.countDown();

            }
        }).execute(new Pair<Context, String>(getContext(), null));
        signal.await();

        boolean empty = false;
        if ((mResult != null) && (mResult.isEmpty())) {
            empty = true;
        }

        assertNotNull(mResult);
        assertTrue(!empty);
        assertNull(mError);

    }

    @Override
    protected void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }
}