package com.example.eda;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.eda.databinding.ActivityMainBinding;
import com.example.eda.databinding.FragmentProfileBinding;
import com.example.eda.retrofitThigies.BearerTokenManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileFragment extends FragmentCallback {

    CallBackFragment callBackFragment;
    FragmentProfileBinding binding;

    public void onStart() {
        super.onStart();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder a_builder = new AlertDialog.Builder(getActivity());
                a_builder.setMessage("Вы уверены что хотите выйти из аккаунта?")
                        .setCancelable(false)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new BearerTokenManager(getContext()).deleteTokenFromPref();
                                ((MainActivity) getActivity()).setNavigationVisibility(View.INVISIBLE);
                                callBackFragment.changeFragment(new LoginFragment(), false);
                            }
                        })
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alert = a_builder.create();
                alert.setTitle("Выход из аккаунта");
                alert.show();
            }
        });
    }

    public void setCallBackFragment(CallBackFragment callBackFragment) {
        this.callBackFragment = callBackFragment;
    }

}