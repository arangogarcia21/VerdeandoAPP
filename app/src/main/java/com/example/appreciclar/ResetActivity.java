package com.example.appreciclar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetActivity extends AppCompatActivity {

    private EditText resetContraseña;
    private Button ejeCambio;
    private String email = "";
    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        mDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        resetContraseña = (EditText) findViewById(R.id.resetContraseña);
        ejeCambio = (Button) findViewById(R.id.ejeCambio);

        resetContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = resetContraseña.getText().toString();

                if (!email.isEmpty()) {

                    mDialog.setMessage("Espera, por favor!");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                    resetPass();
                }else{

                    Toast.makeText(ResetActivity.this, "Por favor ingrese un correo", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void resetPass() {

        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(ResetActivity.this, "Se ha enviado un correo para restablecer tu contraseña", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ResetActivity.this, "No se pudo enviar el correo para restablecer la contrasña", Toast.LENGTH_SHORT).show();
                }

            }
        });
        mDialog.dismiss();
    }
}