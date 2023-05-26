package com.example.appreciclar;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;



import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<DataClass> dataList;


    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @androidx.annotation.NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_publicacion, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull MyViewHolder holder, int position) {

        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.img_item);
        holder.titulo_item.setText(dataList.get(position).getDataNombre());
        holder.desc_item.setText(dataList.get(position).getDataDesc());
        holder.calif_item.setText(dataList.get(position).getDataCalif());


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView img_item;
    TextView titulo_item, desc_item, calif_item;
    CardView card_item;

    public MyViewHolder(@NonNull View itemView){
        super(itemView);

        img_item = itemView.findViewById(R.id.img_item);
        titulo_item = itemView.findViewById(R.id.item_titulo);
        desc_item = itemView.findViewById(R.id.item_desc);
        calif_item = itemView.findViewById(R.id.item_calif);
        card_item = itemView.findViewById(R.id.card_item);
    }
}
