package com.example.eda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eda.uretrofit.ApiAuthService;
import com.example.eda.uretrofit.ApiClient;
import com.example.eda.uretrofit.models.UserAuth;
import com.example.eda.uretrofit.models.UserRegister;
import com.example.eda.uretrofit.models.UserResponse;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {
    TextInputEditText edit_text_email, edit_text_password, edit_text_confirm;
    Button button_reg;
    //FirebaseAuth mAuth;
    ProgressBar progress_bar;
    TextView text_view, govno1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //mAuth = FirebaseAuth.getInstance();
        edit_text_email = findViewById(R.id.email);
        edit_text_password = findViewById(R.id.password);
        button_reg = findViewById(R.id.btn_register);
        edit_text_confirm = findViewById(R.id.password_confirm);
        progress_bar = findViewById(R.id.progress_bar);
        text_view = findViewById(R.id.loginNow);
        govno1 = findViewById(R.id.govno);

        text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
        button_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress_bar.setVisibility(View.VISIBLE);
                String email, password, password_confirm;
                email = String.valueOf(edit_text_email.getText());
                password = String.valueOf(edit_text_password.getText());
                password_confirm = String.valueOf(edit_text_confirm.getText());

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(password_confirm)) {
                    Toast.makeText(Registration.this, "Все поля должны быть заполнены!", Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);
                    return;
                } else if (!password.equals(password_confirm)) {
                    Toast.makeText(Registration.this, "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);
                    return;
                } else if (password.length() < 8) {
                    Toast.makeText(Registration.this, "Минимальная длина пароля 8!", Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }

                UserRegister userRegister = UserRegister.builder()
                        .name("")
                        .surname("")
                        .email(email)
                        .password(password)
                        .build();
                ApiAuthService apiService = ApiClient.getClient().create(ApiAuthService.class);
                Call<UserResponse> call = apiService.registerUser(userRegister);
                call.enqueue(new Callback<UserResponse>() {
                                 @Override
                                 public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                                     ApiClient.setBearerToken(response.body().getToken());
                                     Intent intent = new Intent(getApplicationContext(), Login.class);
                                     startActivity(intent);
                                     finish();
                                 }

                                 @Override
                                 public void onFailure(Call<UserResponse> call, Throwable t) {
                                     Toast.makeText(Registration.this, "Чёто наебнулос.",
                                             Toast.LENGTH_SHORT).show();
                                 }
                             }
                );
//                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                progress_bar.setVisibility(View.GONE);
//                                if (task.isSuccessful()) {
//                                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if (task.isSuccessful()){
//
//                                                Toast.makeText(Registration.this, "Аккаунт создан, пожалуйста подтвердите почту.",
//                                                        Toast.LENGTH_SHORT).show();
//                                                Intent intent = new Intent(getApplicationContext(), Login.class);
//                                                startActivity(intent);
//                                                finish();
//                                            }
//                                            else{
//                                                Toast.makeText(Registration.this, "Что-то пошло не так.",
//                                                        Toast.LENGTH_SHORT).show();
//                                            }
//                                        }
//                                    });
//                                } else {
//                                    Toast.makeText(Registration.this, "Что-то пошло не так.",
//                                            Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
            }
        });

    }
}