package com.example.WorldRock;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.LinkedList;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                setAndSaveSelectedLocation(latLng);
            }
        });


    }

    @Override
    public void onMapReady(GoogleMap map) {
        loadAllRockLocation(map);
    }

    private void setAndSaveSelectedLocation(LatLng latLng) {
        map.addMarker(new MarkerOptions().position(latLng).title("N.D.").draggable(true));
        DatabaseManager db = new DatabaseManager(getApplicationContext());
        db.insertRock(new Rock("Location to define", latLng));
        db.close();
    }

    private void loadAllRockLocation(GoogleMap map) {
        DatabaseManager db = new DatabaseManager(getApplicationContext());
        LinkedList<Rock> rocks = db.getAllRockLocation();
        while(!rocks.isEmpty()){
            map.addMarker(new MarkerOptions().position(rocks.pollFirst().coordinates).title("N.D."));
        }
        db.close();
    }
}