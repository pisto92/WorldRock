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
        Database db = new Database(getApplicationContext());
        db.open();
        db.insertRock(new Rock("Location to define", latLng));
    }

    private void loadAllRockLocation(GoogleMap map) {
        Database db = new Database(getApplicationContext());
        LinkedList<Rock> rocks = DatabaseManager.getAllRockLocation(db);
        while(!rocks.isEmpty()){
            map.addMarker(new MarkerOptions().position(rocks.pollFirst().coordinates).title("N.D."));
        }
    }
}