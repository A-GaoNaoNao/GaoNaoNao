package com.example.day02_lxc.Pertent;

import com.example.day02_lxc.Baen.ItemList;
import com.example.day02_lxc.Medlo.MinaMedlo;
import com.example.day02_lxc.Medlo.Minallback;

public class MinaPertent {
    public void Retrofit(final MinaView minaView){
        MinaMedlo medlo = new MinaMedlo();
        medlo.Retrofit(new Minallback() {
            @Override
            public void ChengGong(ItemList itemList) {
                if (minaView!=null && itemList!=null){
                    minaView.Cheng(itemList);
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
