package com.injaz2019.antism;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.injaz2019.antism.classes.Adapters.rv_routineAdapter;
import com.injaz2019.antism.classes.Metier.Routine;

import java.util.ArrayList;

public class RoutineListActivity extends AppCompatActivity {

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

        rv_routines = (RecyclerView) findViewById(R.id.rv_routines);
        rv_routines.setLayoutManager(new LinearLayoutManager(this, 1, false));
//        routinesAdapter =new rv_routineAdapter(database.getAllClasses());
        routinesListe.add(new Routine(1, "11-22-2222", "This is for Ahmad"));
        routinesListe.add(new Routine(2, "03-09-2134", "This is for Laila"));
        routinesListe.add(new Routine(3, "03-09-2134", "تانتبيا سيبااتسي تسيتبسيب سيبنت"));
        routinesListe.add(new Routine(3, "03-09-2134", "This is for Youness"));
        routinesListe.add(new Routine(3, "03-09-2134", "This is for Youness"));
        routinesListe.add(new Routine(3, "03-09-2134", "This is for Youness"));
        routinesListe.add(new Routine(3, "03-09-2134", "This is for Youness"));
        routinesListe.add(new Routine(3, "03-09-2134", "This is for Youness"));
        routinesListe.add(new Routine(3, "03-09-2134", "This is for Youness"));
        routinesListe.add(new Routine(3, "03-09-2134", "This is for Youness"));
        routinesListe.add(new Routine(3, "03-09-2134", "This is for Youness"));
        routinesListe.add(new Routine(3, "03-09-2134", "This is for Youness"));
        routinesListe.add(new Routine(3, "03-09-2134", "This is for Youness"));
        routinesAdapter = new rv_routineAdapter(routinesListe);
        rv_routines.setAdapter(routinesAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(RoutineListActivity.this, createTaskActivity.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
