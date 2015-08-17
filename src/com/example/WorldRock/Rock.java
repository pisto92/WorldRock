package com.example.WorldRock;

import com.google.android.gms.maps.model.LatLng;

public class Rock {

    public String location;
    public LatLng coordinates;

    public Rock(String location, LatLng coordinates) {
        this.location = location;
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return location + ", " + coordinates.latitude + ", " + coordinates.longitude;
    }

}
