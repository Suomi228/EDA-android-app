package com.example.eda;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.eda.RoomDataBase.Cart;
import com.example.eda.RoomDataBase.CartDB;
import com.example.eda.RoomDataBase.CartDao;
import com.example.eda.databinding.FragmentMenuBinding;
import com.example.eda.menuStuff.CategoryAdapter;
import com.example.eda.menuStuff.GridViewAdapter;
import com.example.eda.menuStuff.MenuItemOffset;
import com.example.eda.menuStuff.RecyclerViewInterface;
import com.example.eda.retrofitThigies.ApiClient;
import com.example.eda.retrofitThigies.ApiService;
import com.example.eda.retrofitThigies.models.Category;
import com.example.eda.retrofitThigies.models.MenuItemEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MenuFragment extends FragmentCallback implements RecyclerViewInterface {
    FragmentMenuBinding binding;
    RecyclerView.Adapter adapter;
    RecyclerView.Adapter grid_adapter;

    ArrayList<Category> category = new ArrayList<>();
    ArrayList<MenuItemEntity> menuItemEntities = new ArrayList<>();

    CartDB cartDB;
    CartDao cartDao;
    int quantityGeneral = 1;



    private void recyclerViewCategoryList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recViewMeals.setLayoutManager(linearLayoutManager);

        adapter = new CategoryAdapter(category, MenuFragment.this);
        binding.recViewMeals.setAdapter(adapter);
        binding.recViewMeals.addItemDecoration(new MenuItemOffset(20,20));


        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Category>> callCategories = apiService.getCategories();

        callCategories.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, retrofit2.Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    List<Category> categoryList = response.body();
                    Collections.sort(categoryList, Comparator.comparingInt(Category::getId));
                    category.clear();
                    category.addAll(categoryList);
                    adapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }
        );


    }
    private void recyclerViewAllFood(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        binding.recViewCategory.setLayoutManager(gridLayoutManager);


        grid_adapter = new GridViewAdapter(menuItemEntities, MenuFragment.this);
        binding.recViewCategory.setAdapter(grid_adapter);
        binding.recViewCategory.addItemDecoration(new MenuItemOffset(20,50));

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<MenuItemEntity>> callMenu = apiService.getFood();

        callMenu.enqueue(new Callback<List<MenuItemEntity>>() {
            @Override
            public void onResponse(Call<List<MenuItemEntity>> call, retrofit2.Response<List<MenuItemEntity>> response) {
                if (response.isSuccessful()) {
                    List<MenuItemEntity> menuItemEntityList = response.body();
                    menuItemEntities.clear();
                    menuItemEntities.addAll(menuItemEntityList);
                    grid_adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getContext(), "Что-то пошло не так." + response.code(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MenuItemEntity>> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cartDB = CartDB.getInstance(getContext());
        cartDao = cartDB.korzinaDao();

        recyclerViewCategoryList();
        recyclerViewAllFood();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onCategoryCLick(int position) {
        switch (position){
            case 0:

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

    @Override
    public void onFoodCLick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View order_menu = getLayoutInflater().inflate(R.layout.order_menu, null);
        ImageView productPic = order_menu.findViewById(R.id.ProductPic);
        ImageView plusBtn = order_menu.findViewById(R.id.plus_btn);
        ImageView minusBtn = order_menu.findViewById(R.id.minus_btn);
        Button addToCartBtn = order_menu.findViewById(R.id.add_to_cart_btn);
        Button closeBtn = order_menu.findViewById(R.id.close_btn);
        TextView productFee = order_menu.findViewById(R.id.ProductFee);
        TextView  productName = order_menu.findViewById(R.id.ProductName);
        TextView  quantityOfFood = order_menu.findViewById(R.id.quantityOfFood);


        productFee.setText(Integer.toString((int) menuItemEntities.get(position).getPrice()) + " ₽");
        productName.setText(menuItemEntities.get(position).getName());
        Glide.with(productPic.getContext())
                .load(ApiClient.PICTURES_URL + menuItemEntities.get(position).getCategoryEntity().getCategory() + "/" + menuItemEntities.get(position).getPictureUrl() + ".png")
                .into(productPic);

        builder.setView(order_menu);
        AlertDialog dialog = builder.create();


        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Количество еды определенного вида в корзине
                int cunt = menuItemEntities.get(position).getUserQuantity();

                quantityGeneral +=1;
                //quantityOfFood.setText(String.valueOf(cunt));
                //menuItemEntities.get(position).setUserQuantity(++cunt);
                quantityOfFood.setText(String.valueOf(quantityGeneral));
                menuItemEntities.get(position).setUserQuantity(quantityGeneral);



            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (quantityGeneral > 1) {
                    quantityGeneral -=1;
                    quantityOfFood.setText(String.valueOf(quantityGeneral));
                    menuItemEntities.get(position).setQuantity(quantityGeneral);
                }

            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartDao.insert(new Cart());

            }
        });
        quantityGeneral = 1;
        dialog.show();

    }

}