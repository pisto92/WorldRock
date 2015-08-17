package com.example.WorldRock;

public class Rock_DB
{
    static final String ROCK_TABLE = "point";
    static final String ROCK_ID = "id";
    static final String ROCK_LOCATION = "location";
    static final String ROCK_DESCRIPTION = "description";
    static final String ROCK_LAT = "latitude";
    static final String ROCK_LON = "longitude";

    static final String ROCK_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
            + Rock_DB.ROCK_TABLE + " ("
            + Rock_DB.ROCK_ID + " integer PRIMARY KEY AUTOINCREMENT, "
            + Rock_DB.ROCK_LOCATION + " text not null, "
            + Rock_DB.ROCK_DESCRIPTION + " text not null, "
            + Rock_DB.ROCK_LAT + " float not null, "
            + Rock_DB.ROCK_LON + " float not null" +
            ");";
}