package com.example.day03.Mvp;

import com.example.day03.ItemList;

public class MinaPertent {
    public void Retrofit(final MinaView minaView){
        MinaMedlo medlo = new MinaMedlo();
        medlo.Retrofit(new Minaballck() {
            @Override
            public void ChengGong(ItemList itemList) {
                if (minaView!=null){
                    if (itemList!=null){
                        minaView.Cheng(itemList);
                    }
                }
            }

            @Override
            public void ShiBai(Throwable e) {
                if (minaView!=null && e!=null){
                    minaView.Shi(e);
                }
            }
        });
    }
}
