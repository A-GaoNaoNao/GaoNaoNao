package com.example.day02_lxc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day02_lxc.Baen.Person;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MyadapterRecyc2 extends RecyclerView.Adapter {
    private Context context;
    private List<Person> list;

    public MyadapterRecyc2(Context context, List<Person> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_cha, parent, false);
        return new ItemListChaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ItemListChaHolder itemListChaHolder = (ItemListChaHolder) holder;
        itemListChaHolder.title.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImg()).into(itemListChaHolder.iv);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (clickLog!=null){
                    clickLog.click(position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemListChaHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView title;
        public ItemListChaHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_item_cha);
            title = itemView.findViewById(R.id.tv_title_cha);
        }
    }

    interface ClickLog{
        void click(int index);
    }
    private ClickLog clickLog;

    public void setClickLog(ClickLog clickLog) {
        this.clickLog = clickLog;
    }
}
