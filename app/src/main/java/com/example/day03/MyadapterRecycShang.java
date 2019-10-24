package com.example.day03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MyadapterRecycShang extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<ItemList.GgBeanBean> list;

    public MyadapterRecycShang(Context context, ArrayList<ItemList.GgBeanBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_gong, parent, false);
        return new GgHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        GgHolder ggHolder = (GgHolder) holder;
        ggHolder.title.setText(list.get(position).getGg_title());
        Glide.with(context).load(list.get(position).getGgIma_url())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(ggHolder.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickler!=null){
                    clickler.click(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class GgHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView title;
        public GgHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_shang);
            title = itemView.findViewById(R.id.tv_shang);
        }
    }
    interface Clickler{
        void click(int index);
    }
    private Clickler clickler;

    public void setClickler(Clickler clickler) {
        this.clickler = clickler;
    }
}
