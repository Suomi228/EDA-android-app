package com.example.eda.uretrofit;

import lombok.Getter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://95.165.91.211:8081/api/v1/";
    private static Retrofit retrofit = null;
    @Getter
    private static String bearerToken;
    public static Retrofit getClient(){
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;
    }

    public String getBearerToken() {
        return bearerToken;
    }

    public static void setBearerToken(String bearerTokeno) {
        bearerTokeno = "Bearer " +  bearerToken;
    }
}
