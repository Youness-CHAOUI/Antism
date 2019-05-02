package com.injaz2019.antism;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AssitantActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_experts, tv_parents;
    View v_experts, v_parents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assitant);

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
                Intent i = new Intent(AssitantActivity.this, VideosListActivity.class);
                i.putExtra("TYPE", "EXPERTS");
                startActivityForResult(i, 1);
                break;
            case R.id.tv_parents:
            case R.id.v_parents:
                Intent ii = new Intent(AssitantActivity.this, VideosListActivity.class);
                ii.putExtra("TYPE", "PARENTS");
                startActivityForResult(ii, 1);
                break;
        }
    }
}
