package org.directory.copticchurch.copticchurchdirectory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

      Thread mythread = new Thread(){
          @Override
          public void run(){
              try {
                  sleep(3500);
                  Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                  startActivity(intent);
                  finish();
              }catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      };
        mythread.start();


    }
}
