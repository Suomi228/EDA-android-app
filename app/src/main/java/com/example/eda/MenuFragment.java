package com.example.eda;

import android.os.Bundle;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.example.eda.databinding.FragmentMenuBinding;
import com.example.eda.menuStuff.CategoryAdapter;
import com.example.eda.menuStuff.CategoryDomain;
import com.example.eda.menuStuff.GridViewAdapter;
import com.example.eda.menuStuff.GridViewDomain;
import com.example.eda.menuStuff.MenuItemOffset;
import com.example.eda.menuStuff.RecyclerViewInterface;
import com.example.eda.retrofitThigies.ApiClient;
import com.example.eda.retrofitThigies.ApiService;
import com.example.eda.retrofitThigies.models.Category;
import com.example.eda.retrofitThigies.models.MenuItemEntity;
import com.example.eda.retrofitThigies.models.UserLoginRequest;
import com.example.eda.retrofitThigies.models.UserRegisterOrLoginResponse;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MenuFragment extends FragmentCallback implements RecyclerViewInterface {
    FragmentMenuBinding binding;
    RecyclerView.Adapter adapter;
    RecyclerView.Adapter grid_adapter;

    ArrayList<Category> category = new ArrayList<>();
    ArrayList<MenuItemEntity> menuItemEntities = new ArrayList<>();

    int quantityGeneral = 1;
    RecyclerView rec_view_category_list;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        recyclerViewCategoryList();
//    }



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
                    category.clear();
                    category.addAll(categoryList);
                    adapter.notifyDataSetChanged();


//                    adapter = new CategoryAdapter(category, MenuFragment.this);
//                    binding.recViewMeals.setAdapter(adapter);
//                    binding.recViewMeals.addItemDecoration(new MenuItemOffset(20,20));
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }
        );
        //ArrayList<CategoryDomain> category = new ArrayList<>();
//        category.add(new CategoryDomain("Салаты","category_salads"));
//        category.add(new CategoryDomain("Первое","category_first_dishes"));
//        category.add(new CategoryDomain("Второе","category_second_dishes"));
//        category.add(new CategoryDomain("Гарниры","category_side_dishes"));
//        category.add(new CategoryDomain("Дессерты","category_deserts"));
//        category.add(new CategoryDomain("Выпечка","category_bakery"));
//        category.add(new CategoryDomain("Пицца","category_pizza"));
//        category.add(new CategoryDomain("Напитки","category_drinks"));
//        adapter = new CategoryAdapter(category, this);
//        binding.recViewMeals.setAdapter(adapter);
//        binding.recViewMeals.addItemDecoration(new MenuItemOffset(20,20));


    }
    private void recyclerViewAllFood(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        binding.recViewCategory.setLayoutManager(gridLayoutManager);
//
//        ArrayList<GridViewDomain> grid_category = new ArrayList<>();
//        grid_category.add(new GridViewDomain("Салаты","category_salads",100));
//        grid_category.add(new GridViewDomain("Первое","category_first_dishes",120));
//        grid_category.add(new GridViewDomain("Второе","category_second_dishes",90));
//        grid_category.add(new GridViewDomain("Гарниры","category_side_dishes",75));
//        grid_category.add(new GridViewDomain("Дессерты","category_deserts",80));
//        grid_category.add(new GridViewDomain("Выпечка","category_bakery",56));
//        grid_category.add(new GridViewDomain("Пицца","category_pizza",150));
//        grid_category.add(new GridViewDomain("Напитки","category_drinks",35));

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
                    //menuItemEntityList.sort((MenuItemEntity m1, MenuItemEntity m2) -> m1.getId().compareTo(m2.getId()));
                    //todo сделать сортировку по id
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

//        grid_adapter = new GridViewAdapter(grid_category, this);
//        binding.recViewCategory.setAdapter(grid_adapter);
//        binding.recViewCategory.addItemDecoration(new MenuItemOffset(20,100));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewCategoryList();
        recyclerViewAllFood();// Moved from onCreate to onViewCreated
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
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
//        String picUrl = "";
//        switch (position){
//            case 0:
//                picUrl = "category_salads";
//                productName.setText("Салаты");
//                productFee.setText("100");
//                break;
//            case 1:
//                picUrl = "category_first_dishes";
//                productName.setText("Первое");
//                productFee.setText("135");
//                break;
//            case 2:
//                picUrl = "category_second_dishes";
//                productName.setText("Второе");
//                productFee.setText("150");
//                break;
//            case 3:
//                picUrl = "category_side_dishes";
//                productName.setText("Гарниры");
//                productFee.setText("50");
//                break;
//            case 4:
//                picUrl = "category_deserts";
//                productName.setText("Дессерты");
//                productFee.setText("89");
//                break;
//            case 5:
//                picUrl = "category_bakery";
//                productName.setText("Выпечка");
//                productFee.setText("80");
//                break;
//            case 6:
//                picUrl = "category_pizza";
//                productName.setText("Пицца");
//                productFee.setText("200");
//                break;
//            case 7:
//                picUrl = "category_drinks";
//                productName.setText("Напитки");
//                productFee.setText("35");
//                break;
//
//        }
//        int drawableResourceId = productPic.getContext().getResources().getIdentifier(picUrl, "drawable", productPic.getContext().getPackageName());

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
                quantityGeneral +=1;
                quantityOfFood.setText(String.valueOf(quantityGeneral));
                menuItemEntities.get(position).setQuantity(quantityGeneral);
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

            }
        });
        quantityGeneral = 1;
        dialog.show();

    }

//    private void replaceFragment(FragmentCallback fragment, boolean allowReturn) {
//        fragment.setCallBackFragment((CallBackFragment) this);
//        if (allowReturn) {
//            getParentFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.container, fragment)
//                    .addToBackStack(null)
//                    .commit();
//        }
//        getParentFragmentManager()
//                .beginTransaction()
//                .replace(R.id.container, fragment)
//                .commit();
//    }

}