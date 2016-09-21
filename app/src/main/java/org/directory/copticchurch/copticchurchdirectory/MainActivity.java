package org.directory.copticchurch.copticchurchdirectory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.onesignal.OneSignal;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private WebView mywebView;
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OneSignal.startInit(this)
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                .init();
        setContentView(R.layout.activity_main);

        Toolbar my_toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);
        //Action bar
        getSupportActionBar().setTitle(R.string.title);


        //webview for Coptic Church Directory Site
        mywebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mywebView.setWebViewClient(new WebViewClient());
        mywebView.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);
                findViewById(R.id.progress1).setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                findViewById(R.id.progress1).setVisibility(View.GONE);
                //hide loading image
                findViewById(R.id.imageView).setVisibility(View.GONE);
                //show webview
                findViewById(R.id.webView).setVisibility(View.VISIBLE);
            }
        });
        mywebView.loadUrl("http://copticchurch-directory.org/");


    }

    @Override
    public void onBackPressed() {
        if(mywebView.canGoBack()) {
            mywebView.goBack();
        } else {
            super.onBackPressed();
        }




    } @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_item_share) {
            doShare();
        }

        if (id == R.id.menu_settings) {

            Intent intent = new Intent(this, AckActivity.class);
            startActivity(intent);

            return true;
        }


        return super.onOptionsItemSelected(item);


    }



    private void doShare(){

        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Coptic Church Directory");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Find the church near you with Coptic Church Directory https://play.google.com/store/apps/details?id=org.directory.copticchurch.copticchurchdirectory ");

        startActivity(Intent.createChooser(shareIntent, "Share Coptic Church Directory to.."));




    }

    // This fires when a notification is opened by tapping on it or one is received while the app is running.
    private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        @Override
        public void notificationOpened(String message, JSONObject additionalData, boolean isActive) {

        }
    }   public void notificationOpened(String message, JSONObject additionalData, boolean isActive) {
        try {
            if (additionalData != null) {
                if (additionalData.has("actionSelected"))
                    Log.d("OneSignalExample", "OneSignal notification button with id " + additionalData.getString("actionSelected") + " pressed");

                Log.d("OneSignalExample", "Full additionalData:\n" + additionalData.toString());
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }



    }









}
