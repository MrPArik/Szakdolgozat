package com.example.fotoalbum.bejelentkezes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fotoalbum.Cim_stilus.Cim_Stilus_recyclerview;
import com.example.fotoalbum.FOoldal;
import com.example.fotoalbum.R;

public class Bejelentkezes extends AppCompatActivity {
EditText felhasznalonev,jelszo;
Button bejelentkezes,regisztralas;
DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bejelentkezes);

        felhasznalonev=findViewById(R.id.Felhasznalo1);
        jelszo=findViewById(R.id.Jelszo1);
        bejelentkezes=findViewById(R.id.Bejelentkezesgomb1);
        regisztralas=findViewById(R.id.Regisztraciogomb1);
        DB=new DBHelper(this);

        bejelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user1=felhasznalonev.getText().toString();
                String pass1=jelszo.getText().toString();
                if (user1.equals("") || pass1.equals("")) {
                    Toast.makeText(Bejelentkezes.this, "Töltsd ki az összes mezőt", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuserpass=DB.checkfelhasznalonevjelszo(user1,pass1);
                    if(checkuserpass==true){
                        Toast.makeText(Bejelentkezes.this, "Bejelentkezés sikeres volt", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), FOoldal.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Bejelentkezes.this, "Sikertelen belépés", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        regisztralas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}