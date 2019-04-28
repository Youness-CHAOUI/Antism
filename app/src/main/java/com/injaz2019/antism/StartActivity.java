package com.injaz2019.antism;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    /*Variables*/
    TextView tv_ca, tv_a;
    View v_ca, v_a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

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
    public void onClick(View v)
    {
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
}
