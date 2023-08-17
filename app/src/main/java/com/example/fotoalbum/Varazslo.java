package com.example.fotoalbum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fotoalbum.Cim_stilus.Cim_Stilus_recyclerview;
import com.example.fotoalbum.adatbazisfotokonyvhoz.DBHelper1;
import com.example.fotoalbum.fotokonyv_oldalak.Elso_oldal;

import java.util.Random;

public class Varazslo extends AppCompatActivity {

    final int a = new Random().nextInt(5) ;
    final int b = new Random().nextInt(4) ;
    final int c = new Random().nextInt(4) ;
    final int d = new Random().nextInt(4) ;
    final int e = new Random().nextInt(4) ;
    final int f = new Random().nextInt(4) ;
    final int g = new Random().nextInt(4) ;
    final int h = new Random().nextInt(4) ;
    final int i = new Random().nextInt(4) ;
    final int j = new Random().nextInt(4) ;
    final int k = new Random().nextInt(4) ;
    final int l = new Random().nextInt(4) ;
    Button btn;
    EditText text;
    DBHelper1 db;
    String cimstilus="",oldal1="",oldal2="",oldal3="",oldal4="",oldal5="",oldal6="",oldal7="",oldal8="",oldal9="",oldal10="",stilus="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_varazslo);

        btn=findViewById(R.id.Varazslo_btn);
        text=findViewById(R.id.Varazslo_text);
        db=new DBHelper1(this);

// cimstílus
        if(a==0) {
            cimstilus="Arany színű";
        }
        else if (a==1){
            cimstilus="Ezüst színű";
        }
        else if (a==2){
            cimstilus="Rozé arany";
        }
        else if (a==3){
            cimstilus="Kék színű";
        }
        else if (a==4){
            cimstilus="Zöld színű";
        }

        //oldalak

        if(b==0) {
            oldal1="Két képes oldal";
        }
        else if (b==1){
            oldal1="Három képes oldal";
        }
        else if (b==2){
            oldal1="Három képes oldal fordítva";
        }
        else if (b==3){
            oldal1="Negykepes oldal";
        }


        if(c==0) {
            oldal2="Két képes oldal";
        }
        else if (c==1){
            oldal2="Három képes oldal";
        }
        else if (c==2){
            oldal2="Három képes oldal fordítva";
        }
        else if (c==3){
            oldal2="Negykepes oldal";
        }


        if(d==0) {
            oldal3="Két képes oldal";
        }
        else if (d==1){
            oldal3="Három képes oldal";
        }
        else if (d==2){
            oldal3="Három képes oldal fordítva";
        }
        else if (d==3){
            oldal3="Negykepes oldal";
        }


        if(e==0) {
            oldal4="Két képes oldal";
        }
        else if (e==1){
            oldal4="Három képes oldal";
        }
        else if (e==2){
            oldal4="Három képes oldal fordítva";
        }
        else if (e==3){
            oldal4="Negykepes oldal";
        }


        if(f==0) {
            oldal5="Két képes oldal";
        }
        else if (f==1){
            oldal5="Három képes oldal";
        }
        else if (f==2){
            oldal5="Három képes oldal fordítva";
        }
        else if (f==3){
            oldal5="Negykepes oldal";
        }


        if(g==0) {
            oldal6="Két képes oldal";
        }
        else if (g==1){
            oldal6="Három képes oldal";
        }
        else if (g==2){
            oldal6="Három képes oldal fordítva";
        }
        else if (g==3){
            oldal6="Negykepes oldal";
        }


        if(h==0) {
            oldal7="Két képes oldal";
        }
        else if (h==1){
            oldal7="Három képes oldal";
        }
        else if (h==2){
            oldal7="Három képes oldal fordítva";
        }
        else if (h==3){
            oldal7="Negykepes oldal";
        }


        if(i==0) {
            oldal8="Két képes oldal";
        }
        else if (i==1){
            oldal8="Három képes oldal";
        }
        else if (i==2){
            oldal8="Három képes oldal fordítva";
        }
        else if (i==3){
            oldal8="Negykepes oldal";
        }

        if(j==0) {
            oldal9="Két képes oldal";
        }
        else if (j==1){
            oldal9="Három képes oldal";
        }
        else if (j==2){
            oldal9="Három képes oldal fordítva";
        }
        else if (j==3){
            oldal9="Negykepes oldal";
        }

        if(k==0) {
            oldal10="Két képes oldal";
        }
        else if (k==1){
            oldal10="Három képes oldal";
        }
        else if (k==2){
            oldal10="Három képes oldal fordítva";
        }
        else if (k==3){
            oldal10="Negykepes oldal";
        }

        //stilus
        if(l==0) {
            stilus="Fekete háttér";
        }
        else if (l==1){
            stilus="Kék háttér";
        }
        else if (l==2){
            stilus="Szürke háttér";
        }
        else if (l==3){
            stilus="Zöld háttér szürke keret";
        }




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NevTXT=text.getText().toString();
                Boolean checkinsertdata=db.adatbeadasa(NevTXT,cimstilus,oldal1,oldal2,oldal3,oldal4,oldal5,oldal6,oldal7,oldal8,oldal9,oldal10,stilus);
                if(checkinsertdata==true){
                    Toast.makeText(Varazslo.this, "Siker", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Varazslo.this, "Nem siker", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(Varazslo.this, Elso_oldal.class);
                i.putExtra("cim1",NevTXT);
                startActivity(i);





            }
        });
    }
}