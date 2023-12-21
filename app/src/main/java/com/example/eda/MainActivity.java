package com.example.eda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eda.databinding.ActivityMainBinding;
import com.example.eda.retrofitThigies.BearerTokenManager;

public class MainActivity extends AppCompatActivity implements CallBackFragment {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BearerTokenManager bearerTokenManager = new BearerTokenManager(this);
        bearerTokenManager.getTokenFromPref();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (bearerTokenManager.isTokenNotExpired()) {
            replaceFragment(new HomeFragment(), false);
        } else
            replaceFragment(new LoginFragment(), false);

        binding.bottomNavigationView.setVisibility(View.GONE);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home_item) {
                replaceFragment(new HomeFragment(), true);
            }
            if (item.getItemId() == R.id.menu_item) {
                replaceFragment(new MenuFragment(), true);
            }
            if (item.getItemId() == R.id.profile_item) {
                replaceFragment(new ProfileFragment(), true);
            }
            if (item.getItemId() == R.id.cart_item) {
                replaceFragment(new CartFragment(), true);
            }
            return true;
        });
    }

    @Override
    public void onBackPressed() {

    }

    private void replaceFragment(FragmentCallback fragment, boolean allowReturn) {
        fragment.setCallBackFragment(this);
        if (allowReturn) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }


    private void registrationFragment() {
        FragmentCallback fragment = new LoginFragment();
        FragmentManager f1 = getSupportFragmentManager();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.container, fragment);
        ft.commit();
    }

    @Override
    public void changeFragment(FragmentCallback fragment, boolean allowReturn) {
        replaceFragment(fragment, allowReturn);
    }

    @Override
    public void setNavigationVisibility(int state) {
        binding.bottomNavigationView.setVisibility(state);
    }
}