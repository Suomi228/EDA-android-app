package com.example.eda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.eda.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewGroup.LayoutParams params = binding.container.getLayoutParams();
        params.height = 600;
        binding.container.setLayoutParams(params);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId()==R.id.home_item){
                replaceFragment(new HomeFragment());
            }
            if (item.getItemId()==R.id.menu_item){
                replaceFragment(new MenuFragment());
            }
            if (item.getItemId()==R.id.profile_item){
                replaceFragment(new ProfileFragment());
            }
            if (item.getItemId()==R.id.cart_item){
                replaceFragment(new CartFragment());
            }
            return true;
        });

    }
    private void replaceFragment(Fragment fragment){
        FragmentManager f1 = getSupportFragmentManager();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container,fragment);
        ft.commit();
    }
}