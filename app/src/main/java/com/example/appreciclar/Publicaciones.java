package com.example.appreciclar;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.ProgressDialog;
import android.media.session.MediaSessionManager;
import android.os.Bundle;
import android.util.Log;

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
    MyAdapterP myAdapterP;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicaciones);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("fetching Data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        PublicacionesArrayList = new ArrayList<DataPublicaciones>();
        myAdapterP = new MyAdapterP(Publicaciones.this,PublicacionesArrayList);

        recyclerView.setAdapter(myAdapterP);

        EventChangeListener();

        }

        private void EventChangeListener(){

            db.collection("Publicaciones").orderBy("Creador", Query.Direction.ASCENDING)
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            if (error != null){

                                if(progressDialog.isShowing())
                                    progressDialog.dismiss();
                                Log.e("Firestore Error",error.getMessage());
                                return;
                            }
                            for (DocumentChange dc : value.getDocumentChanges()){
                                if (dc.getType()== DocumentChange.Type.ADDED){
                                    PublicacionesArrayList.add(dc.getDocument().toObject(DataPublicaciones.class));

                                }
                                myAdapterP.notifyDataSetChanged();
                                if(progressDialog.isShowing())
                                    progressDialog.dismiss();
                            }


                        }
                    });




        }
}