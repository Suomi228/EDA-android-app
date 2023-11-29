package com.example.eda.retrofitThigies;

import com.example.eda.retrofitThigies.models.GetFoodResponse;
import com.example.eda.retrofitThigies.models.MenuItemEntity;
import com.example.eda.retrofitThigies.models.UserLoginRequest;
import com.example.eda.retrofitThigies.models.UserRegisterRequest;
import com.example.eda.retrofitThigies.models.UserRegisterOrLoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    //BASE_URL = "http://95.165.91.211:8081/api/v1/"
    @POST("auth/register")
    Call<UserRegisterOrLoginResponse> registerUser(@Body UserRegisterRequest student);

    @POST("auth/authentication")
    Call<UserRegisterOrLoginResponse> loginUser(@Body UserLoginRequest student);

    @GET("staff/getFood")
    Call<List<MenuItemEntity>> getFood();
}
