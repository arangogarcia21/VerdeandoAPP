package com.example.appreciclar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class CrearPost extends AppCompatActivity {
    ImageView subir_img;
    Button guardarPost;
    EditText nombrePost, descPost, califPost;

    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;




    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_post);


        Bundle extras = getIntent().getExtras();
        String Dato = extras.getString("Dato");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        subir_img = findViewById(R.id.subir_img);
        guardarPost = findViewById(R.id.guardarPost);
        nombrePost = findViewById(R.id.NombrePost);
        descPost = findViewById(R.id.DescPost);
        califPost = findViewById(R.id.CalifPost);



        guardarPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getTitulo = nombrePost.getText().toString();
                String getDesc = descPost.getText().toString();
                String getCalif = califPost.getText().toString();
                String getNomPerfil = Dato;


                HashMap<String,Object> hasMap = new HashMap<>();
                hasMap.put("Titulo",getTitulo);
                hasMap.put("Descripcion",getDesc);
                hasMap.put("Calificacion", getCalif);
                hasMap.put("Creador", getNomPerfil);



                databaseReference.child("Publicaciones")
                        .child(getTitulo)
                        .setValue(hasMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(CrearPost.this, "Creado con exito", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CrearPost.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

    }


}