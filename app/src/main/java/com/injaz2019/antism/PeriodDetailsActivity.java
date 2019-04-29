package com.injaz2019.antism;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.injaz2019.antism.classes.Adapters.rv_taskAdapter;
import com.injaz2019.antism.classes.Database.myDBHelper;
import com.injaz2019.antism.classes.Metier.Tache;

import java.util.ArrayList;

public class PeriodDetailsActivity extends AppCompatActivity {

    myDBHelper dbHelper;
    RecyclerView _rv_tasks;
    rv_taskAdapter _taskAdapter;
    ArrayList<Tache> tasksList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_details);

        dbHelper = new myDBHelper(this);
        _rv_tasks = (RecyclerView) findViewById(R.id.rv_tasks);
//        _rv_tasks.setLayoutManager(new LinearLayoutManager(this, 1, false));
        _rv_tasks.setLayoutManager(new GridLayoutManager(this, 2));
//        _rv_tasks.setLayoutManager(new GridLayoutManager(this, 1, 0, false));

        int ID_ROUTINE = getIntent().getExtras().getInt("ID_ROUTINE");
        boolean PERIOD = getIntent().getExtras().getBoolean("PERIOD");

        _taskAdapter = new rv_taskAdapter(dbHelper.getTachesByRoutineAndPeriod(ID_ROUTINE, PERIOD));
        _rv_tasks.setAdapter(_taskAdapter);
    }
}
