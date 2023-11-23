package com.example.eda;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegistrationFragment extends FragmentCallback{

    TextInputEditText edit_text_email, edit_text_password, edit_text_confirm;
    Button button_reg;
    FirebaseAuth mAuth;
    ProgressBar progress_bar;
    TextView text_view;
    CallBackFragment callBackFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        mAuth = FirebaseAuth.getInstance();
        edit_text_email = view.findViewById(R.id.email);
        edit_text_password = view.findViewById(R.id.password);
        button_reg = view.findViewById(R.id.btn_register);
        edit_text_confirm = view.findViewById(R.id.password_confirm);
        progress_bar = view.findViewById(R.id.progress_bar);
        text_view = view.findViewById(R.id.loginNow);

        text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callBackFragment!=null){
                    callBackFragment.changeFragment(new LoginFragment(),true);
                }
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
                    Toast.makeText(getContext(),"Все поля должны быть заполнены!",Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                else if (!password.equals(password_confirm)){
                    Toast.makeText(getContext(),"Пароли не совпадают!",Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                else if (password.length()<8){
                    Toast.makeText(getContext(),"Минимальная длина пароля 8!",Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progress_bar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){

                                                Toast.makeText(getContext(), "Аккаунт создан, пожалуйста подтвердите почту.",
                                                        Toast.LENGTH_SHORT).show();
                                                if (callBackFragment!=null){
                                                    callBackFragment.changeFragment(new LoginFragment(),true);
                                                }
                                            }
                                            else{
                                                Toast.makeText(getContext(), "Что-то пошло не так.",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(getContext(), "Что-то пошло не так.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        return view;
    }
    public void setCallBackFragment(CallBackFragment callBackFragment){
        this.callBackFragment = callBackFragment;
    }
}