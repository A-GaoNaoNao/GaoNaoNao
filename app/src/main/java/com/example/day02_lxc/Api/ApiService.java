package com.example.day02_lxc.Api;

import com.example.day02_lxc.Baen.ItemList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("http://news-at.zhihu.com/api/4/news/hot")
    Observable<ItemList>getItemList();
}
