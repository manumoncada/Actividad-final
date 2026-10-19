package com.example.contactoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

// Base de datos SQLite: tabla "mascota" con las ultimas 5 mascotas que recibieron rating
public class BaseDatos extends SQLiteOpenHelper {

    private static final String DB_NOMBRE = "mascotas.db";
    private static final int DB_VERSION = 1;

    public static final String TABLA_MASCOTA = "mascota";
    public static final String COL_ID = "id";
    public static final String COL_NOMBRE = "nombre";
    public static final String COL_FOTO = "foto";
    public static final String COL_RATING = "rating";

    public static final int MAX_MASCOTAS = 5;

    public BaseDatos(Context context) {
        super(context.getApplicationContext(), DB_NOMBRE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // id UNIQUE identifica a cada mascota. No es INTEGER PRIMARY KEY a proposito:
        // asi SQLite conserva su rowid interno, que crece con cada insercion y nos
        // sirve para saber cual es la mascota mas reciente.
        db.execSQL("CREATE TABLE " + TABLA_MASCOTA + " ("
                + COL_ID + " INTEGER NOT NULL UNIQUE, "
                + COL_NOMBRE + " TEXT NOT NULL, "
                + COL_FOTO + " INTEGER NOT NULL, "
                + COL_RATING + " INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_MASCOTA);
        onCreate(db);
    }

    // Guarda la mascota completa tras darle rating y deja solo las ultimas 5
    public void guardarRating(Mascota mascota) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            // Si ya estaba, se borra y se vuelve a insertar para que pase a ser la mas reciente
            db.delete(TABLA_MASCOTA, COL_ID + " = ?", new String[]{String.valueOf(mascota.getId())});

            ContentValues valores = new ContentValues();
            valores.put(COL_ID, mascota.getId());
            valores.put(COL_NOMBRE, mascota.getNombre());
            valores.put(COL_FOTO, mascota.getFoto());
            valores.put(COL_RATING, mascota.getRating());
            db.insert(TABLA_MASCOTA, null, valores);

            // Conserva solo las 5 mas recientes
            db.execSQL("DELETE FROM " + TABLA_MASCOTA + " WHERE rowid NOT IN ("
                    + "SELECT rowid FROM " + TABLA_MASCOTA
                    + " ORDER BY rowid DESC LIMIT " + MAX_MASCOTAS + ")");

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    // Devuelve las ultimas mascotas con rating, la mas reciente primero
    public ArrayList<Mascota> obtenerUltimas() {
        ArrayList<Mascota> lista = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLA_MASCOTA,
                new String[]{COL_ID, COL_NOMBRE, COL_FOTO, COL_RATING},
                null, null, null, null,
                "rowid DESC", String.valueOf(MAX_MASCOTAS));
        try {
            while (c.moveToNext()) {
                lista.add(new Mascota(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3)));
            }
        } finally {
            c.close();
        }
        return lista;
    }

    // Rating guardado de una mascota (o el valor por defecto si no esta en la tabla)
    public int obtenerRating(int id, int porDefecto) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLA_MASCOTA, new String[]{COL_RATING},
                COL_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        try {
            if (c.moveToFirst()) {
                return c.getInt(0);
            }
        } finally {
            c.close();
        }
        return porDefecto;
    }
}
