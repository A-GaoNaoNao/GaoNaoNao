package com.example.day02_lxc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day02_lxc.Baen.ItemList;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MyadapterRecyc extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<ItemList.RecentBean> list;

    public MyadapterRecyc(Context context, ArrayList<ItemList.RecentBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ItemHolder itemHolder = (ItemHolder) holder;
        itemHolder.title.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getThumbnail()).into(itemHolder.iv);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (clickLong!=null){
                    clickLong.click(position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView title;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_item);
            title = itemView.findViewById(R.id.tv_title);
        }
    }

    interface ClickLong{
        void click(int index);
    }
    private ClickLong clickLong;

    public void setClickLong(ClickLong clickLong) {
        this.clickLong = clickLong;
    }
}
