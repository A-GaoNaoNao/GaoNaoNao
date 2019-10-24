package com.example.day03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MyadapterRecycXia extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<ItemList.HxListBeansBean> listBeansBeans;

    public MyadapterRecycXia(Context context, ArrayList<ItemList.HxListBeansBean> listBeansBeans) {
        this.context = context;
        this.listBeansBeans = listBeansBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListHolder listHolder = (ListHolder) holder;
        listHolder.title.setText(listBeansBeans.get(position).getShop_title());
        listHolder.desc.setText(listBeansBeans.get(position).getShop_content());
        Glide.with(context).load(listBeansBeans.get(position).getShop_image_url()).into(listHolder.iv);
    }

    @Override
    public int getItemCount() {
        return listBeansBeans.size();
    }
    class ListHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView title;
        private TextView desc;
        public ListHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_xia);
            title = itemView.findViewById(R.id.tv_xia);
            desc = itemView.findViewById(R.id.desc_xia);
        }
    }
}
