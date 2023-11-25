package com.example.eda;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.eda.databinding.FragmentMenuBinding;

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
        binding.recViewMeals.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Salads","category_salads"));
        category.add(new CategoryDomain("FirstDishes","category_first_dishes"));
        category.add(new CategoryDomain("SecondDishes","category_second_dishes"));
        category.add(new CategoryDomain("SideDishes","category_side_dishes"));
        category.add(new CategoryDomain("Deserts","category_desserts"));
        category.add(new CategoryDomain("Bakery","category_bakery"));
        category.add(new CategoryDomain("Pizza","category_pizza"));
        category.add(new CategoryDomain("Drinks","category_drinks"));
        adapter = new CategoryAdapter(category);
        binding.recViewMeals.setAdapter(adapter);

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