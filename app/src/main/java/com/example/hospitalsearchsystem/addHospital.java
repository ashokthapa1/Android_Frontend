package com.example.hospitalsearchsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import api.HospitalAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import url.URL;

public class addHospital extends AppCompatActivity {

    private Button btnAback,btnAadd;
    private EditText addName,addAddress,addDoctors,addRating,addDiseases,addNears,addPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hospital);

        btnAback = findViewById(R.id.btnAback);
        btnAadd = findViewById(R.id.btnAadd);
        addName = findViewById(R.id.addName);
        addAddress = findViewById(R.id.addAddress);
        addDoctors = findViewById(R.id.addDoctors);
        addRating = findViewById(R.id.addRating);
        addDiseases = findViewById(R.id.addDiseases);
        addNears = findViewById(R.id.addNears);
        addPhone = findViewById(R.id.addPhone);

        btnAback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addHospital.this, admin_dashboard.class);
                startActivity(intent);
            }
        });

        btnAadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHospital();
            }
        });


    }


    private void addHospital(){

        String name = addName.getText().toString();
        String address = addAddress.getText().toString();
        String doctors = addDoctors.getText().toString();
        String rating = addRating.getText().toString();
        String diseases = addDiseases.getText().toString();
        String nears = addNears.getText().toString();
        String phone = addPhone.getText().toString();

        if ( name.length() > 0 && address.length() > 0 && doctors.length() > 0 && rating.length() > 0 && diseases.length() > 0 && nears.length() > 0 ){
            HospitalAPI hospitalAPI = URL.getRetrofitInstance().create(HospitalAPI.class);
            Call<String> addCall = hospitalAPI.addHospital(name,address,rating,phone,diseases,doctors,nears);
            addCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
                Commons.alert(addHospital.this,"Hospital has been added");
                Commons.showNotification(addHospital.this,"Hospital Notification","Hospital has been added");

        }else{
            Commons.alert(getApplicationContext(),"All values required");
        }
    }
}
