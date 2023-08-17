package com.example.fotoalbum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.fotoalbum.Cim_stilus.Cim_Stilus_recyclerview;
import com.example.fotoalbum.Formatum.Formatum_recyclerview;

import com.example.fotoalbum.Stilus.Stilus_recyclerview;
import com.example.fotoalbum.adatbazisfotokonyvhoz.DBHelper1;
import com.example.fotoalbum.adatbazisfotokonyvhoz.fotokonyv1;

public class Projekt_Nev_Megadas extends AppCompatActivity {

    Button btn;
    EditText edt;
    EditText edt1;
    DBHelper1 db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_projekt_nev_megadas);

        btn=findViewById(R.id.Nev_button);
        edt=findViewById(R.id.Nev_beirt_szoveg);
        edt1=findViewById(R.id.Nev_beirt_oldalszam);
        db=new DBHelper1(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NevTXT=edt.getText().toString();
                String OldalTXT=edt1.getText().toString();
                Boolean checkinsertdata=db.adatbeadasa(NevTXT,"Null","Null","Null","Null","Null","Null","Null","Null","Null","Null","Null","Null");
                if(checkinsertdata==true){
                    Toast.makeText(Projekt_Nev_Megadas.this, "Siker", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Projekt_Nev_Megadas.this, "Nem siker", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(Projekt_Nev_Megadas.this, Cim_Stilus_recyclerview.class);
                i.putExtra("cs_nev",NevTXT);
                i.putExtra("o_nev",OldalTXT);
                startActivity(i);





            }
        });
    }
}