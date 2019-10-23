package com.example.day03.Mvp;

import com.example.day03.ItemList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MinaMedlo {
    public void Retrofit(final Minaballck minaballck){
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
                        if (minaballck!=null){
                            minaballck.ChengGong(itemList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (minaballck!=null){
                            minaballck.ShiBai(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
