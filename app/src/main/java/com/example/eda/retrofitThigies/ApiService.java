package com.example.eda.retrofitThigies;

import com.example.eda.retrofitThigies.models.UserLoginRequest;
import com.example.eda.retrofitThigies.models.UserRegisterRequest;
import com.example.eda.retrofitThigies.models.UserRegisterOrLoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("auth/register")
    Call<UserRegisterOrLoginResponse> registerUser(@Body UserRegisterRequest student);

    @POST("auth/authentication")
    Call<UserRegisterOrLoginResponse> loginUser(@Body UserLoginRequest student);
}
