package com.example.appreciclar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_registro extends AppCompatActivity implements View.OnClickListener  {

    Button btnRegistrarse, iniciarSesion;
    EditText ingresarCorreo,ingresar_Contraseña,ingresarNombre;

    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ingresarNombre = findViewById(R.id.ingresarNombre);
        ingresarCorreo = findViewById(R.id.ingresarCorreo);
        ingresar_Contraseña = findViewById(R.id.ingresar_Contraseña);
        btnRegistrarse =findViewById(R.id.btnRegistrarse);
        iniciarSesion = findViewById(R.id.iniciarSesion);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        btnRegistrarse.setOnClickListener(this);

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_registro.this,Login.class));

            }
        });

        };

    private void registrarUsuario(){
        String nombre = ingresarNombre.getText().toString().trim();
        String email = ingresarCorreo.getText().toString().trim();
        String pass = ingresar_Contraseña.getText().toString().trim();

        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(this, "Debes ingresar un nombre", Toast.LENGTH_SHORT).show();

        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Debes ingresar un correo válido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Debes ingresar una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Realizando registro en línea");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Toast.makeText(activity_registro.this,"¡Registro con éxito!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(activity_registro.this,"No se pudo registrar, verifica los campos.", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
                }
        });
        }


    @Override
    public void onClick(View v) {
    registrarUsuario();
    }

}


