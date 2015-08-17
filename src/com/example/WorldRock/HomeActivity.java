package com.example.WorldRock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {
    /**
     * Called when the activity is first created.
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

       //this.deleteDatabase("WorldRock"); //Elimina il db ogni volta che lanci l'app


        Button btn_go_to_map = (Button) findViewById(R.id.btn_open_map);

        btn_go_to_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_open_map = new Intent(HomeActivity.this, MapActivity.class);
                startActivity(int_open_map);
            }
        });
    }
}
