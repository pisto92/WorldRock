package com.example.WorldRock;

import com.google.android.gms.maps.model.LatLng;

public class Rock {

    public String location;
    public String description;
    public LatLng coordinates;

    public Rock(String location, String description, LatLng coordinates) {
        this.location = location;
        this.description = description;
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return location + ", " + description + ", " + coordinates.latitude + ", " + coordinates.longitude;
    }

}
