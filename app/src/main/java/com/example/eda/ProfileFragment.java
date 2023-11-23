package com.example.eda;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileFragment extends Fragment {
    FirebaseAuth auth;
    Button logout;
    TextView user_name;
    FirebaseUser user;
    CallBackFragment callBackFragment;
    public void onStart() {
        super.onStart();
        if (user == null){
            if (callBackFragment!=null){
                callBackFragment.changeFragment(new LoginFragment());
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        auth = FirebaseAuth.getInstance();
        user_name = (TextView)getView().findViewById(R.id.user_name);
        logout = (Button) getView().findViewById(R.id.logout);
        user = auth.getCurrentUser();
        if (user == null){
            if (callBackFragment!=null){
                callBackFragment.changeFragment(new LoginFragment());
            }
        }
        else{
            user_name.setText(user.getEmail());
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder a_builder = new AlertDialog.Builder(getActivity());
                a_builder.setMessage("Вы уверены что хотите выйти из аккаунта?")
                                .setCancelable(false)
                                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                FirebaseAuth.getInstance().signOut();
                                                if (callBackFragment!=null){
                                                    callBackFragment.changeFragment(new LoginFragment());
                                                }
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

}