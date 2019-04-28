package com.injaz2019.antism;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.injaz2019.antism.classes.Database.myDBHelper;

public class RoutineDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    myDBHelper dbHelper;
    int ID_ROUTINE;
    Intent _intent;

    CardView l_morning, l_night;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_details);

        dbHelper = new myDBHelper(this);
        ID_ROUTINE = getIntent().getExtras().getInt("idRoutine");
        _intent = new Intent(this, PeriodDetailsActivity.class);
        _intent.putExtra("ID_ROUTINE", ID_ROUTINE);

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
                _intent.putExtra("PERIOD", false);
                startActivity(_intent);
                break;
            case R.id.l_night:
                _intent.putExtra("PERIOD", true);
                startActivity(_intent);
                break;
        }
    }
}
