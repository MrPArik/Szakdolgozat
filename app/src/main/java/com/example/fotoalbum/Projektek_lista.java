package com.example.fotoalbum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.fotoalbum.Projektek.Elso_Oldal_Projektek;
import com.example.fotoalbum.adatbazisfotokonyvhoz.DBHelper1;
import com.example.fotoalbum.adatbazisfotokonyvhoz.fotokonyv1;
import com.example.fotoalbum.fotokonyv_oldalak.harom_kepes_oldal;

import java.util.ArrayList;

public class Projektek_lista extends AppCompatActivity {

    ListView lista;
    DBHelper1 db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_projektek_lista);

        lista=findViewById(R.id.Projektek_listview);
        db=new DBHelper1(this);

        ArrayList<String> theList=new ArrayList<>();
        Cursor data =db.getListContent();

        if(data.getCount()==0){
            Toast.makeText(Projektek_lista.this, "Üres", Toast.LENGTH_SHORT).show();
        }
        else{
            while(data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,theList);
                lista.setAdapter(listAdapter);
            }
        }

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str=lista.getAdapter().getItem(i).toString();
                Intent intent=new Intent(getApplicationContext(), Elso_Oldal_Projektek.class);
                intent.putExtra("ss_nev",str);
                startActivity(intent);
            }
        });

    }
}