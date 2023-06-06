package com.example.appreciclar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterP extends RecyclerView.Adapter<MyAdapterP.MyViewHolder> {
    Context context;
    ArrayList<DataPublicaciones> publicacionesArrayList;

    public MyAdapterP(Context context, ArrayList<DataPublicaciones> publicacionesArrayList) {
        this.context = context;
        this.publicacionesArrayList = publicacionesArrayList;
    }

    @NonNull
    @Override
    public MyAdapterP.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_publicacion,parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterP.MyViewHolder holder, int position) {
        DataPublicaciones dataPublicaciones = publicacionesArrayList.get(position);

        holder.NombreUsuario.setText(dataPublicaciones.Creador);
        holder.item_titulo.setText(dataPublicaciones.Titulo);
        holder.item_desc.setText(dataPublicaciones.Descripcion);
        holder.item_calif.setText(dataPublicaciones.Calificacion);

    }

    @Override
    public int getItemCount() {
        return publicacionesArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView NombreUsuario, item_titulo, item_desc, item_calif;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            NombreUsuario = itemView.findViewById(R.id.NombreUsuario);
            item_calif = itemView.findViewById(R.id.item_calif);
            item_titulo = itemView.findViewById(R.id.item_titulo);
            item_desc = itemView.findViewById(R.id.item_desc);
        }
    }





}
