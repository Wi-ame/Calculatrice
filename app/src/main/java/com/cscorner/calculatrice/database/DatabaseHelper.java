package com.cscorner.calculatrice.database;

import static java.nio.channels.SocketChannel.open;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "login";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_USERS = "user";
    public static final String COLUMN_ID = "id";
    public static final String EMAIL = "email";
    public static final String COLUMN_PASSWORD_1 = "password1";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD_2 = "password2";
    public static final String COLUMN_PASSWORD_3 = "password3";


    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EMAIL + " TEXT UNIQUE, " +
                    COLUMN_PASSWORD_1 + " TEXT, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_PASSWORD_2 + " TEXT, " +
                    COLUMN_PASSWORD_3 + " TEXT  );";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_USERS);
        onCreate(db);
    }
    // Ajoutez ces constantes à votre classe DatabaseHelper
    public enum InsertResult {
        SUCCESS,
        DUPLICATE_USER,
        FAILURE
    }


    public InsertResult insertUser(String email, String password1, String username, String password2, String password3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL, email);
        values.put(COLUMN_PASSWORD_1, password1);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD_2, password2);
        values.put(COLUMN_PASSWORD_3, password3);


        if (userExists(email)) {
            db.close();
            return InsertResult.DUPLICATE_USER;
        }

        long result = db.insert(TABLE_USERS, null, values);
        db.close();

        return (result == -1) ? InsertResult.FAILURE : InsertResult.SUCCESS;
    }


    private boolean userExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + EMAIL + " FROM " + TABLE_USERS + " WHERE " + EMAIL + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }
    public boolean authenticateUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + EMAIL + "=? AND " + COLUMN_PASSWORD_1 + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});

        boolean authenticated = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return authenticated;
    }

}

