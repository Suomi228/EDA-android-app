package com.example.eda;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eda.databinding.FragmentCartBinding;
import com.example.eda.databinding.FragmentProfileBinding;
import com.example.eda.retrofitThigies.ApiClient;
import com.example.eda.retrofitThigies.ApiService;
import com.example.eda.retrofitThigies.BearerTokenManager;
import com.example.eda.retrofitThigies.models.GetFoodResponse;
import com.example.eda.retrofitThigies.models.UserLoginRequest;
import com.example.eda.retrofitThigies.models.UserRegisterOrLoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends FragmentCallback {
    FragmentCartBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false);

        binding.listTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                ApiService apiService = ApiClient.getClient().create(ApiService.class);
//                Call<List<GetFoodResponse.MenuItemEntity>> callLogin = apiService.getFood();
//
//                callLogin.enqueue(new Callback<List<GetFoodResponse.MenuItemEntity>>() {
//                    @Override
//                    public void onResponse(Call<List<GetFoodResponse.MenuItemEntity>> call, Response<List<GetFoodResponse.MenuItemEntity>> response) {
//                        if (response.isSuccessful()) {
//
//                            List<GetFoodResponse.MenuItemEntity> menuItemEntityList = response.body();
//
//                            String s ="";
//
//                            for(GetFoodResponse.MenuItemEntity menuItemEntity : menuItemEntityList){
//                                s += menuItemEntity.toString() + "\n";
//                            }
//
//                            binding.listTextView.setText(s);
//
//                        } else {
//                            Toast.makeText(getContext(), "Неверный логин или пароль. " + response.code(),
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<GetFoodResponse.MenuItemEntity>> call, Throwable t) {
//                        Toast.makeText(getContext(), "Ошибка подключения к серверу." + t.toString(),
//                                Toast.LENGTH_SHORT).show();
//                        throw new RuntimeException("Ошибка подключения к серверу." + t.toString());
//
//                    }
//
//                });
        }
        });


        return binding.getRoot();
    }
}