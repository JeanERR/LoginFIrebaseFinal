package com.example.erick.loginfirebasefinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class InicioSesion extends AppCompatActivity {

    private Button botonRegistrar;
    private EditText EdittextEmail;
    private EditText EdittextPass;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);


        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        botonRegistrar = (Button) findViewById(R.id.btnRegistrar);
        EdittextEmail = (EditText) findViewById(R.id.editTextoEmail);
        EdittextPass = (EditText) findViewById(R.id.editTextoContraseña);
    }




    private void RegistrarUsuario() {

        String email = EdittextEmail.getText().toString().trim();
        String contraseña = EdittextPass.getText().toString().trim();

        if (TextUtils.isEmpty(email)){

            Toast.makeText(this, "Ingresa tu Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(contraseña)){

            Toast.makeText(this, "Ingresa tu Contraseña", Toast.LENGTH_SHORT).show();
        }
        progressDialog.setMessage("Verificando usuario....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,contraseña)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss();

                        if (task.isSuccessful()){

                            finish();

                            Toast.makeText(InicioSesion.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();

                            Intent intent = null;

                            intent = new Intent(InicioSesion.this, PostSesion.class);

                            startActivity(intent);


                        }


                    }
                });


    }

    public void Registrar(View view){



        if (view.getId() == R.id.textViewirCrear){

            startActivity(new Intent(InicioSesion.this, CrearUsuario.class));


        }

        if (view.getId() == R.id.btnRegistrar){

            RegistrarUsuario();
        }




    }
}
