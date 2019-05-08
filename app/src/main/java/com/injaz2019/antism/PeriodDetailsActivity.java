package com.injaz2019.antism;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

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

        tasksList = dbHelper.getTachesByRoutineAndPeriod(ID_ROUTINE, PERIOD);
        _taskAdapter = new rv_taskAdapter(tasksList);
        _rv_tasks.setAdapter(_taskAdapter);
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
}
