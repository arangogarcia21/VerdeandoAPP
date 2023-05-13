package com.example.appreciclar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appreciclar.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MisDatos extends AppCompatActivity {

    ImageView ImagenDato;

    TextView uidDato, NombreDato, ApellidosDatos, CorreoDato, ContraseñaDato, EdadDato, TelefonoDato, DireccionDato;

    Button ActualizarDatos, ActualizarPass;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference BASE_DE_DATOS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_datoss);

        ImagenDato = findViewById(R.id.ImagenDato);
        uidDato = findViewById(R.id.uidDato);
        NombreDato = findViewById(R.id.NombreDato);
        ApellidosDatos = findViewById(R.id.ApellidosDatos);
        CorreoDato = findViewById(R.id.CorreoDato);
        ContraseñaDato = findViewById(R.id.ContraseñaDato);
        EdadDato = findViewById(R.id.EdadDato);
        TelefonoDato = findViewById(R.id.TelefonoDato);
        DireccionDato = findViewById(R.id.DireccionDato);

        ActualizarDatos = findViewById(R.id.ActualizarDatos);
        ActualizarPass = findViewById(R.id.ActualizarPass);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        BASE_DE_DATOS = FirebaseDatabase.getInstance().getReference("Usuarios");

        BASE_DE_DATOS.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    String uid = ""+snapshot.child("uid").getValue();
                    String nombre = ""+snapshot.child("nombre").getValue();
                    String apellido = ""+snapshot.child("apellido").getValue();
                    String correo = ""+snapshot.child("correo").getValue();
                    String contraseña = ""+snapshot.child("contraseña").getValue();
                    String direccion = ""+snapshot.child("direccion").getValue();
                    String edad = ""+snapshot.child("edad").getValue();
                    String celular = ""+snapshot.child("celular").getValue();
                    String imagen = ""+snapshot.child("imagen").getValue();

                    uidDato.setText(uid);
                    NombreDato.setText(nombre);
                    ApellidosDatos.setText(apellido);
                    CorreoDato.setText(correo);
                    ContraseñaDato.setText(contraseña);
                    DireccionDato.setText(direccion);
                    EdadDato.setText(edad);
                    TelefonoDato.setText(celular);

                    try {

                        Picasso.get().load(imagen).placeholder(R.drawable.img_perfil).into(ImagenDato);

                    } catch (Exception e){

                        Picasso.get().load(R.drawable.img_perfil).into(ImagenDato);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}