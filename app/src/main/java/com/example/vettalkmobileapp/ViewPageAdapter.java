package com.example.vettalkmobileapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPageAdapter extends FragmentPagerAdapter{

    public ViewPageAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return Step1Fragment.getInstance();
            case 1:
                return Step2Fragment.getInstance();
            case 2:
                return Step3Fragment.getInstance();
            case 3:
                return Step4Fragment.getInstance();
        }
        return null;
    }

    @Override
    public int getCount() {

        return 4;
    }
}
