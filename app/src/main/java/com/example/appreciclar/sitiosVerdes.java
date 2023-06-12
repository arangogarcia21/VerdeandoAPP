package com.example.appreciclar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class sitiosVerdes extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<DataSitiosVerdes> sitiosVerdesArrayList;
    MyAdapterS myAdapterS;
    FirebaseFirestore db;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitios_verdes);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        sitiosVerdesArrayList = new ArrayList<DataSitiosVerdes>();
        myAdapterS = new MyAdapterS(sitiosVerdes.this,sitiosVerdesArrayList);

        recyclerView.setAdapter(myAdapterS);

        EventChangeListener();

    }

    private void EventChangeListener(){

        db.collection("Sitios").orderBy("Descripcion", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null){


                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()){

                            if (dc.getType()== DocumentChange.Type.ADDED){
                                sitiosVerdesArrayList.add(dc.getDocument().toObject(DataSitiosVerdes.class));

                            }
                            myAdapterS.notifyDataSetChanged();

                        }
                    }
                });

    }
}