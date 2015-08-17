package com.example.WorldRock;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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
        UpdateDeleteDialog(latLng);
        loadAllRockLocation(map);
    }

    private void loadAllRockLocation(GoogleMap map) {
        DatabaseManager db = new DatabaseManager(getApplicationContext());
        LinkedList<Rock> rocks = db.getAllRockLocation();
        while (!rocks.isEmpty()) {
            map.addMarker(new MarkerOptions().position(rocks.pollFirst().coordinates).title("N.D."));
        }
        db.close();
    }

    private void UpdateDeleteDialog(final LatLng latlng) {

        final Dialog myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.dialog_insert_rock);
        myDialog.setCancelable(true);
        myDialog.setTitle("Edit Fuel");
        myDialog.show();

        final EditText edt_location = (EditText) myDialog.findViewById(R.id.edt_location);
        final EditText edt_description = (EditText) myDialog.findViewById(R.id.edt_description);
        Button btn_ok = (Button) myDialog.findViewById(R.id.btn_dialog_insert_ok);

        btn_ok.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DatabaseManager db=new DatabaseManager(getApplicationContext());
                db.insertRock(new Rock(edt_location.getText().toString(), edt_description.getText().toString(), latlng));
                Toast.makeText(getApplicationContext(), "Insert new rock!", Toast.LENGTH_LONG).show();
                db.close();
                myDialog.cancel();
            }
        });
    }
}