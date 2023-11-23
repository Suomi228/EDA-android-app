package com.example.eda;

import android.view.KeyEvent;

import androidx.fragment.app.Fragment;

public interface CallBackFragment {
    void changeFragment(Fragment fragment, boolean allowReturn);
    void setNavigationVisibility(int state);
}
