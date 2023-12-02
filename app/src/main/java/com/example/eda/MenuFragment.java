package com.example.eda;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.eda.databinding.FragmentMenuBinding;
import com.example.eda.menuStuff.CategoryAdapter;
import com.example.eda.menuStuff.CategoryDomain;
import com.example.eda.menuStuff.GridViewAdapter;
import com.example.eda.menuStuff.GridViewDomain;
import com.example.eda.menuStuff.MenuItemOffset;
import com.example.eda.menuStuff.RecyclerViewInterface;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MenuFragment extends FragmentCallback implements RecyclerViewInterface {
    FragmentMenuBinding binding;
    RecyclerView.Adapter adapter;
    RecyclerView.Adapter grid_adapter;
    GridViewDomain object;
    RecyclerView rec_view_category_list;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        recyclerViewCategoryList();
//    }

    private void getBundle(){

    }

    private void recyclerViewCategoryList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recViewMeals.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Салаты","category_salads"));
        category.add(new CategoryDomain("Первое","category_first_dishes"));
        category.add(new CategoryDomain("Второе","category_second_dishes"));
        category.add(new CategoryDomain("Гарниры","category_side_dishes"));
        category.add(new CategoryDomain("Дессерты","category_deserts"));
        category.add(new CategoryDomain("Выпечка","category_bakery"));
        category.add(new CategoryDomain("Пицца","category_pizza"));
        category.add(new CategoryDomain("Напитки","category_drinks"));
        adapter = new CategoryAdapter(category, this);
        binding.recViewMeals.setAdapter(adapter);
        binding.recViewMeals.addItemDecoration(new MenuItemOffset(20,20));


    }
    private void recyclerViewAllFood(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        binding.recViewCategory.setLayoutManager(gridLayoutManager);

        ArrayList<GridViewDomain> grid_category = new ArrayList<>();
        grid_category.add(new GridViewDomain("Салаты","category_salads",100));
        grid_category.add(new GridViewDomain("Первое","category_first_dishes",120));
        grid_category.add(new GridViewDomain("Второе","category_second_dishes",90));
        grid_category.add(new GridViewDomain("Гарниры","category_side_dishes",75));
        grid_category.add(new GridViewDomain("Дессерты","category_deserts",80));
        grid_category.add(new GridViewDomain("Выпечка","category_bakery",56));
        grid_category.add(new GridViewDomain("Пицца","category_pizza",150));
        grid_category.add(new GridViewDomain("Напитки","category_drinks",35));
        grid_adapter = new GridViewAdapter(grid_category, this);
        binding.recViewCategory.setAdapter(grid_adapter);
        binding.recViewCategory.addItemDecoration(new MenuItemOffset(20,100));
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
        View dialog_view = getLayoutInflater().inflate(R.layout.order_menu, null);

        builder.setView(dialog_view);
        AlertDialog dialog = builder.create();
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