package com.example.appreciclar;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapterS extends RecyclerView.Adapter<MyAdapterS.MyViewHolder> {
    Context context;
    ArrayList<DataSitiosVerdes> sitiosVerdesArrayList;

    public MyAdapterS(Context context, ArrayList<DataSitiosVerdes> sitiosVerdesArrayList) {
        this.context = context;
        this.sitiosVerdesArrayList  = sitiosVerdesArrayList;
    }

    @NonNull
    @Override
    public MyAdapterS.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_sitiosverdes,parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterS.MyViewHolder holder, int position) {
        DataSitiosVerdes dataSitiosVerdes = sitiosVerdesArrayList.get(position);

        holder.Titulo.setText(dataSitiosVerdes.Titulo);
        holder.Descripcion.setText(dataSitiosVerdes.Descripcion);
        holder.Latitud.setText(dataSitiosVerdes.Latitud);
        holder.Longitud.setText(dataSitiosVerdes.Longitud);
        Glide.with(context).load(dataSitiosVerdes.Imagen).into(holder.Imagen);

    }

    @Override
    public int getItemCount() {
        return sitiosVerdesArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Titulo, Descripcion, Latitud, Longitud;
        ImageView Imagen;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Titulo = itemView.findViewById(R.id.Titulo);
            Descripcion = itemView.findViewById(R.id.descripcion);
            Latitud = itemView.findViewById(R.id.latitud);
            Longitud = itemView.findViewById(R.id.longitud);
            Imagen = itemView.findViewById(R.id.img_item);
        }
    }





}
