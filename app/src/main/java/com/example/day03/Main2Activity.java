package com.example.day03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.day03.Mvp.ApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements MyadapterRecyc.Clickler {

    private RecyclerView re;
    private MyadapterRecyc myadapterRecyc;
    private ArrayList<ItemList.GgBeanBean> ggBeanBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //EventBus.getDefault().register(this);
        initView2();
    }

    private void initView2() {
        ggBeanBeans = new ArrayList<>();

        re = findViewById(R.id.review);

        re.setLayoutManager(new LinearLayoutManager(this));

        myadapterRecyc = new MyadapterRecyc(this, ggBeanBeans);

        re.setAdapter(myadapterRecyc);

        initRetrofit();

        myadapterRecyc.setClickler(this);
    }

    /*@Subscribe()
    public void initView(EventList eventList) {

    }
*/
    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://106.13.63.54:8080/sys/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getItemList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ItemList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ItemList itemList) {
                        ArrayList<ItemList.GgBeanBean> ggBean = (ArrayList<ItemList.GgBeanBean>) itemList.getGgBean();
                        ggBeanBeans.addAll(ggBean);
                        myadapterRecyc.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Gao", "onError: "+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void click(int index) {
        String gg_title = ggBeanBeans.get(index).getGg_title();
        String ggIma_url = ggBeanBeans.get(index).getGgIma_url();
        String ggWeb_url = ggBeanBeans.get(index).getGgWeb_url();
        Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
        intent.putExtra("title",gg_title);
        intent.putExtra("ima",ggIma_url);
        intent.putExtra("web",ggWeb_url);
        startActivity(intent);
    }
}
