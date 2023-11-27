package com.example.eda;

import android.view.KeyEvent;

import androidx.fragment.app.Fragment;

public interface CallBackFragment {
    void changeFragment(FragmentCallback fragment, boolean allowReturn);

    void changeFragmentCategory(FragmentCallback fragment, boolean allowReturn);

    void setNavigationVisibility(int state);

}
