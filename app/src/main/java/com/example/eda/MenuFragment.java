package com.example.eda;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eda.databinding.FragmentMenuBinding;
import com.example.eda.menuStuff.CategoryAdapter;
import com.example.eda.menuStuff.CategoryDomain;
import com.example.eda.menuStuff.MenuItemOffset;
import com.example.eda.menuStuff.RecyclerViewInterface;
import com.example.eda.retrofitThigies.ApiClient;
import com.example.eda.retrofitThigies.ApiService;
import com.example.eda.retrofitThigies.models.GetFoodResponse;
import com.example.eda.retrofitThigies.models.MenuItemEntity;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuFragment extends FragmentCallback implements RecyclerViewInterface {
    FragmentMenuBinding binding;
    RecyclerView.Adapter adapter;
    RecyclerView rec_view_category_list;

    List<MenuItemEntity> menuItemEntityList;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        recyclerViewCategoryList();
//    }

    private void recyclerViewCategoryList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recViewMeals.setLayoutManager(linearLayoutManager);

        ArrayList<MenuItemEntity> category = new ArrayList<>(menuItemEntityList);
        //category.add(new MenuItemEntity("Салаты","category_salads"));
        adapter = new CategoryAdapter(category, this, getContext());
        binding.recViewMeals.setAdapter(adapter);
        binding.recViewMeals.addItemDecoration(new MenuItemOffset(20));


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMenuItems();
        //recyclerViewCategoryList();  // перенес в метод getMenuItems()
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onItemCLick(int position) {
        switch (position){
            case 0:
                //replaceFragment(new RegistrationFragment(),true);
                //callBackFragment.changeFragmentCategory(new RegistrationFragment(),true);
                break;
            case 1:

                break;
            case 2:
                break;
            case 3:
               break;
            case 4:
                break;
            case 5:
                break;
            case 6:
               break;
            case 7:

                break;

        }
    }

    public void getMenuItems() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<MenuItemEntity>> callLogin = apiService.getFood();

        callLogin.enqueue(new Callback<List<MenuItemEntity>>() {
            @Override
            public void onResponse(Call<List<MenuItemEntity>> call, Response<List<MenuItemEntity>> response) {
                if (response.isSuccessful()) {
                    menuItemEntityList = response.body();
                    recyclerViewCategoryList();
                } else {
                    Toast.makeText(getContext(), "Неверный логин или пароль. " + response.code(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MenuItemEntity>> call, Throwable t) {
                Toast.makeText(getContext(), "Ошибка подключения к серверу." + t.toString(),
                        Toast.LENGTH_SHORT).show();
                throw new RuntimeException("Ошибка подключения к серверу." + t.toString());
            }

        });
    }

}