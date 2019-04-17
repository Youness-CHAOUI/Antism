package com.injaz2019.antism;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*Variables*/
    Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        startActivity(new Intent(this, StartActivity.class));
        this.finish();
    }

}
