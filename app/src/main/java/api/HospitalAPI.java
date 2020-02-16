package api;

import com.example.hospitalsearchsystem.Commons;

import java.util.List;

import models.Hospital;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HospitalAPI {

    @DELETE("api/hospital/{id}")
    Call<String> deleteHospital(
            @Path("id") int hospitalId
    );

    @FormUrlEncoded
    @POST("api/hospital/")
    Call<String> addHospital(
            @Field("name") String name,
            @Field("address") String address,
            @Field("rating") String rating,
            @Field("phone") String phone,
            @Field("disease") String diseases,
            @Field("doctor") String doctors,
            @Field("nears") String nears
    );

    @FormUrlEncoded
    @PUT("api/hospital/{id}")
    Call<String> updateospital(
        @Path("id") int hospitalId,
        @Field("colName") String colName,
        @Field("colValue") String colValue
    );

    @FormUrlEncoded
    @POST("api/view_hospital")
    Call<String> addViewCount(
            @Field("hospitalId") int hospitalId,
            @Field("viewCount") int viewCount
    );

}
