package com.example.eda.uretrofit;

import com.example.eda.uretrofit.models.UserAuth;
import com.example.eda.uretrofit.models.UserRegister;
import com.example.eda.uretrofit.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiAuthService {
    @POST("auth/register")
    Call<UserResponse> registerUser(@Body UserRegister student);

    @POST("auth/authentication")
    Call<UserAuth> authenticateUser(@Body UserAuth student);
}
