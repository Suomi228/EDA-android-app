package com.example.eda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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

public class Login extends AppCompatActivity {
    TextInputEditText edit_text_email, edit_text_password;
    Button button_log;
    FirebaseAuth mAuth;
    ProgressBar progress_bar;
    TextView text_view,password_forgot;
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
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        text_view = findViewById(R.id.registerNow);
        edit_text_email = findViewById(R.id.email);
        edit_text_password = findViewById(R.id.password);
        button_log = findViewById(R.id.btn_login);
        progress_bar = findViewById(R.id.progress_bar);
        password_forgot = findViewById(R.id.forgot_password);

        text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
                finish();
            }
        });
        button_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress_bar.setVisibility(View.VISIBLE);
                String email,password;
                email = String.valueOf(edit_text_email.getText());
                password = String.valueOf(edit_text_password.getText());

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this,"Все поля должны быть заполнены!",Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progress_bar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "С возвращением!",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Login.this, "Неверный логин или пароль.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        password_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                View dialog_view = getLayoutInflater().inflate(R.layout.dialog_forgot,null);
                TextInputEditText email_forgot = dialog_view.findViewById(R.id.email_forgot);
                builder.setView(dialog_view);
                AlertDialog dialog = builder.create();

                dialog_view.findViewById(R.id.btn_reset).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String user_email = email_forgot.getText().toString();
                        if (TextUtils.isEmpty(user_email) && !Patterns.EMAIL_ADDRESS.matcher(user_email).matches()){
                            Toast.makeText(Login.this,"Все поля должны быть заполнены!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        mAuth.sendPasswordResetEmail(user_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(Login.this,"На вашу почту пришло письмо!",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                                else{
                                    Toast.makeText(Login.this,"Что-то пошло не так.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                });
                dialog_view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                if (dialog.getWindow()!=null){
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                dialog.show();
            }
        });
    }
}