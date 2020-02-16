package com.example.hospitalsearchsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.ListAdapter;
import api.HospitalAPI;
import api.SearchAPI;
import models.Hospital;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import url.URL;

public class SearchResult extends AppCompatActivity {

    private TextView pageTitle;
    private ListView searchResultView;
    private List<Hospital> hospitalList = new ArrayList();
    private List<String> hospitalListString = new ArrayList();
    public static String currentSearchType = "";
    public static String currentSearchKeyword = "";
    public static boolean isEmergency = false;
    public static boolean isMostViewed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        pageTitle = findViewById(R.id.pageTitle);
        searchResultView = findViewById(R.id.searchResultListView);
        if(isMostViewed){
            setTitle("Most Viewed Hospitals");
            pageTitle.setText("Most Viewed Hospitals");
        }
        else{
            setTitle("Search Result");
            pageTitle.setText("Search Result");
        }
        getHospitals();
    }
    ArrayAdapter<String> listViewAdapter;
    private void getHospitals(){

        SearchAPI searchAPI = URL.getRetrofitInstance().create(SearchAPI.class);
        Call<List<Hospital>> searchListCall = searchAPI.getHopitalByName(currentSearchKeyword);

        if (isMostViewed){
            searchListCall = searchAPI.getMostViewed();
        }else{
            if (currentSearchType.equals("diseases")){
                searchListCall = searchAPI.getHopitalByDisease(currentSearchKeyword);
            }

            if (currentSearchType.equals("location")){
                searchListCall = searchAPI.getHopitalByLocation(currentSearchKeyword);
            }
        }

        searchListCall.enqueue(new Callback<List<Hospital>>() {
            @Override
            public void onResponse(Call<List<Hospital>> call, Response<List<Hospital>> response) {
                if (response.isSuccessful()) {
                    hospitalList = response.body();
                    if (hospitalList.size() > 0) {
                        for (Hospital hospital : hospitalList) {
                            String doctors = "", hospitalString = "";
                            if (!isMostViewed) doctors = "\n" + hospital.getDoctors().replace("'", "");
                            if (!isEmergency && !isMostViewed) hospitalString = "\n" + hospital.getName() + "\n\nLocation: " + hospital.getAddress() + "\nRating: " + hospital.getRating() + "\n" + doctors + "\nNears: " + hospital.getNears();
                            else hospitalString = "\n" + hospital.getName() + "\n\nLocation: " + hospital.getAddress() + "\nRating: " + hospital.getRating() + "\n";

                            if (isMostViewed) hospitalString += "\nView Count: "+hospital.getViewCount()+"\n";
                            hospitalListString.add(hospitalString);
                        }
                    }

                    listViewAdapter = new ListAdapter(getApplicationContext(), R.layout.custom_listview, hospitalListString);

                    searchResultView.setAdapter(listViewAdapter);
                    addViewCount();
                } else {
                    Commons.alert(getApplicationContext(), "No hospitals found");
                }

            }

            @Override
            public void onFailure(Call<List<Hospital>> call, Throwable t) {

            }
        });

    }

    private void addViewCount(){

        for (Hospital hospital:hospitalList){
            HospitalAPI hospitalAPI = URL.getRetrofitInstance().create(HospitalAPI.class);
            Call<String> addCall = hospitalAPI.addViewCount(hospital.getId(),1);

            addCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                }
            });
        }

    }
}
