package com.injaz2019.antism;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;

public class childAssitantActivity extends AppCompatActivity implements View.OnClickListener {

    /*Variables*/
    TextView tv_vid, tv_routine;
    View v_vid, v_routine;
    AdView adView;

    private PublisherInterstitialAd mPublisherInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_assitant);

        MobileAds.initialize(this, getResources().getString(R.string.admob_app_id));
        adView = findViewById(R.id.adView_3);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(getResources().getString(R.string.test_device_id)).build();
        adView.loadAd(adRequest);

        mPublisherInterstitialAd = new PublisherInterstitialAd(this);
        mPublisherInterstitialAd.setAdUnitId(getResources().getString(R.string.admob_interstitial1_key));
        PublisherAdRequest publisherAdRequest = new PublisherAdRequest.Builder()
                .addTestDevice(getResources().getString(R.string.test_device_id))
                .build();
        mPublisherInterstitialAd.loadAd(publisherAdRequest);
        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder()
                        .addTestDevice(getResources().getString(R.string.test_device_id))
                        .build());
                Log.i("***", "The interstitial 0 is ended!");
                /*Move to the next activity*/
                Intent i = new Intent(childAssitantActivity.this, VideosListActivity.class);
                i.putExtra("TYPE", "MODULAR");
                startActivityForResult(i, 1);
            }
        });

        tv_vid = findViewById(R.id.tv_videos);
        tv_routine = findViewById(R.id.tv_routine);
        v_vid = findViewById(R.id.v_videos);
        v_routine = findViewById(R.id.v_routine);

        tv_vid.setOnClickListener(this);
        tv_routine.setOnClickListener(this);
        v_vid.setOnClickListener(this);
        v_routine.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_0, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quitter:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    finishAffinity();
                }
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Snackbar.make(v_vid, R.string._error_vid_load, Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_videos:
            case R.id.v_videos:
                if (mPublisherInterstitialAd.isLoaded()) {
                    mPublisherInterstitialAd.show();
                    Log.i("***", "The interstitial 0 is displayed.");
                } else {
                    Log.i("***", "The interstitial 0 wasn't loaded yet.");
                }
                break;
            case R.id.tv_routine:
            case R.id.v_routine:
                startActivity(new Intent(this, RoutineListActivity.class));
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (adView != null)
            adView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adView != null)
            adView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adView != null)
            adView.destroy();
    }
}
