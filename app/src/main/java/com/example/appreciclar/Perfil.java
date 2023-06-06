package com.example.appreciclar;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class Perfil extends AppCompatActivity {

    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference BASE_DE_DATOS;

    Button misDatosOpcion, crearPublicacion, publicaciones, usuarios, chatOpcion;
    ImageView foto_perfil;
    TextView uidPerfil, correoPerfil, nombresPerfil;

    Button CerrarSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        BASE_DE_DATOS = firebaseDatabase.getReference("Usuarios");

        foto_perfil = findViewById(R.id.foto_perfil);
        uidPerfil = findViewById(R.id.uidPerfil);
        correoPerfil = findViewById(R.id.correoPerfil);
        nombresPerfil = findViewById(R.id.nombresPerfil);

        misDatosOpcion = findViewById(R.id.misDatosOpcion);
        crearPublicacion = findViewById(R.id.crearPublicacion);
        publicaciones = findViewById(R.id.publicaciones);
        CerrarSesion = findViewById(R.id.cSesion);

        String df= nombresPerfil.getText().toString();

        publicaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Perfil.this, Publicaciones.class));
            }
        });

        crearPublicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Perfil.this, CrearPost.class);
                i.putExtra("Dato",df);
                startActivity(i);
            }
        });

        misDatosOpcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Perfil.this, MisDatos.class));
                Toast.makeText(Perfil.this, "Mis Datos", Toast.LENGTH_SHORT).show();
            }
        });

        CerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CerrarSesion();
            }
        });

    }

    @Override
    protected void onStart() {
        VerificarInicioSesion();
        super.onStart();
    }

    private void VerificarInicioSesion (){
        if (firebaseUser != null){
            CargarDatos();
            Toast.makeText(this, "Se ha iniciado sesión", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(Perfil.this, Login.class));
            finish();
        }
    }

    public void CargarDatos(){
        Query query = BASE_DE_DATOS.orderByChild("correo").equalTo(firebaseUser.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){

                    String uid = ""+ds.child("uid").getValue();
                    String correo = ""+ds.child("correo").getValue();
                    String nombre = ""+ds.child("nombre").getValue();
                    String imagen = ""+ds.child("imagen").getValue();

                    uidPerfil.setText(uid);
                    correoPerfil.setText(correo);
                    nombresPerfil.setText(nombre);

                    try {
                        Picasso.get().load(imagen).placeholder(R.drawable.img_perfil).into(foto_perfil);
                    } catch (Exception e){
                        Picasso.get().load(R.drawable.img_perfil).into(foto_perfil);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void CerrarSesion(){
        firebaseAuth.signOut();
        Toast.makeText(this, "Ha cerrado sesión", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(Perfil.this,Login.class) );
    }

    }






