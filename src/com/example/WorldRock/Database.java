package com.example.WorldRock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {

    SQLiteDatabase mDb;
    DbHelper mDbHelper;
    Context mContext;
    private static final String DB_NAME = "WorldRock";
    private static final int DB_VERSION = 1;

    public Database(Context ctx) {
        mContext = ctx;
        mDbHelper = new DbHelper(ctx, DB_NAME, null, DB_VERSION);
    }

    public void open() {
        mDb = mDbHelper.getWritableDatabase();
        mDb.execSQL("PRAGMA foreign_keys=ON;");

    }

    public void close() {
        mDb.close();
    }

    public void insertRock(Rock rock) {
        ContentValues cv = new ContentValues();
        cv.put(Rock_DB.ROCK_LOCATION, rock.location);
        cv.put(Rock_DB.ROCK_LAT, rock.coordinates.latitude);
        cv.put(Rock_DB.ROCK_LON, rock.coordinates.longitude);
        mDb.insert(Rock_DB.ROCK_TABLE, null, cv);
    }

    public Cursor fetchRocks() {
        return mDb.query(Rock_DB.ROCK_TABLE, null, null, null, null, null, null);
    }

    static class Rock_DB {
        static final String ROCK_TABLE = "point";
        static final String ROCK_ID = "id";
        static final String ROCK_LOCATION = "location";
        static final String ROCK_LAT = "latitude";
        static final String ROCK_LON = "longitude";

    }

    private static final String ROCK_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
            + Rock_DB.ROCK_TABLE + " ("
            + Rock_DB.ROCK_ID + " integer PRIMARY KEY AUTOINCREMENT, "
            + Rock_DB.ROCK_LOCATION + " text not null, "
            + Rock_DB.ROCK_LAT + " float not null, "
            + Rock_DB.ROCK_LON + " float not null" +
            ");";

    private class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(ROCK_TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
        }

    }


}