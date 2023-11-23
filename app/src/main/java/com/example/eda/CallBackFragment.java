package com.example.eda;

import androidx.fragment.app.Fragment;

public interface CallBackFragment {
    void changeFragment(Fragment fragment);
    void setNavigationVisibility(int state);
}
