package com.example.day03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.day03.Mvp.MinaPertent;
import com.example.day03.Mvp.MinaView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MinaView, MyadapterRecycShang.Clickler {

    private RecyclerView re;
    private RecyclerView re_item;
    private ArrayList<ItemList.GgBeanBean> ggBeanBeans;
    private ArrayList<ItemList.HxListBeansBean> hxListBeansBeans;
    private MyadapterRecycShang myadapterRecycShang;
    private MyadapterRecycXia myadapterRecycXia;
    private int mPotision;
    private String gg_title;
    private String ggIma_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        re = findViewById(R.id.re_shang);

        re.setLayoutManager(new GridLayoutManager(this,4));
        re_item = findViewById(R.id.re_xia);

        re_item.setLayoutManager(new LinearLayoutManager(this));

        ggBeanBeans = new ArrayList<>();
        hxListBeansBeans = new ArrayList<>();

        MinaPertent pertent = new MinaPertent();
        pertent.Retrofit(this);

        myadapterRecycShang = new MyadapterRecycShang(this, ggBeanBeans);
        myadapterRecycXia = new MyadapterRecycXia(this, hxListBeansBeans);

        re.setAdapter(myadapterRecycShang);
        re_item.setAdapter(myadapterRecycXia);

        myadapterRecycShang.setClickler(this);
    }

    @Override
    public void Cheng(ItemList itemList) {
        ArrayList<ItemList.GgBeanBean> ggBean = (ArrayList<ItemList.GgBeanBean>) itemList.getGgBean();
        ggBeanBeans.addAll(ggBean);
        myadapterRecycShang.notifyDataSetChanged();
        ArrayList<ItemList.HxListBeansBean> hxListBeans = (ArrayList<ItemList.HxListBeansBean>) itemList.getHxListBeans();
        hxListBeansBeans.addAll(hxListBeans);
        myadapterRecycXia.notifyDataSetChanged();
        EventBus.getDefault().post(new EventList(itemList));
    }

    @Override
    public void Shi(Throwable e) {
        Log.i("Gao", "Shi: "+e);
    }

    @Override
    public void click(int index) {
        mPotision = index;
        startActivity(new Intent(MainActivity.this,Main2Activity.class));
    }
}
