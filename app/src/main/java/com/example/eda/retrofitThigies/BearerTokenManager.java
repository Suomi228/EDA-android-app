package com.example.eda.retrofitThigies;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class BearerTokenManager {
    private Context context;
    private static final String EDA_APP_PREFS = "EdaAppPrefs";
    private static String token = "";
    private static final String SECRET_KEY = ("9c56bbb2442aa20f7d48ce5ba13b75c38000266334fb008387322a8a8ff24944").toUpperCase();
    public BearerTokenManager(Context context) {
        this.context = context;
    }

    public void saveTokenToPref(String token) {
        this.token = token;
        // Получение объекта SharedPreferences
        SharedPreferences preferences = context.getSharedPreferences(EDA_APP_PREFS, Context.MODE_PRIVATE);
        // Получение объекта Editor для редактирования данных
        SharedPreferences.Editor editor = preferences.edit();
        // Запись данных
        editor.putString("BearerToken", token);
        // Применение изменений
        editor.apply();
    }

    public String getTokenFromPref() {
        // Получение объекта SharedPreferences
        SharedPreferences preferences = context.getSharedPreferences(EDA_APP_PREFS, Context.MODE_PRIVATE);
        // Получение сохраненного значения
        String token = preferences.getString("BearerToken", "");
        this.token = token;
        return token;
    }

    public void deleteTokenFromPref() {
        // Получение объекта SharedPreferences
        SharedPreferences preferences = context.getSharedPreferences(EDA_APP_PREFS, Context.MODE_PRIVATE);
        // Получение объекта Editor для редактирования данных
        SharedPreferences.Editor editor = preferences.edit();
        // Запись данных
        editor.putString("BearerToken", "");
        // Применение изменений
        editor.apply();
    }

    public static String getToken() {
        return token;
    }

    public boolean isTokenNotExpired() {
        if (token.length() <= 0)
            return false;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            Date expDate = new Date(claims.getExpiration().getTime() * 1000);
            Date now = new Date();

            if (now.before(expDate)) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }


}
