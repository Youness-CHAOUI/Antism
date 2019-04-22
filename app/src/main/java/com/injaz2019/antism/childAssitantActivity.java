package com.injaz2019.antism;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class childAssitantActivity extends AppCompatActivity implements View.OnClickListener {

    /*Variables*/
    TextView tv_vid, tv_routine;
    View v_vid, v_routine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_assitant);

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_videos:
                startActivity(new Intent(this, modularVideosActivity.class));
                break;
            case R.id.v_videos:
                startActivity(new Intent(this, modularVideosActivity.class));
                break;
            case R.id.tv_routine:
                startActivity(new Intent(this, RoutineListActivity.class));
                break;
            case R.id.v_routine:
                startActivity(new Intent(this, RoutineListActivity.class));
                break;
        }
    }
}
