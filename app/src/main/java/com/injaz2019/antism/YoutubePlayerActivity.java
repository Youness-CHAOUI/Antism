package com.injaz2019.antism;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.injaz2019.antism.classes.Utils.YoutubeApiConfig;

public class YoutubePlayerActivity extends YouTubeBaseActivity {

    @SuppressLint("InlinedApi")
    private static final int PORTRAIT_ORIENTATION = Build.VERSION.SDK_INT < 9
            ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            : ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;

    @SuppressLint("InlinedApi")
    private static final int LANDSCAPE_ORIENTATION = Build.VERSION.SDK_INT < 9
            ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            : ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
    YouTubePlayerView youtube_view;
    YouTubePlayer.OnInitializedListener listener;
    String _video_ID;
    private YouTubePlayer mPlayer = null;
    private boolean mAutoRotation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);

        youtube_view = findViewById(R.id.youtube_view);
        mAutoRotation = Settings.System.getInt(getContentResolver(),
                Settings.System.ACCELEROMETER_ROTATION, 0) == 1;
        _video_ID = getIntent().getExtras().getString("VID_ID");

        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                /*Load video*/
                mPlayer = youTubePlayer;

                if (mAutoRotation) {
                    youTubePlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION
                            | YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI
                            | YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE
                            | YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
                } else {
                    youTubePlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION
                            | YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI
                            | YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
                }

                youTubePlayer.loadVideo(_video_ID);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                /*If the initialisation is failed*/
//                Bundle conData = new Bundle();
//                conData.putString("param_result", "Thanks Thanks");
                Intent intent = new Intent();
//                intent.putExtras(conData);
                setResult(RESULT_OK, intent);
                finish();
                Log.i("***", "FAILED");
            }
        };

        youtube_view.initialize(YoutubeApiConfig.getApiKey(), listener);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (mPlayer != null)
                mPlayer.setFullscreen(true);
        }
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (mPlayer != null)
                mPlayer.setFullscreen(false);
        }
    }

    public void onFullscreen(boolean fullsize) {
        if (fullsize) {
            setRequestedOrientation(LANDSCAPE_ORIENTATION);
        } else {
            setRequestedOrientation(PORTRAIT_ORIENTATION);
        }
    }


}
