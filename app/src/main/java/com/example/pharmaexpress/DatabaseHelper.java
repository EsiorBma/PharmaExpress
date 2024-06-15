package com.example.pharmaexpress;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;

public class DatabaseHelper {

    private static final String DATABASE_NAME = "pharmaexpress.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USERS = "Utilisateur";
    private static final String COLUMN_ID = "idUtilisateur";
    private static final String COLUMN_FIRSTNAME = "prenom";
    private static final String COLUMN_SURNAME = "nom";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "motDePasse";
    private static final String COLUMN_LOCATION = "adresse";

    private final SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        DatabaseCopyHelper dbHelper = new DatabaseCopyHelper(context);
        try {
            dbHelper.createDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        db = dbHelper.openDatabase();
    }

    public boolean addUser(String firstname, String surname, String email, String password, String location) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, firstname);
        values.put(COLUMN_SURNAME, surname);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_LOCATION, location);

        long result = db.insert(TABLE_USERS, null, values);
        // if result is -1, there was an error
        return result != -1;
    }
    public Cursor getUserData(String email) {
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + "=?";
        return db.rawQuery(query, new String[]{email});
    }
    public void close() {
        db.close();
    }
}
