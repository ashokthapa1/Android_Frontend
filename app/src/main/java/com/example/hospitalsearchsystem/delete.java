package com.example.hospitalsearchsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import api.HospitalAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import url.URL;

public class delete extends AppCompatActivity {

    private Button btnDback,btnDdelete;
    private EditText etDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        btnDback = findViewById(R.id.btnDback);
        etDelete = findViewById(R.id.etDelete);
        btnDdelete = findViewById(R.id.btnDdelete);

        btnDback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(delete.this, admin_dashboard.class);
                startActivity(intent);
            }
        });

        btnDdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteHospital();
            }
        });


    }

    private void deleteHospital(){


        if (etDelete.getText().toString().length() > 0){

            HospitalAPI hospitalAPI = URL.getRetrofitInstance().create(HospitalAPI.class);
            Call<String> delCalll = hospitalAPI.deleteHospital(Integer.parseInt(etDelete.getText().toString()));

            delCalll.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                }
            });
            Commons.alert(getApplicationContext(),"Deleted");
        }else{
            Commons.alert(getApplicationContext(),"Delete id required!");
        }

    }

}
