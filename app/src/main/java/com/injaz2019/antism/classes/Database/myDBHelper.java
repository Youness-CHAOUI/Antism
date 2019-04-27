package com.injaz2019.antism.classes.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.injaz2019.antism.classes.Metier.Routine;

/**
 * Created by CY_15 on 21/06/2018.
 */
public class myDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_gestion_absence";
    private static final String TABLE_CLASSE = "Classe";
    private static final String TABLE_Etudiant = "Etudiant";
    private static final String TABLE_Matiere = "Matiere";
    private static final String TABLE_LISTEABSENCE = "ListeAbsence";
    private static final String TABLE_PRESENCE = "Presence";
    SQLiteDatabase db;

    public myDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Routine.sqlCreateRoutine);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Routine.sqlDropRoutine);
        onCreate(db);
    }

    /** AUTRES FONCTIONS **/
//
//    /** CLASSE **/
//    public Long ajouterClasse(Classe classe)
//    {
//        ContentValues values = new ContentValues();
//        //values.put("id", 000)
//        values.put("libelle", classe.getLibelle());
//
//        // Insertion
//        db = this.getWritableDatabase();
//        Long id = db.insert(TABLE_CLASSE, null, values);
//        db.close(); // Closing database connection
//        return id;
//    }
//
//    public ArrayList<Classe> getAllClasses()
//    {
//        ArrayList<Classe> listeClasses = new ArrayList<Classe>();
//        String query = "SELECT  * FROM " + TABLE_CLASSE;
//        db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        if (cursor.moveToFirst())
//        {
//            do
//            {
//                Classe classe = new Classe();
//                classe.setId(Integer.parseInt(cursor.getString(0)));
//                classe.setLibelle(cursor.getString(1));
//                listeClasses.add(classe);
//            }
//            while (cursor.moveToNext());
//        }
//        db.close();
//        return listeClasses;
//    }
//
//
//    /** ETUDIANTS **/
//    public Long ajouterEtudiant(Etudiant Etudiant)
//    {
//        db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("cne", Etudiant.getCne());
//        values.put("prenom", Etudiant.getPrenom().substring(0,1).toUpperCase()+Etudiant.getPrenom().substring(1));
//        values.put("nom", Etudiant.getNom().toUpperCase());
//        values.put("email", Etudiant.getEmail());
//        values.put("id_classe", Etudiant.getId_classe());
//
//        // Insertion
//        Long id = db.insert(TABLE_Etudiant, null, values);
//        db.close(); // Closing database connection
//        return id;
//    }
//
//    public ArrayList<Etudiant> getAllEtudiantByClasse(int idClasse)
//    {
//        ArrayList<Etudiant> listeEtudiants = new ArrayList<>();
//        String query = "SELECT  * FROM " + TABLE_Etudiant+" WHERE id_classe = "+idClasse;
//
//        db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//
//        if (cursor.moveToFirst())
//        {
//            do {
//                Etudiant etudiant = new Etudiant();
//                etudiant.setCne(cursor.getString(0));
//                etudiant.setPrenom(cursor.getString(1));
//                etudiant.setNom(cursor.getString(2));
//                etudiant.setEmail(cursor.getString(3));
//                etudiant.setId_classe(cursor.getInt(4));
//
//                listeEtudiants.add(etudiant);
//            }
//            while (cursor.moveToNext());
//        }
//        db.close();
//        return listeEtudiants;
//    }
//
//    public Etudiant findEtudiantByCNE(String cne)
//    {
//        Etudiant etd = null;
//        String query = "SELECT  * FROM " + TABLE_Etudiant+" WHERE cne = "+cne;
//
//        db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//
//        if (cursor.moveToFirst())
//        {
//            do {
//                etd = new Etudiant();
//                etd.setCne(cursor.getString(0));
//                etd.setPrenom(cursor.getString(1));
//                etd.setNom(cursor.getString(2));
//                etd.setEmail(cursor.getString(3));
//                etd.setId_classe(cursor.getInt(4));
//            }
//            while (cursor.moveToNext());
//        }
//        db.close();
//        return etd;
//    }
//
//
//    /** MATIIERES **/
//    public Long ajouterMatiere(Matiere matiere)
//    {
//        db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        //values.put("id", matiere.getId());
//        values.put("libelle", matiere.getLibelle().substring(0,1).toUpperCase()+matiere.getLibelle().substring(1));
//        values.put("nbHeures", matiere.getNbHeures());
//        values.put("id_classe", matiere.getId_classe());
//
//        // Insertion
//        Long id = db.insert(TABLE_Matiere, null, values);
//        db.close(); // Closing database connection
//        return id;
//    }
//
//    public ArrayList<Matiere> getAllMatieresByClasse(int idClasse)
//    {
//        ArrayList<Matiere> listeMatieres = new ArrayList<>();
//        String query = "SELECT  * FROM " + TABLE_Matiere+" WHERE id_classe = "+idClasse;
//
//        db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//
//        if (cursor.moveToFirst())
//        {
//            do {
//                Matiere matiere = new Matiere();
//                matiere.setId(cursor.getInt(0));
//                matiere.setLibelle(cursor.getString(1));
//                matiere.setNbHeures(cursor.getInt(2));
//                matiere.setId_classe(cursor.getInt(3));
//
//                listeMatieres.add(matiere);
//            }
//            while (cursor.moveToNext());
//        }
//        db.close();
//        return listeMatieres;
//    }
//
//    public Matiere findMatiereById(int id)
//    {
//        Matiere matiere = null;
//        String query = "SELECT * FROM " + TABLE_Matiere+" WHERE id = "+id;
//
//        db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//
//        if (cursor.moveToFirst())
//        {
//            do {
//                matiere = new Matiere();
//                matiere.setId(cursor.getInt(0));
//                matiere.setLibelle(cursor.getString(1));
//                matiere.setNbHeures(cursor.getInt(2));
//                matiere.setId_classe(cursor.getInt(3));
//            }
//            while (cursor.moveToNext());
//        }
//        db.close();
//        return matiere;
//    }
//
//
//    /** LISTE D'ABSENCE **/
//    public Long ajouterSeance(ListeAbsence listeAbsence)
//    {
//        db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        //values.put("id", matiere.getId());
//        values.put("date", listeAbsence.getDate());
//        values.put("heure", listeAbsence.getHeure());
//        values.put("id_matiere", listeAbsence.getId_matiere());
//        values.put("id_classe", listeAbsence.getId_classe());
//
//        // Insertion
//        Long idLA = db.insert(TABLE_LISTEABSENCE, null, values);
//
//        // noter tous l'absence avec ABSENT
//        ArrayList<Etudiant> listeEtudiants= getAllEtudiantByClasse(listeAbsence.getId_classe());
//        for (Etudiant e: listeEtudiants)
//        {
//            db = this.getWritableDatabase();
//            ContentValues val = new ContentValues();
//            val.put("cne", e.getCne());
//            val.put("present", 1); //0: Absent, 1: Présent
//            val.put("id_listeAbsence", idLA);
//            db.insert(TABLE_PRESENCE, null, val);
//        }
//        db.close(); // Closing database connection
//        return idLA;
//    }
//
//    public ArrayList<ListeAbsence> getAllSeances(int id_classe, int id_matiere)
//    {
//        ArrayList<ListeAbsence> liste = new ArrayList<>();
//        String query = "SELECT * FROM "+TABLE_LISTEABSENCE+" WHERE id_classe = "+id_classe+" AND id_matiere = "+id_matiere;
//
//        db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//
//        if (cursor.moveToFirst())
//        {
//            do {
//                ListeAbsence listeAbsence = new ListeAbsence();
//                listeAbsence.setId(cursor.getInt(0));
//                listeAbsence.setDate(cursor.getString(1));
//                listeAbsence.setHeure(cursor.getString(2));
//                listeAbsence.setId_matiere(cursor.getInt(3));
//                listeAbsence.setId_classe(cursor.getInt(4));
//
//                liste.add(listeAbsence);
//            }
//            while (cursor.moveToNext());
//        }
//        db.close();
//        return liste;
//    }
//
//    public Presence getPresenceByCne(String cne, int id_LA)
//    {
//        Presence presence = null;
//        String query = "SELECT * FROM " + TABLE_PRESENCE+" WHERE cne = "+cne+" AND id_listeAbsence = "+id_LA;
//
//        db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//
//        if (cursor.moveToFirst())
//        {
//            do {
//                presence = new Presence();
//                presence.setId(cursor.getInt(0));
//                presence.setCne(cursor.getString(1));
//                presence.setPresent(cursor.getInt(2));
//                presence.setId_listeAbsence(cursor.getInt(3));
//            }
//            while (cursor.moveToNext());
//        }
//        db.close();
//        return presence;
//    }
//
//    public ArrayList<Presence> getAllSeancesByIdLA(int id_seance)
//    {
//        ArrayList<Presence> listePresence = new ArrayList<>();
//        String query = "SELECT * FROM "+TABLE_PRESENCE+" WHERE  id_listeAbsence = "+id_seance;
//
//        db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//
//        if (cursor.moveToFirst())
//        {
//            do {
//                Presence presence = new Presence();
//                presence.setId(cursor.getInt(0));
//                presence.setCne(cursor.getString(1));
//                presence.setPresent(cursor.getInt(2));
//                presence.setId_listeAbsence(cursor.getInt(3));
//
//                listePresence.add(presence);
//            }
//            while (cursor.moveToNext());
//        }
//        db.close();
//        return listePresence;
//    }
//
//    public void updatePresenceByCne(String cne, int id_seance, boolean present)
//    {
//        db = this.getWritableDatabase();
//        String query1 = "UPDATE "+TABLE_PRESENCE+" SET present = 1 WHERE cne = "+cne+" AND id_listeAbsence = "+id_seance;
//        String query2 = "UPDATE "+TABLE_PRESENCE+" SET present = 0 WHERE cne = "+cne+" AND id_listeAbsence = "+id_seance;
//        if(present)
//            db.execSQL(query1);
//        else
//            db.execSQL(query2);
//        db.close();
//    }
}
