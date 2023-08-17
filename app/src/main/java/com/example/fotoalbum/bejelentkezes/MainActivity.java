package com.example.fotoalbum.bejelentkezes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fotoalbum.FOoldal;
import com.example.fotoalbum.R;

public class MainActivity extends AppCompatActivity {
    EditText felhasznalo,jelszo,jelszo_ujra;
    Button regisztracio,bejelentkezes;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        felhasznalo=findViewById(R.id.Felhasznalo);
        jelszo=findViewById(R.id.Jelszo);
        jelszo_ujra=findViewById(R.id.JelszoUjra);
        regisztracio=findViewById(R.id.Regisztraciogomb);
        bejelentkezes=findViewById(R.id.Bejelentkezesgomb);
        DB=new DBHelper(this);

        regisztracio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = felhasznalo.getText().toString();
                String pass = jelszo.getText().toString();
                String repass = jelszo_ujra.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals("")) {
                    Toast.makeText(MainActivity.this, "Töltsd ki az összes mezőt", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkfelhasznalonev(user);

                        if (checkuser == false) {
                            Boolean insert=DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(MainActivity.this,"Sikeres regisztráció",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(), FOoldal.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(MainActivity.this,"Sikertelen regisztráció",Toast.LENGTH_SHORT).show();
                            }

                        }
                        else{
                            Toast.makeText(MainActivity.this,"Felhasználó már létezik kérem válasszon másik felhasználót",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Jelszavak nem eggyeznek",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        bejelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(getApplicationContext(), Bejelentkezes.class);
                startActivity(intent2);


            }
        });



    }
}