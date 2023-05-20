package com.example.appreciclar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class activity_registro extends AppCompatActivity  {
    EditText ingresarCorreo, ingresar_Contraseña, ingresarNombre, ingresarApellidos, edad, nCelular, direccion;

    Button btnRegistrarse, iniciarSesion;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        ingresarCorreo = findViewById(R.id.ingresarCorreo);
        ingresar_Contraseña = findViewById(R.id.ingresarContra);
        ingresarNombre = findViewById(R.id.ingresarNombre);
        ingresarApellidos = findViewById(R.id.ingresarApellidos);
        edad = findViewById(R.id.edad);
        nCelular = findViewById(R.id.nCelular);
        direccion = findViewById(R.id.direccion);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);
        iniciarSesion = findViewById(R.id.iniciarSesion);

        mAuth = FirebaseAuth.getInstance();

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_registro.this, Login.class));
            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = ingresarCorreo.getText().toString();
                String pass = ingresar_Contraseña.getText().toString();

                if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
                    ingresarCorreo.setError("Correo no válido");
                    ingresarCorreo.setFocusable(true);
                } else if (pass.length()<6) {
                    ingresar_Contraseña.setError("La contraseña debe tener más de 6 letras");
                    ingresar_Contraseña.setFocusable(true);
                } else {
                    REGISTRAR(correo, pass);
                }
            }
        });



    }

    /*Método para registrar un usuario*/
    private void REGISTRAR(String correo, String pass) {
        mAuth.createUserWithEmailAndPassword(correo, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();

                    assert user != null;
                    String uid = user.getUid();
                    String correo = ingresarCorreo.getText().toString();
                    String contraseña = ingresar_Contraseña.getText().toString();
                    String nombres = ingresarNombre.getText().toString();
                    String apellidos = ingresarApellidos.getText().toString();
                    String Edad = edad.getText().toString();
                    String celular = nCelular.getText().toString();
                    String Direccion = direccion.getText().toString();

                    /*Mandar datos al Database*/
                    HashMap<Object,String> DatosUsuario = new HashMap<>();

                    DatosUsuario.put("uid", uid);
                    DatosUsuario.put("correo", correo);
                    DatosUsuario.put("contraseña", contraseña);
                    DatosUsuario.put("nombre", nombres);
                    DatosUsuario.put("apellido", apellidos);
                    DatosUsuario.put("edad", Edad);
                    DatosUsuario.put("celular", celular);
                    DatosUsuario.put("direccion", Direccion);
                    DatosUsuario.put("imagen", "");

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference("Usuarios");
                    reference.child(uid).setValue(DatosUsuario);
                    Toast.makeText(activity_registro.this, "Se registró exitosamente", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(activity_registro.this, Perfil.class));
                } else {
                    Toast.makeText(activity_registro.this, "Algo ha salido mal", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity_registro.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}

