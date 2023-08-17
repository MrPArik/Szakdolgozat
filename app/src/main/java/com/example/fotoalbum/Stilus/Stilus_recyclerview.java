package com.example.fotoalbum.Stilus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fotoalbum.Cim_stilus.Cim_Stilus_Adapter;
import com.example.fotoalbum.Cim_stilus.Interface;
import com.example.fotoalbum.Cim_stilus.modell;
import com.example.fotoalbum.fotokonyv_oldalak.Elso_oldal;
import com.example.fotoalbum.R;
import com.example.fotoalbum.adatbazisfotokonyvhoz.DBHelper1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;



public class Stilus_recyclerview extends AppCompatActivity implements Interface {
    TextView cim;
    DBHelper1 db;
    String szin;
    ArrayList<modell> cim_stilus_modells=new ArrayList<>();
    int[] Stilus_kepek={R.drawable.feketehatter,R.drawable.barna,R.drawable.silverhatter,R.drawable.ketkep,R.drawable.pinkhatter,R.drawable.lightbluehatter,R.drawable.fshatter};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stilus_recyclerview);

        RecyclerView recyclerView=findViewById(R.id.s_recycler);
        db=new DBHelper1(this);
        cim=findViewById(R.id.s_nev);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("s_nev");
            cim.setText(value);
        }
        Bundle extras1 = getIntent().getExtras();
        if (extras != null) {
            String value = extras1.getString("szin");
            szin=value;
        }
        recszovege4();

        Cim_Stilus_Adapter adapter=new Cim_Stilus_Adapter(this,cim_stilus_modells,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void recszovege4(){
        String[] stilus_szoveg=getResources().getStringArray(R.array.Stilus_Szoveg);
        for(int i=0;i<stilus_szoveg.length;i++){
            cim_stilus_modells.add(new modell(stilus_szoveg[i],Stilus_kepek[i]));
        }
    }

    @Override
    public void OnItemClicked(int position) {

        String StilusSzoveg=cim_stilus_modells.get(position).getSzoveg();
        String Cim=cim.getText().toString();
        int updatePapir=db.updatestilus(StilusSzoveg,Cim);
        //String NevTXT=cim.getText().toString();
        Intent intent1=new Intent(getApplicationContext(), Elso_oldal.class);
        intent1.putExtra("ss_nev",StilusSzoveg);
        intent1.putExtra("szin1",szin);
        intent1.putExtra("cim1",Cim);
        startActivity(intent1);



    }
}