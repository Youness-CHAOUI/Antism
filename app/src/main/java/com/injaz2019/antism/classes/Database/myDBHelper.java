package com.injaz2019.antism.classes.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.injaz2019.antism.classes.Metier.Routine;
import com.injaz2019.antism.classes.Metier.Tache;

import java.util.ArrayList;

import static com.injaz2019.antism.classes.Metier.Routine.ROUTINE_TABLE;
import static com.injaz2019.antism.classes.Metier.Tache.TACHE_TABLE;

/**
 * Created by CY_15 on 21/06/2018.
 */
public class myDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_mautism";
    SQLiteDatabase db;

    public myDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Routine.sqlCreateRoutine);
        db.execSQL(Tache.sqlCreateTache);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Routine.sqlDropRoutine);
        db.execSQL(Tache.sqlDropTache);
        onCreate(db);
    }


    /**
     * TACHE
     **/
    public Long addTacheToRoutine(Tache tache) {
        ContentValues values = new ContentValues();
        values.put("image", tache.getImage());
        values.put("audio", tache.getAudio());
        values.put("heure", tache.getHeure());
        values.put("period", tache.getPeriod());
        values.put("isDone", tache.getIsDone());
        values.put("rate", tache.getRate());
        values.put("id_routine", tache.getId_routine());

        // Insertion
        db = this.getWritableDatabase();
        Long id = db.insert(TACHE_TABLE, null, values);

        /*update routine*/
        Routine routine = findRoutineById(tache.getId_routine());
        if (tache.getPeriod())
            routine.setNbNightTasks(routine.getNbNightTasks() + 1);
        else
            routine.setNbMorningTasks(routine.getNbMorningTasks() + 1);
        ContentValues values2 = new ContentValues();
        values2.put("nbMorningTasks", routine.getNbMorningTasks());
        values2.put("nbNightTasks", routine.getNbNightTasks());
        db = this.getWritableDatabase();
        db.update(ROUTINE_TABLE, values2, "id=" + routine.getId(), null);

        db.close(); // Closing database connection

        Log.i("***tache", tache.toString());
        Log.i("***routine", routine.toString());
        return id;
    }

    public ArrayList<Tache> getAllTaches() {
        ArrayList<Tache> liste = new ArrayList<>();
        String query = "SELECT * FROM " + TACHE_TABLE;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Tache tache = new Tache(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        Boolean.parseBoolean(cursor.getString(4)),
                        Boolean.parseBoolean(cursor.getString(5)),
                        Integer.parseInt(cursor.getString(6)),
                        Integer.parseInt(cursor.getString(7))
                );
                liste.add(tache);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return liste;
    }

    public ArrayList<Tache> getTachesByRoutineAndPeriod(int id_routine, boolean period) {
        ArrayList<Tache> liste = new ArrayList<>();
        int _period = period ? 1 : 0;
        String query = "SELECT * FROM " + TACHE_TABLE + " WHERE id_routine = " + id_routine + " AND period = " + _period;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Tache tache = new Tache(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        Boolean.parseBoolean(cursor.getString(4)),
                        Boolean.parseBoolean(cursor.getString(5)),
                        Integer.parseInt(cursor.getString(6)),
                        Integer.parseInt(cursor.getString(7))
                );
                liste.add(tache);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return liste;
    }

    /**
     * ROUTINE
     **/
    public Long addRoutine(Routine routine) {
        ContentValues values = new ContentValues();
        //values.put("id", 000)
        values.put("date", routine.getDate());
        values.put("nbMorningTasks", routine.getNbMorningTasks());
        values.put("nbNightTasks", routine.getNbNightTasks());

        // Insertion
        db = this.getWritableDatabase();
        Long id = db.insert(ROUTINE_TABLE, null, values);
        db.close(); // Closing database connection

        return id;
    }

    public ArrayList<Routine> getAllRoutines() {
        ArrayList<Routine> liste = new ArrayList<>();
        String query = "SELECT * FROM " + ROUTINE_TABLE;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Routine routine = new Routine(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        Integer.parseInt(cursor.getString(2)),
                        Integer.parseInt(cursor.getString(3))
                );
                liste.add(routine);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return liste;
    }


    public int findRoutineByDate(String date) {
        int ID = -1;
        String query = "SELECT * FROM " + ROUTINE_TABLE + " WHERE date = '" + date + "'";

        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ID = Integer.parseInt(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return ID;
    }

    public Routine findRoutineById(int id) {
        Routine routine = null;
        String query = "SELECT * FROM " + ROUTINE_TABLE + " WHERE id = " + id;

        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                routine = new Routine(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        Integer.parseInt(cursor.getString(2)),
                        Integer.parseInt(cursor.getString(3))
                );
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return routine;
    }
}
