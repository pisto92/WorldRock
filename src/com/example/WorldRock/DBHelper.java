package com.example.WorldRock;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL(Rock_DB.ROCK_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
    }

}