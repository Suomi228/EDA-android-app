package com.example.eda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {
    TextInputEditText edit_text_email, edit_text_password, edit_text_confirm;
    Button button_reg;
    FirebaseAuth mAuth;
    ProgressBar progress_bar;
    TextView text_view;
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();
        edit_text_email = findViewById(R.id.email);
        edit_text_password = findViewById(R.id.password);
        button_reg = findViewById(R.id.btn_register);
        edit_text_confirm = findViewById(R.id.password_confirm);
        progress_bar = findViewById(R.id.progress_bar);
        text_view = findViewById(R.id.loginNow);
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
                String email,password,password_confirm;
                email = String.valueOf(edit_text_email.getText());
                password = String.valueOf(edit_text_password.getText());
                password_confirm = String.valueOf(edit_text_confirm.getText());

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(password_confirm)){
                    Toast.makeText(Registration.this,"Все поля должны быть заполнены!",Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                else if (!password.equals(password_confirm)){
                    Toast.makeText(Registration.this,"Пароли не совпадают!",Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                else if (password.length()<8){
                    Toast.makeText(Registration.this,"Минимальная длина пароля 8!",Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progress_bar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(Registration.this, "Аккаунт создан.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Registration.this, "Что-то пошло не так.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }
}