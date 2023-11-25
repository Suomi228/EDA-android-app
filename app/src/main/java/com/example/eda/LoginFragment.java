package com.example.eda;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eda.databinding.DialogForgotBinding;
import com.example.eda.databinding.FragmentLoginBinding;
import com.example.eda.retrofitThigies.ApiClient;
import com.example.eda.retrofitThigies.ApiService;
import com.example.eda.retrofitThigies.BearerTokenManager;
import com.example.eda.retrofitThigies.models.UserLoginRequest;
import com.example.eda.retrofitThigies.models.UserRegisterOrLoginResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends FragmentCallback {
    TextInputEditText edit_text_email, edit_text_password;
    Button button_log;
    FirebaseAuth mAuth;
    ProgressBar progress_bar;
    TextView text_viewRegistretion, password_forgot;
    FragmentLoginBinding binding;
    DialogForgotBinding dialogForgotBinding;

    CallBackFragment callBackFragment;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if ((currentUser != null) && currentUser.isEmailVerified()) {
            if (callBackFragment != null) {
                callBackFragment.changeFragment(new HomeFragment(), true);

            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        View ContainerView = binding.getRoot();
        dialogForgotBinding = DialogForgotBinding.inflate(inflater, container, false);
        mAuth = FirebaseAuth.getInstance();
        //text_viewRegistretion = ContainerView.findViewById(R.id.registerNow);
        //edit_text_email = ContainerView.findViewById(R.id.email);
        //edit_text_password = ContainerView.findViewById(R.id.password);
        //button_log = ContainerView.findViewById(R.id.btn_login);
        //progress_bar = ContainerView.findViewById(R.id.progress_bar);
        //password_forgot = ContainerView.findViewById(R.id.forgot_password);
        binding.registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callBackFragment != null) {
                    callBackFragment.changeFragment(new RegistrationFragment(), true);

                }

            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(binding.email.getText());
                password = String.valueOf(binding.password.getText());

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "Все поля должны быть заполнены!", Toast.LENGTH_SHORT).show();
                    binding.progressBar.setVisibility(View.GONE);
                    return;
                }

                ApiService apiService = ApiClient.getClient().create(ApiService.class);
                UserLoginRequest userLoginRequest = new UserLoginRequest(email, password);
                Call<UserRegisterOrLoginResponse> callLogin = apiService.loginUser(userLoginRequest);

                callLogin.enqueue(new Callback<UserRegisterOrLoginResponse>() {
                    @Override
                    public void onResponse(Call<UserRegisterOrLoginResponse> call, Response<UserRegisterOrLoginResponse> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getContext(), "С возвращением!",
                                    Toast.LENGTH_SHORT).show();
                            if (callBackFragment!=null){
                                BearerTokenManager bearerTokenManager = new BearerTokenManager(getContext());
                                bearerTokenManager.saveTokenToPref(response.body().getToken());
                                HomeFragment homeFragment = new HomeFragment();
                                homeFragment.setCallBackFragment(callBackFragment);
                                callBackFragment.changeFragment(homeFragment, true);
                            }
                        }else {
                            Toast.makeText(getContext(), "Неверный логин или пароль.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserRegisterOrLoginResponse> call, Throwable t) {

                    }
                });


//                mAuth.signInWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                progress_bar.setVisibility(View.GONE);
//                                if (task.isSuccessful() && mAuth.getCurrentUser().isEmailVerified()) {
//                                    Toast.makeText(getContext(), "С возвращением!",
//                                            Toast.LENGTH_SHORT).show();
//                                    if (callBackFragment!=null){
//                                        HomeFragment homeFragment = new HomeFragment();
////                                      homeFragment.setCallBackFragment(callBackFragment);
//                                        callBackFragment.changeFragment(homeFragment,true);
//
//                                    }
//
//                                } else {
//                                    Toast.makeText(getContext(), "Неверный логин или пароль.",
//                                            Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });

                //затычка
//                binding.progressBar.setVisibility(View.GONE);
//                if (true) {
//                    Toast.makeText(getContext(), "С возвращением!",
//                            Toast.LENGTH_SHORT).show();
//                    if (callBackFragment != null) {
//                        HomeFragment homeFragment = new HomeFragment();
//                        homeFragment.setCallBackFragment(callBackFragment);
//                        callBackFragment.changeFragment(homeFragment, true);
//                    }
//
//                }
            }
        });
        binding.forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View dialog_view = getLayoutInflater().inflate(R.layout.dialog_forgot, null);
                TextInputEditText email_forgot = dialog_view.findViewById(R.id.email_forgot);
                builder.setView(dialog_view);
                AlertDialog dialog = builder.create();

                dialog_view.findViewById(R.id.btn_reset);
                dialog_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String user_email = email_forgot.getText().toString();
                        if (TextUtils.isEmpty(user_email) && !Patterns.EMAIL_ADDRESS.matcher(user_email).matches()) {
                            Toast.makeText(getContext(), "Все поля должны быть заполнены!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        mAuth.sendPasswordResetEmail(user_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "На вашу почту пришло письмо!", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(getContext(), "Что-то пошло не так.", Toast.LENGTH_SHORT).show();
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
                if (dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                dialog.show();
            }
        });
//        return inflater.inflate(R.layout.fragment_login, container, false);
        return ContainerView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public void setCallBackFragment(CallBackFragment callBackFragment) {
        this.callBackFragment = callBackFragment;
    }


}