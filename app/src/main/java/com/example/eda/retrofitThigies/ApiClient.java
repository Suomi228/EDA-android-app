package com.example.eda.retrofitThigies;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://95.165.91.211:8081/api/v1/";

    public static final String PICTURES_URL = "http://95.165.91.211:8081/files/";
    private static Retrofit retrofit = null;
    private static Retrofit retrofitWithoutInterceptor = null;
    private Context context;

    public ApiClient(Context context) {
        this.context = context;
    }

    static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new AuthInterceptor(BearerTokenManager.getToken()))
            .build();
    public static Retrofit getClient(){

        int i = client.interceptors().size();

        if (retrofit == null || client.interceptors().size() < 1)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        return retrofit;
    }
    public static Retrofit getClientWithoutInterceptor(){
        if (retrofitWithoutInterceptor == null)
            retrofitWithoutInterceptor = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofitWithoutInterceptor;
    }
}
