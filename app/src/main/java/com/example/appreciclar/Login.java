package com.example.appreciclar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Login extends AppCompatActivity {
    Button btnRegistrar, btnLogin;
    EditText ingresarEmail, ingresarContraseña;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_login);
        ingresarEmail = (EditText) findViewById(R.id.ingresarEmail);
        ingresarContraseña = (EditText) findViewById(R.id.ingresarContra);
        btnLogin = findViewById(R.id.Login);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        dialog = new Dialog(Login.this);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Activity_registro.class));

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = ingresarEmail.getText().toString();
                String pass = ingresarContraseña.getText().toString();

                if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
                    ingresarEmail.setError("Correo inválido");
                    ingresarEmail.setFocusable(true);
                } else if (pass.length()<6) {
                    ingresarContraseña.setError("Contraseña inválida");
                    ingresarContraseña.setFocusable(true);
                } else {
                    LOGINUSUARIO(correo,pass);
                }
            }
        });


    }


    //MÉTODO PARA INICIAR SESIÓN
    private void LOGINUSUARIO(String correo, String pass) {

        progressDialog.setCancelable(false);
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(correo, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    startActivity(new Intent(Login.this,Perfil.class));
                    Toast.makeText(Login.this, "Bienvenido a Verdeando " +user.getEmail(), Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(Login.this,"Algo ha salido mal", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}