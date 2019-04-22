package com.injaz2019.antism;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class RoutineDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    CardView l_morning, l_night;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_details);

        l_morning = (CardView) findViewById(R.id.l_morning);
        l_night = (CardView) findViewById(R.id.l_night);

        l_morning.setOnClickListener(this);
        l_night.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        TextView textv = (TextView) findViewById(R.id.textView4);
//        textv.setShadowLayer(1, 0, 0, Color.BLACK);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.l_morning:
//                startActivity(new Intent(this, RoutineListActivity.class));
                Toast.makeText(this, "Morning", Toast.LENGTH_SHORT).show();
                break;
            case R.id.l_night:
//                startActivity(new Intent(this, RoutineListActivity.class));
                Toast.makeText(this, "Night", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
