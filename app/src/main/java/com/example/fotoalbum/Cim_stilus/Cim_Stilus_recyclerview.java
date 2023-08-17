package com.example.fotoalbum.Cim_stilus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.fotoalbum.Formatum.Formatum_recyclerview;
import com.example.fotoalbum.R;
import com.example.fotoalbum.adatbazisfotokonyvhoz.DBHelper1;

import java.util.ArrayList;

public class Cim_Stilus_recyclerview extends AppCompatActivity implements Interface {
    TextView cim,text;
    DBHelper1 db;
    String OldalTXT;
    ArrayList<modell> cim_stilus_modells=new ArrayList<>();
    int[] Cim_Stilus_kepek={R.drawable.arany,R.drawable.silver,R.drawable.rose,R.drawable.kek,R.drawable.fekete,R.drawable.pink,R.drawable.bezs};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_cim_stilus_recyclerview);
        RecyclerView recyclerView=findViewById(R.id.cs_recycler);
        db=new DBHelper1(this);
        cim=findViewById(R.id.cs_nev);
        text=findViewById(R.id.textView2);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           String value = extras.getString("cs_nev");
            cim.setText(value);

        }
        Bundle extras1 = getIntent().getExtras();
        if (extras1 != null) {
            String value1 = extras.getString("o_nev");
            OldalTXT=value1;

        }
        recszovege();

        Cim_Stilus_Adapter adapter=new Cim_Stilus_Adapter(this,cim_stilus_modells,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void recszovege(){
        String[] cim_stilus_szoveg=getResources().getStringArray(R.array.Cim_Stilus_Szoveg);
        for(int i=0;i<cim_stilus_szoveg.length;i++){
            cim_stilus_modells.add(new modell(cim_stilus_szoveg[i],Cim_Stilus_kepek[i]));
        }
    }

    @Override
    public void OnItemClicked(int position) {

        String CimStilusSzoveg=cim_stilus_modells.get(position).getSzoveg();
        String Cim=cim.getText().toString();
        int updateCimStilus=db.updatecimstilus(CimStilusSzoveg,Cim);
        Intent i = new Intent(Cim_Stilus_recyclerview.this, Formatum_recyclerview.class);
        i.putExtra("f_nev",Cim);
        i.putExtra("text_szin",CimStilusSzoveg);
        i.putExtra("o_szam",OldalTXT);
        startActivity(i);



    }
}