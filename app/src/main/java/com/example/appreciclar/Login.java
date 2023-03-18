package com.example.appreciclar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button btnRegistrar,btnLogin;
    EditText ingresarEmail,ingresarContraseña;

    FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;
    
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_login);
        ingresarEmail = (EditText)findViewById(R.id.ingresarEmail);
        ingresarContraseña = (EditText)findViewById(R.id.ingresarContraseña);
        btnLogin=findViewById(R.id.Login);
        btnRegistrar=(Button)findViewById(R.id.btnRegistrar);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(Login.this,activity_registro.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarCredenciales();
            }
        });

    }

    private void verificarCredenciales() {

        String email = ingresarEmail.getText().toString();
        final String pass = ingresarContraseña.getText().toString();

        if (email.isEmpty() || !email.contains("@")){
            Toast.makeText(this, "Correo invalido", Toast.LENGTH_SHORT).show();
            return;
        } else if (pass.isEmpty() || pass.length()<6) {
            Toast.makeText(this, "Contraseña invalida", Toast.LENGTH_SHORT).show();
            return;
        } else {
            firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                         Intent intent = new Intent(Login.this,activity_registro.class);
                         intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Rellena los campos", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            });
        }
        progressDialog.setMessage("INGRESANDO A LA APLICACION");
        progressDialog.show();
         firebaseAuth.signInWithEmailAndPassword(email, pass);{
            Toast.makeText(this, "VERIFICANDO CREDENCIALES", Toast.LENGTH_SHORT).show();
        }


        }

    }


