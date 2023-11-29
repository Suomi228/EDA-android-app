package com.example.eda.retrofitThigies;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://95.165.91.211:8081/api/v1/";
    private static Retrofit retrofit = null;
    private Context context;

    public ApiClient(Context context) {
        this.context = context;
    }

    static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new AuthInterceptor(BearerTokenManager.getToken()))
            .build();
    public static Retrofit getClient(){
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        return retrofit;
    }
}
