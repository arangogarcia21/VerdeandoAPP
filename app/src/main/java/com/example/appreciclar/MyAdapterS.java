package com.example.appreciclar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        Glide.with(context).load(dataSitiosVerdes.Imagen).into(holder.Imagen);

        holder.VerMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double latitud = dataSitiosVerdes.getLatitud();
                Double longitud = dataSitiosVerdes.getLongitud();

                Intent intent = new Intent(context, MapsActivity1.class);
                intent.putExtra("latitud", latitud);
                intent.putExtra("longitud", longitud);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sitiosVerdesArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Titulo, Descripcion;
        ImageView Imagen;
        Button VerMapa;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Titulo = itemView.findViewById(R.id.Titulo);
            Descripcion = itemView.findViewById(R.id.descripcion);

            Imagen = itemView.findViewById(R.id.img_item);
            VerMapa = itemView.findViewById(R.id.verMapa);
        }
    }





}