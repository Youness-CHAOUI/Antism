package com.injaz2019.antism;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.injaz2019.antism.classes.Utils.YoutubeApiConfig;

public class AssitantActivity extends AppCompatActivity {

    TextView tv_experts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assitant);

        tv_experts = findViewById(R.id.tv_experts);
        tv_experts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AssitantActivity.this, YoutubePlayerActivity.class);
                i.putExtra("VID_ID", YoutubeApiConfig.parentsVideos.get(0).getPath());
                startActivityForResult(i, 1);
            }
        });
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
}
