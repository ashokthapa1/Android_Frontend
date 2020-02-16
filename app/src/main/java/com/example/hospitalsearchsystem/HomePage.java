package com.example.hospitalsearchsystem;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    private EditText searchKeyword;
    private Spinner searchType;
    private Button btnSearch,btnOut,btnMostViewed;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        searchKeyword = findViewById(R.id.etSearch);
        searchType = findViewById(R.id.searchType);
        btnSearch = findViewById(R.id.btnSearch);
        btnOut = findViewById(R.id.btnOut);
        btnMostViewed = findViewById(R.id.btnMostViewed);
        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Commons.logout(HomePage.this);
            }
        });

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        SensorEventListener sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                if (values[1] > 0){
                    SearchResult.isMostViewed = true;
                    Intent intent = new Intent(HomePage.this,SearchResult.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if (sensor != null) {
            sensorManager.registerListener(sel, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(this, "No Sensor Found", Toast.LENGTH_SHORT).show();
        }

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

        btnMostViewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchResult.isMostViewed = true;
                Intent intent = new Intent(HomePage.this,SearchResult.class);
                startActivity(intent);
            }
        });

    }

    private void search() {

        if ( searchKeyword.getText().length() > 0 ){
            SearchResult.isMostViewed = false;
            SearchResult.currentSearchType = searchType.getSelectedItem().toString().toLowerCase();
            SearchResult.currentSearchKeyword = searchKeyword.getText().toString();
            SearchResult.isEmergency = false;
            Intent in = new Intent(HomePage.this,SearchResult.class);
            startActivity(in);
        }else{
            Commons.alert(getApplicationContext(),"Please enter search keyword");
        }

    }




}
