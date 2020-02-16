package com.example.hospitalsearchsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import api.HospitalAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import url.URL;

public class edit extends AppCompatActivity{

    private Button btnUback,btnUpdate;
    private EditText updateValue,hospitalId;
    private Spinner toUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnUback = findViewById(R.id.btnUback);
        btnUpdate= findViewById(R.id.btnUpdate);
        updateValue = findViewById(R.id.updateValue);
        toUpdate = findViewById(R.id.updateType);
        hospitalId = findViewById(R.id.uId);

        btnUback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(edit.this, admin_dashboard.class);
                startActivity(intent);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editHospital();
            }
        });


    }

    private void editHospital(){
        if (updateValue.getText().length() > 0 && hospitalId.getText().length() > 0){

            HospitalAPI hospitalAPI = URL.getRetrofitInstance().create(HospitalAPI.class);
            Call<String> updateCall = hospitalAPI.updateospital(Integer.parseInt(hospitalId.getText().toString()),toUpdate.getSelectedItem().toString().toLowerCase(),updateValue.getText().toString());

            updateCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
            Commons.alert(getApplicationContext(),"Updated");

        }else{
            Commons.alert(this,"Hospital id and update value needed");
        }
    }
}
