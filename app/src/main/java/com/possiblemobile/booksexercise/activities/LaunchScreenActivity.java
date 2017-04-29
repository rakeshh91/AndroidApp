package com.possiblemobile.booksexercise.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.possiblemobile.booksexercise.R;


public class LaunchScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        launchAndOpenMain();
    }

    /* Thread to show launcher layout for 2.5 seconds */
    private void launchAndOpenMain(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(LaunchScreenActivity.this, MainActivity.class);
                LaunchScreenActivity.this.startActivity(i);
                LaunchScreenActivity.this.finish();
            }
        },2500);
    }
}


/*Improvemnets
- Try making UI without using View.VISIBle or any other visibitlity because developer when going through
code will find it difficult

- Implementation of Constraint layout for making the layout file fit to all kind of screen sizes

- Implementation of pagination when there are too much data in the response. For smoother transitions, implmenet in new thread

- Store only necessary data from the server response. Do not use the data which are not required for the app.
This will save storage memory

- Make sure the data is update whenever we make a new request to the server.Data consistency has to be maintained else
will have conflicts with data since multiple versions of data wil be present

- Unregister / deactivate all listeners or connections before destroying the app. This helps prevent memory leakage and also
prevent unnecessary usage of memory in the backend

- Incase i allow this app to work on different orientation (apart from portrait mode) then i will have to handle fragment's
recreation by saving the instanceState before its recreation.

 */