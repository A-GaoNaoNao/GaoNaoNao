package com.example.day02_lxc.Medlo;

import com.example.day02_lxc.Api.ApiService;
import com.example.day02_lxc.Baen.ItemList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MinaMedlo {
    public void Retrofit(final Minallback minallback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/")
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
                        if (minallback!=null){
                            minallback.ChengGong(itemList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (minallback!=null){
                            minallback.ShiBai(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
