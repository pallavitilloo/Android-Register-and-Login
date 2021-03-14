package com.example.register_tilloop1_login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/************************************************************************************
 *
 *  The Splash activity class is the first to be launched. It starts the Splash activity
 *  and runs for the number of milliseconds passed as argument delayMillis
 *
 * @author Pallavi Tilloo
 * https://github.com/pallavitilloo/AndroidRegisterLogin
 *
 *************************************************************************************/

public class SplashActivity extends Activity {

    Handler handler;
    final int DELAY = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },DELAY);   //This will show the Splash screen for 5000 milliseconds

    }
}