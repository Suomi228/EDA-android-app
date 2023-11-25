package com.example.eda;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eda.databinding.FragmentMenuBinding;
import com.example.eda.menuStuff.CategoryAdapter;
import com.example.eda.menuStuff.CategoryDomain;
import com.example.eda.menuStuff.MenuItemOffset;

import java.util.ArrayList;

public class MenuFragment extends FragmentCallback {
    FragmentMenuBinding binding;
    RecyclerView.Adapter adapter;
    RecyclerView rec_view_category_list;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        recyclerViewCategoryList();
//    }

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
        adapter = new CategoryAdapter(category);
        binding.recViewMeals.setAdapter(adapter);
        binding.recViewMeals.addItemDecoration(new MenuItemOffset(20));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewCategoryList();  // Moved from onCreate to onViewCreated
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}