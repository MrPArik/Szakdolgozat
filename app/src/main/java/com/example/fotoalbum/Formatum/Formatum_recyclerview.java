package com.example.fotoalbum.Formatum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fotoalbum.Cim_stilus.Cim_Stilus_Adapter;
import com.example.fotoalbum.Cim_stilus.modell;
import com.example.fotoalbum.Cim_stilus.Interface;
import com.example.fotoalbum.R;
import com.example.fotoalbum.Stilus.Stilus_recyclerview;
import com.example.fotoalbum.adatbazisfotokonyvhoz.DBHelper1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

public class Formatum_recyclerview extends AppCompatActivity implements Interface {
    TextView cim,oldalszam,text;
    DBHelper1 db;
    String szin,OldalTXT;
    ArrayList<modell> cim_stilus_modells=new ArrayList<>();
    int[] Formatum_kepek={R.drawable.ketkep,R.drawable.haromkep,R.drawable.haromkepf,R.drawable.negykep};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_formatum_recyclerview);

        RecyclerView recyclerView=findViewById(R.id.f_recycler);
        db=new DBHelper1(this);
        cim=findViewById(R.id.f_nev);
        oldalszam=findViewById(R.id.textView3);
        text=findViewById(R.id.textView4);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("f_nev");
            cim.setText(value);
        }
        Bundle extras1 = getIntent().getExtras();
        if (extras1 != null) {
            String value = extras.getString("text_szin");
            szin=value;
        }

        Bundle extras2 = getIntent().getExtras();
        if (extras2 != null) {
            String value = extras.getString("oldal");
            if(value != null && !value.isEmpty())
                oldalszam.setText(value);

        }
        Bundle extras3 = getIntent().getExtras();
        if (extras1 != null) {
            String value1 = extras.getString("o_szam");
            OldalTXT=value1;
        }

        recszovege1();

        Cim_Stilus_Adapter adapter=new Cim_Stilus_Adapter(this,cim_stilus_modells,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void recszovege1(){
        String[] formatum_szoveg=getResources().getStringArray(R.array.Formatum_Szoveg);
        for(int i=0;i<formatum_szoveg.length;i++){
            cim_stilus_modells.add(new modell(formatum_szoveg[i],Formatum_kepek[i]));
        }
    }

    @Override
    public void OnItemClicked(int position) {
        String szam= oldalszam.getText().toString();
        int oldalszamertek=Integer.parseInt(szam);
        int oldalszamertek1=Integer.parseInt(OldalTXT);
        if (oldalszamertek<oldalszamertek1) {

            String OldalSzoveg = cim_stilus_modells.get(position).getSzoveg();
            if(oldalszamertek==1){
                String Cim = cim.getText().toString();
                int updateOldal = db.update_elso(OldalSzoveg, Cim);
            }
            else if(oldalszamertek==2){
                String Cim = cim.getText().toString();
                int updateOldal = db.updatemasodik(OldalSzoveg, Cim);
            }
            else if(oldalszamertek==3){
                String Cim = cim.getText().toString();
                int updateOldal = db.updateharmadik(OldalSzoveg, Cim);
            }
            else if(oldalszamertek==4){
                String Cim = cim.getText().toString();
                int updateOldal = db.updatenegyedik(OldalSzoveg, Cim);
            }
            else if(oldalszamertek==5){
                String Cim = cim.getText().toString();
                int updateOldal = db.updateototdik(OldalSzoveg, Cim);
            }
            else if(oldalszamertek==6){
                String Cim = cim.getText().toString();
                int updateOldal = db.updatehatodik(OldalSzoveg, Cim);
            }
            else if(oldalszamertek==7){
                String Cim = cim.getText().toString();
                int updateOldal = db.updatehetedik(OldalSzoveg, Cim);
            }
            else if(oldalszamertek==8){
                String Cim = cim.getText().toString();
                int updateOldal = db.updatenyolcadik(OldalSzoveg, Cim);
            }
            else if(oldalszamertek==9){
                String Cim = cim.getText().toString();
                int updateOldal = db.updatekilencedik(OldalSzoveg, Cim);
            }

            oldalszamertek=oldalszamertek+1;
            String strI = String.valueOf(oldalszamertek);

            String NevTXT = cim.getText().toString();
            Intent i = new Intent(Formatum_recyclerview.this, Formatum_recyclerview.class);
            i.putExtra("oldal", strI);
            i.putExtra("f_nev", NevTXT);
            i.putExtra("text_szin", szin);
            i.putExtra("o_szam", OldalTXT);
            startActivity(i);
        }
        else{
            String FomratumSzoveg = cim_stilus_modells.get(position).getSzoveg();
            String Cim = cim.getText().toString();
            int updateOldal = db.updatetizedik(FomratumSzoveg, Cim);



            String NevTXT = cim.getText().toString();
            Intent i = new Intent(Formatum_recyclerview.this, Stilus_recyclerview.class);
            i.putExtra("s_nev", NevTXT);
            i.putExtra("szin", szin);

            startActivity(i);

        }


    }
}