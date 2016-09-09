package org.directory.copticchurch.copticchurchdirectory;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView mywebView;
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar my_toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);
        //Action bar
        getSupportActionBar().setTitle(R.string.title);
        //webview for Coptic Church Directory Site
        mywebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mywebView.loadUrl("http://copticchurch-directory.org/");
        mywebView.setWebViewClient(new WebViewClient());
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
        if (item.getItemId() == R.id.menu_item_share) {
            doShare();
        }
        return super.onOptionsItemSelected(item);
    }

    private void doShare(){

        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Coptic Church Directory");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Find the church near you with Coptic Church Directory https://play.google.com/store/apps/details?id=org.directory.copticchurch.copticchurchdirectory ");

        startActivity(Intent.createChooser(shareIntent, "Share myData to.."));


    }



}
