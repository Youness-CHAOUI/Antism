package com.injaz2019.antism;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.injaz2019.antism.classes.Adapters.rv_videoAdapter;
import com.injaz2019.antism.classes.Utils.YoutubeApiConfig;

public class VideosListActivity extends AppCompatActivity {

    RecyclerView rv_videos;
    rv_videoAdapter videoAdapter;
    private String _vids_type;

    private PublisherInterstitialAd mPublisherInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_list);

        MobileAds.initialize(this, getResources().getString(R.string.admob_app_id));
        mPublisherInterstitialAd = new PublisherInterstitialAd(this);
        mPublisherInterstitialAd.setAdUnitId(getResources().getString(R.string.admob_interstitial1_key));
        mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder()
                .addTestDevice(getResources().getString(R.string.test_device_id))
                .build());
        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder()
                        .addTestDevice(getResources().getString(R.string.test_device_id))
                        .build());
            }
        });

        _vids_type = getIntent().getExtras().getString("TYPE");

        rv_videos = (RecyclerView) findViewById(R.id.rv_videos);
        rv_videos.setLayoutManager(new LinearLayoutManager(this, 1, false));
//        rv_routines.setLayoutManager(new GridLayoutManager(this, 2));
//        rv_routines.setLayoutManager(new GridLayoutManager(this, 1, 0, false));

        switch (_vids_type) {
            case "EXPERTS":
                setTitle(R.string._experts);
                videoAdapter = new rv_videoAdapter(YoutubeApiConfig.expertsVideos);
                break;
            case "PARENTS":
                setTitle(R.string._parent);
                videoAdapter = new rv_videoAdapter(YoutubeApiConfig.parentsVideos);
                break;
            case "MODULAR":
                setTitle(R.string._videos);
                videoAdapter = new rv_videoAdapter(YoutubeApiConfig.modelisationVideos);
                break;
        }
        rv_videos.setAdapter(videoAdapter);
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
            case 22:
                Log.d("***", "The interstitial showen after back from video.");
//                if (mPublisherInterstitialAd.isLoaded()) {
//                    mPublisherInterstitialAd.show();
//                    Log.d("***", "The interstitial showen after back from video.");
//                } else {
//                    Log.d("***", "The interstitial wasn't loaded yet.");
//                }
                break;
            default:
                Log.i("***", "no");
                break;
        }
    }
}
