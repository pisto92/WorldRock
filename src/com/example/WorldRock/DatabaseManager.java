package com.example.WorldRock;

import android.database.Cursor;
import android.util.Log;
import com.google.android.gms.maps.model.LatLng;

import java.util.LinkedList;

public class DatabaseManager {

    public static LinkedList<Rock> getAllRockLocation(Database db)
    {
        LinkedList<Rock> rocks= new LinkedList<>();
        db.open();
        Cursor cursor = db.fetchRocks();
        int location_col = cursor.getColumnIndex(Database.Rock_DB.ROCK_LOCATION);
        int latitude_col = cursor.getColumnIndex(Database.Rock_DB.ROCK_LAT);
        int longitude_col = cursor.getColumnIndex(Database.Rock_DB.ROCK_LON);
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
        db.close();
        return rocks;
    }
}
