package com.example.appreciclar;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.media.session.MediaSessionManager;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Publicaciones extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<DataPublicaciones> PublicacionesArrayList;

    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicaciones);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Publicaciones.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(Publicaciones.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

         PublicacionesArrayList = new ArrayList<>();

         MyAdapterP myAdapterP = new MyAdapterP(Publicaciones.this,PublicacionesArrayList);
         recyclerView.setAdapter(myAdapterP);

         databaseReference = FirebaseDatabase.getInstance().getReference("Publicaciones");
         dialog.show();

         eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 PublicacionesArrayList.clear();
                 for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                     DataPublicaciones dataPublicaciones = itemSnapshot.getValue(DataPublicaciones.class);
                     PublicacionesArrayList.add(dataPublicaciones);
                 }
                 myAdapterP.notifyDataSetChanged();
                 dialog.dismiss();
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
             }
         });


    }
}