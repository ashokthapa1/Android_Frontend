package api;

import com.example.hospitalsearchsystem.Commons;

import java.util.List;

import models.Users;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsersAPI {

    @GET("api/users")
    Call<List<Users>> getUsers();

    @FormUrlEncoded
    @POST("api/auth/users/")
    Call<Commons.ResponseBody> authUser(
        @Field("username") String username,
        @Field("password") String password
    );

    @FormUrlEncoded
    @POST("api/users")
    Call<Void> registerUser(
            @Field("username") String username1,
            @Field("fullname") String fullname,
            @Field("email") String email,
            @Field("address") String address,
            @Field("password") String password1
    );



}
