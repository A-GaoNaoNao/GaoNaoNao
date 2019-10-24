package com.example.day02_lxc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements TabLayout.BaseOnTabSelectedListener {

    private TabLayout tab;
    private ShowFragment showFragment;
    private CangFragment cangFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        tab = findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("首页"));
        tab.addTab(tab.newTab().setText("收藏"));

        showFragment = new ShowFragment();
        cangFragment = new CangFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fram,showFragment).commit();

        tab.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        if (position==0){
            getSupportFragmentManager().beginTransaction().replace(R.id.fram,showFragment).commit();
        }
        if (position == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.fram,cangFragment).commit();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
