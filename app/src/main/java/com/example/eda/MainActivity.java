package com.example.eda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.eda.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements CallBackFragment{

    ActivityMainBinding binding;
    TextView registerNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginFragment();
        registerNow = findViewById(R.id.registerNow);
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
    private void loginFragment(){
        LoginFragment fragment = new LoginFragment();
        fragment.setCallBackFragment(this);
        FragmentManager f1 = getSupportFragmentManager();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container,fragment);
        ft.commit();
    }
    private void registrationFragment(){
        Fragment fragment = new LoginFragment();
        FragmentManager f1 = getSupportFragmentManager();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.container,fragment);
        ft.commit();
    }

    @Override
    public void changeFragment(Fragment fragment) {
        replaceFragment(fragment);
    }
}