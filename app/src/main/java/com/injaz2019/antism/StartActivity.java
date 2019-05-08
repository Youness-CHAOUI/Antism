package com.injaz2019.antism;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    /*Variables*/
    TextView tv_ca, tv_a;
    View v_ca, v_a;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        MobileAds.initialize(this, getResources().getString(R.string.admob_app_id));
        adView = findViewById(R.id.adView_1);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(getResources().getString(R.string.test_device_id)).build();
        adView.loadAd(adRequest);

        tv_ca = findViewById(R.id.tv_childAssistant);
        tv_a = findViewById(R.id.tv_assitant);
        v_ca = findViewById(R.id.v_childAssistant);
        v_a = findViewById(R.id.v_assitant);


        tv_ca.setOnClickListener(this);
        tv_a.setOnClickListener(this);
        v_ca.setOnClickListener(this);
        v_a.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_childAssistant:
                startActivity(new Intent(this, childAssitantActivity.class));
                break;
            case R.id.v_childAssistant:
                startActivity(new Intent(this, childAssitantActivity.class));
                break;
            case R.id.tv_assitant:
                startActivity(new Intent(this, AssitantActivity.class));
                break;
            case R.id.v_assitant:
                startActivity(new Intent(this, AssitantActivity.class));
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
