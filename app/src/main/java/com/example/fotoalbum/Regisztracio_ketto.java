package com.example.fotoalbum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Regisztracio_ketto extends AppCompatActivity {
    EditText email,jelszo;
    Button regisztracio,bejelentkezes;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_regisztracio_ketto);

        email=findViewById(R.id.Felhasznalo_masodikmodszer);
        jelszo=findViewById(R.id.Jelszo_masodikmodszer);
        regisztracio=findViewById(R.id.Regisztraciogomb_masodikmodszer);
        bejelentkezes=findViewById(R.id.Bejelentkezesgomb_masodikmodszer);
        mAuth=FirebaseAuth.getInstance();

        regisztracio.setOnClickListener(view ->{
            createUser();
        });

        bejelentkezes.setOnClickListener(view -> {
            startActivity(new Intent(Regisztracio_ketto.this,Bejelentkezes_ketto.class));
        });
    }

    private void createUser(){
        String Email = email.getText().toString();
        String Jelszo=jelszo.getText().toString();

        if(TextUtils.isEmpty(Email)){
            email.setError("Email nem lehet üres");
            email.requestFocus();
        }else if (TextUtils.isEmpty(Jelszo)){
            jelszo.setError("Jelszó nem lehet üres");
            jelszo.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(Email,Jelszo).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Regisztracio_ketto.this,"Sikeres regisztráció",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Regisztracio_ketto.this,Bejelentkezes_ketto.class));
                    }else{
                        Toast.makeText(Regisztracio_ketto.this,"Sikertelen regisztráció: "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

}