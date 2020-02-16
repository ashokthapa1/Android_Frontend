package com.example.hospitalsearchsystem;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnBeam;
    private EditText eSearchKeyword;
    private Spinner eSearchType;
    private Button btnESearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBeam = findViewById(R.id.btnBeam);
        btnESearch = findViewById(R.id.btnEsearch);
        eSearchKeyword = findViewById(R.id.emergencySearchKeyword);
        eSearchType = findViewById(R.id.emergencySearchType);

        btnBeam.setOnClickListener(this);
        btnESearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent myIntent = new Intent(MainActivity.this, ViewPagerActivity.class);
        startActivity(myIntent);
    }

    private void search() {

        if ( eSearchKeyword.getText().length() > 0 ){
            SearchResult.isMostViewed = false;
            SearchResult.currentSearchType = eSearchType.getSelectedItem().toString().toLowerCase();
            SearchResult.currentSearchKeyword = eSearchKeyword.getText().toString();
            SearchResult.isEmergency = true;
            Intent in = new Intent(MainActivity.this,SearchResult.class);
            startActivity(in);
        }else{
            Commons.alert(getApplicationContext(),"Please enter search keyword");
        }

    }

}
