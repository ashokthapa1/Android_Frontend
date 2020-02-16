package api;

import java.util.List;

import models.Hospital;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SearchAPI {

    @GET("/api/search/hospital/{hospitalName}")
    Call<List<Hospital>> getHopitalByName(
        @Path("hospitalName") String hospitalName
    );

    @GET("/api/search/location/{location}")
    Call<List<Hospital>> getHopitalByLocation(
        @Path("location") String location
    );

    @GET("/api/search/disease/{diseaseName}")
    Call<List<Hospital>> getHopitalByDisease(
        @Path("diseaseName") String diseaseName
    );

    @GET("/api/recommend/mostviewed/")
    Call<List<Hospital>> getMostViewed();
}

