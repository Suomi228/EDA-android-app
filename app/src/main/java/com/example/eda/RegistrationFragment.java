package com.example.eda;

import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eda.databinding.FragmentRegistrationBinding;
import com.example.eda.retrofitThigies.ApiClient;
import com.example.eda.retrofitThigies.ApiService;
import com.example.eda.retrofitThigies.models.UserRegisterRequest;
import com.example.eda.retrofitThigies.models.UserRegisterOrLoginResponse;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrationFragment extends FragmentCallback {

    CallBackFragment callBackFragment;

    FragmentRegistrationBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false);

        binding.loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callBackFragment != null) {
                    callBackFragment.changeFragment(new LoginFragment(), true);
                }
            }
        });
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressBar.setVisibility(View.VISIBLE);
                String email, password, password_confirm;
                email = String.valueOf(binding.email.getText());
                password = String.valueOf(binding.password.getText());
                password_confirm = String.valueOf(binding.passwordConfirm.getText());

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(password_confirm)) {
                    Toast.makeText(getContext(), "Все поля должны быть заполнены!", Toast.LENGTH_SHORT).show();
                    binding.progressBar.setVisibility(View.GONE);
                    return;
                } else if (!password.equals(password_confirm)) {
                    Toast.makeText(getContext(), "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
                    binding.progressBar.setVisibility(View.GONE);
                    return;
                } else if (password.length() < 8) {
                    Toast.makeText(getContext(), "Минимальная длина пароля 8!", Toast.LENGTH_SHORT).show();
                    binding.progressBar.setVisibility(View.GONE);
                    return;
                }

                ApiService apiService = ApiClient.getClient().create(ApiService.class);

                UserRegisterRequest userRegisterRequest = new UserRegisterRequest(
                        "name",
                        "surname",
                        email,
                        password
                );

                Call<UserRegisterOrLoginResponse> call = apiService.registerUser(userRegisterRequest);

                call.enqueue(new Callback<UserRegisterOrLoginResponse>() {
                    @Override
                    public void onResponse(Call<UserRegisterOrLoginResponse> call, Response<UserRegisterOrLoginResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getContext(), "Аккаунт создан.", Toast.LENGTH_SHORT).show();
                            if (callBackFragment != null) {
                                callBackFragment.changeFragment(new LoginFragment(), true);
                            }
                        } else {
                            Toast.makeText(getContext(), "Что-то пошло не так.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserRegisterOrLoginResponse> call, Throwable t) {

                    }
                });

            }
        });

        return binding.getRoot();
    }

    public void setCallBackFragment(CallBackFragment callBackFragment) {
        this.callBackFragment = callBackFragment;
    }
}