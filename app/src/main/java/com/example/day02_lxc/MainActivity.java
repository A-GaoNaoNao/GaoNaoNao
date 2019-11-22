package com.example.day02_lxc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.example.day02_lxc.Baen.TabList;
import com.example.day02_lxc.adapter.MyadapterFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private ShowFragment showFragment;
    private CangFragment cangFragment;
    public ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        tab = findViewById(R.id.tab);
        pager = findViewById(R.id.pager);

        ArrayList<TabList> tabLists = new ArrayList<>();
        tabLists.add(new TabList("我的"));
        tabLists.add(new TabList("收藏"));


        showFragment = new ShowFragment();
        cangFragment = new CangFragment();

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(showFragment);
        fragments.add(cangFragment);

        MyadapterFragment myadapterFragment = new MyadapterFragment(getSupportFragmentManager(), fragments);

        pager.setAdapter(myadapterFragment);

        tab.setupWithViewPager(pager);

        for (int i = 0; i < fragments.size(); i++) {
            tab.getTabAt(i).setText(tabLists.get(i).getName());
        }

    }


}
