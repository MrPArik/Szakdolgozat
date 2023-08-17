package com.example.fotoalbum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fotoalbum.bejelentkezes.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class Bejelentkezes_ketto extends AppCompatActivity {
    EditText email,jelszo;
    Button bejelentkezes,regisztralas,nyelvbtn;
    FirebaseAuth mAuth;
    String nyelv="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_bejelentkezes_ketto);

        email=findViewById(R.id.Felhasznalo1_masodikmodszer);
        jelszo=findViewById(R.id.Jelszo1_masodikmodszer);
        bejelentkezes=findViewById(R.id.Bejelentkezesgomb1_masodikmodszer);
        regisztralas=findViewById(R.id.Regisztraciogomb1_masodikmodszer);
        mAuth=FirebaseAuth.getInstance();
        nyelvbtn=findViewById(R.id.button10);
        bejelentkezes.setOnClickListener(view -> {
            loginUser();
        });
        regisztralas.setOnClickListener(view -> {
            startActivity(new Intent(Bejelentkezes_ketto.this,Regisztracio_ketto.class));
        });
        nyelvbtn.setOnClickListener(view -> {
            nyelv = nyelvbtn.getText().toString();

            finish();
            startActivity(getIntent());
            if(nyelv.equals("Languages")){
            setLocal(Bejelentkezes_ketto.this,"hu");
            finish();
            startActivity(getIntent());
            }else if(nyelv.equals("Nyelv")){
                setLocal(Bejelentkezes_ketto.this,"en");
                finish();
                startActivity(getIntent());
            }
        });
    }

    public void setLocal(Activity activity,String langcode){
        Locale locale=new Locale(langcode);
        locale.setDefault(locale);
        Resources resources=activity.getResources();
        Configuration config=resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config,resources.getDisplayMetrics());
    }

    private  void loginUser(){
        String Email = email.getText().toString();
        String Jelszo=jelszo.getText().toString();

        if(TextUtils.isEmpty(Email)){
            email.setError("Email nem lehet üres");
            email.requestFocus();
        }else if (TextUtils.isEmpty(Jelszo)){
            jelszo.setError("Jelszó nem lehet üres");
            jelszo.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(Email,Jelszo).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Bejelentkezes_ketto.this,"Sikeres bejelentkezés",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Bejelentkezes_ketto.this,FOoldal.class));
                    }else{
                        Toast.makeText(Bejelentkezes_ketto.this,"Sikertelen bejelentkezés: "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}