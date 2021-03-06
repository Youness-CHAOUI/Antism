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

public class AssitantActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_experts, tv_parents;
    View v_experts, v_parents;
    AdView adView;

    PublisherInterstitialAd mPublisherInterstitialAd, mPublisherInterstitialAd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assitant);

        MobileAds.initialize(this, getResources().getString(R.string.admob_app_id));
        adView = findViewById(R.id.adView_2);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(getResources().getString(R.string.test_device_id)).build();
        adView.loadAd(adRequest);

        mPublisherInterstitialAd = new PublisherInterstitialAd(this);
        mPublisherInterstitialAd.setAdUnitId(getResources().getString(R.string.admob_interstitial2_key));
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
                Log.i("***", "The interstitial 1 is ended!");
                /*Move to the experts activity*/
                Intent i = new Intent(AssitantActivity.this, VideosListActivity.class);
                i.putExtra("TYPE", "EXPERTS");
                startActivityForResult(i, 1);
            }
        });

        mPublisherInterstitialAd2 = new PublisherInterstitialAd(this);
        mPublisherInterstitialAd2.setAdUnitId(getResources().getString(R.string.admob_interstitial3_key));
        PublisherAdRequest publisherAdRequest2 = new PublisherAdRequest.Builder()
                .addTestDevice(getResources().getString(R.string.test_device_id))
                .build();
        mPublisherInterstitialAd2.loadAd(publisherAdRequest2);
        mPublisherInterstitialAd2.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mPublisherInterstitialAd2.loadAd(new PublisherAdRequest.Builder()
                        .addTestDevice(getResources().getString(R.string.test_device_id))
                        .build());
                Log.i("***", "The interstitial 2 is ended!");
                /*Move to the parents activity*/
                Intent ii = new Intent(AssitantActivity.this, VideosListActivity.class);
                ii.putExtra("TYPE", "PARENTS");
                startActivityForResult(ii, 1);
            }
        });

        tv_experts = findViewById(R.id.tv_experts);
        v_experts = findViewById(R.id.v_experts);
        tv_parents = findViewById(R.id.tv_parents);
        v_parents = findViewById(R.id.v_parents);

        tv_experts.setOnClickListener(this);
        v_experts.setOnClickListener(this);
        tv_parents.setOnClickListener(this);
        v_parents.setOnClickListener(this);
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
                    Snackbar.make(tv_experts, R.string._error_vid_load, Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_experts:
            case R.id.v_experts:
                if (mPublisherInterstitialAd.isLoaded()) {
                    mPublisherInterstitialAd.show();
                    Log.i("***", "The interstitial 1 is displayed.");
                } else {
                    Log.i("***", "The interstitial 1 wasn't loaded yet.");
                }
                break;
            case R.id.tv_parents:
            case R.id.v_parents:
                if (mPublisherInterstitialAd2.isLoaded()) {
                    mPublisherInterstitialAd2.show();
                    Log.i("***", "The interstitial 2 is displayed.");
                } else {
                    Log.i("***", "The interstitial 2 wasn't loaded yet.");
                }
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
