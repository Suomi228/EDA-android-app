package com.example.eda.retrofitThigies;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class BearerTokenManager {
    private Context context;
    private String EdaAppPrefs = "EdaAppPrefs";
    private static String token = "";

    public BearerTokenManager(Context context) {
        this.context = context;
    }

    public void saveTokenToPref(String token) {
        this.token = token;
        // Получение объекта SharedPreferences
        SharedPreferences preferences = context.getSharedPreferences(EdaAppPrefs, Context.MODE_PRIVATE);
        // Получение объекта Editor для редактирования данных
        SharedPreferences.Editor editor = preferences.edit();
        // Запись данных
        editor.putString("BearerToken", token);
        // Применение изменений
        editor.apply();
    }

    public String getTokenFromPref() {
        // Получение объекта SharedPreferences
        SharedPreferences preferences = context.getSharedPreferences(EdaAppPrefs, Context.MODE_PRIVATE);
        // Получение сохраненного значения
        String token = preferences.getString("BearerToken", "");
        this.token = token;
        return token;
    }

    public static String getToken() {
        return token;
    }

    public boolean isTokenNotExpired() {
        if (token.length() <= 0)
            return false;
        Claims claims = Jwts.parser()
                .setSigningKey("eda")
                .parseClaimsJws(token)
                .getBody();
        Date expDate = new Date(claims.getExpiration().getTime() * 1000);
        Date now = new Date();
        if (expDate.before(now)) {
            return true;
        }
        return false;
    }


}
