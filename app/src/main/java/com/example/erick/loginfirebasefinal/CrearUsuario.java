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

public class CrearUsuario extends AppCompatActivity {

    private Button buttonCrear;
    private EditText editTextEmail;
    private EditText editTextPass;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        buttonCrear = (Button) findViewById(R.id.btnCrear);
        editTextEmail = (EditText) findViewById(R.id.editTextoEmail);
        editTextPass = (EditText) findViewById(R.id.editTextoContraseña);
    }


    public void Nuevousuario(View v){



        if (v.getId() == R.id.textViewirRegistrar){

            startActivity(new Intent(CrearUsuario.this, InicioSesion.class));




        }
        if (v.getId() == R.id.btnCrear){

            CrearUsuario();
        }
    }

    private void CrearUsuario() {

        String email =editTextEmail.getText().toString().trim();
        String contraseña = editTextPass.getText().toString().trim();

        //MENSAJES SI LOS CAMPOS ESTAN VACIOS

        if (TextUtils.isEmpty(email)){


            Toast.makeText(this,"Ingrese un Email",Toast.LENGTH_SHORT).show();
            return;
        }



        if (TextUtils.isEmpty(contraseña)){

            Toast.makeText(this,"Ingrese una Contraseña",Toast.LENGTH_SHORT).show();
            return;
        }


        // si se registra bien cargara un progressdialog

        progressDialog.setMessage("Registrando Nuevo Usuario...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,contraseña)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            finish();



                            Toast.makeText(CrearUsuario.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();

                            Intent intent = null;
                            intent = new Intent(CrearUsuario.this, InicioSesion.class);
                            startActivity(intent);

                        }
                        else {

                            Toast.makeText(CrearUsuario.this, "Ha ocurrido un error al registrarse", Toast.LENGTH_SHORT).show();

                        }

                        progressDialog.dismiss();
                    }
                });
    }
}
