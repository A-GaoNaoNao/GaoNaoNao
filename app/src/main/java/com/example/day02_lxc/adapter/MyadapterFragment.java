package com.example.day02_lxc.adapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyadapterFragment extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;

    public MyadapterFragment(@NonNull FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }

    public MyadapterFragment(@NonNull FragmentManager fm, int behavior, ArrayList<Fragment> list) {
        super(fm, behavior);
        this.list = list;
    }

    public MyadapterFragment(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
