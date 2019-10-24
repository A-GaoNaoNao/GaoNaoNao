package com.example.day03.Mvp;

import com.example.day03.ItemList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("http://106.13.63.54:8080/sys/qm02a.json")
    Observable<ItemList>getItemList();
}
