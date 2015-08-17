package com.example.WorldRock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.google.android.gms.maps.model.LatLng;

import java.util.LinkedList;

public class DatabaseManager {

    SQLiteDatabase mDb;
    DbHelper mDbHelper;
    Context mContext;
    private static final String DB_NAME = "WorldRock";
    private static final int DB_VERSION = 1;

    public DatabaseManager(Context ctx) {
        mContext = ctx;
        mDbHelper = new DbHelper(ctx, DB_NAME, null, DB_VERSION);
        mDb = mDbHelper.getWritableDatabase();
        mDb.execSQL("PRAGMA foreign_keys=ON;");
    }

    public void close() {
        mDb.close();
    }

    public void insertRock(Rock rock)
    {
        ContentValues cv = new ContentValues();
        cv.put(Rock_DB.ROCK_LOCATION, rock.location);
        cv.put(Rock_DB.ROCK_LAT, rock.coordinates.latitude);
        cv.put(Rock_DB.ROCK_LON, rock.coordinates.longitude);
        mDb.insert(Rock_DB.ROCK_TABLE, null, cv);
    }

    public Cursor fetchRocks() {
        return mDb.query(Rock_DB.ROCK_TABLE, null, null, null, null, null, null);
    }

    public LinkedList<Rock> getAllRockLocation()
    {
        LinkedList<Rock> rocks= new LinkedList<>();
        Cursor cursor = fetchRocks();
        int location_col = cursor.getColumnIndex(Rock_DB.ROCK_LOCATION);
        int latitude_col = cursor.getColumnIndex(Rock_DB.ROCK_LAT);
        int longitude_col = cursor.getColumnIndex(Rock_DB.ROCK_LON);
        while (cursor.moveToNext())

        {
            Rock current_rock = new Rock(
                    cursor.getString(location_col),
                    new LatLng(cursor.getFloat(latitude_col), cursor.getFloat(longitude_col))
            );
            rocks.add(current_rock);
            //TODO print to info
            Log.d("DEBUG!!", current_rock.toString());
        }
        return rocks;
    }
}