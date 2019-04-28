package com.injaz2019.antism;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.injaz2019.antism.classes.Adapters.rv_routineAdapter;
import com.injaz2019.antism.classes.Database.myDBHelper;
import com.injaz2019.antism.classes.Metier.Routine;

import java.util.ArrayList;

public class RoutineListActivity extends AppCompatActivity {

    myDBHelper dbHelper;
    RecyclerView rv_routines;
    rv_routineAdapter routinesAdapter;
    AlertDialog popupAddClass;
    ArrayList<Routine> routinesListe = new ArrayList<Routine>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new myDBHelper(this);
        rv_routines = (RecyclerView) findViewById(R.id.rv_routines);
        rv_routines.setLayoutManager(new LinearLayoutManager(this, 1, false));
//        rv_routines.setLayoutManager(new GridLayoutManager(this, 2));
//        rv_routines.setLayoutManager(new GridLayoutManager(this, 1, 0, false));

        routinesAdapter = new rv_routineAdapter(dbHelper.getAllRoutines());
        rv_routines.setAdapter(routinesAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivityForResult(new Intent(RoutineListActivity.this, createTaskActivity.class), 1);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                routinesAdapter = new rv_routineAdapter(dbHelper.getAllRoutines());
                rv_routines.setAdapter(routinesAdapter);
            }
        }
    }
}
