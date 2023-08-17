package com.example.fotoalbum.adatbazisfotokonyvhoz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fotoalbum.R;

public class fotokonyv1 extends AppCompatActivity {

    EditText nev,cimstilus,text2,text3,text4,text5,id;
    Button bt1,bt2,bt3,bt4;
    DBHelper1 db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotokonyv1);


        nev=findViewById(R.id.text0);
        cimstilus=findViewById(R.id.text1);
        text2=findViewById(R.id.text7);
        text3=findViewById(R.id.text3);
        text4=findViewById(R.id.text4);
        text5=findViewById(R.id.text5);
        id=findViewById(R.id.text6);

        bt1=findViewById(R.id.button);
        bt2=findViewById(R.id.button2);
        bt3=findViewById(R.id.button3);
        bt4=findViewById(R.id.button4);

        db=new DBHelper1(this);



        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* String nevTXT=nev.getText().toString();
                String cimstilTXT=cimstilus.getText().toString();
                String TXT3=text2.getText().toString();
                String nevTXT4=text3.getText().toString();
                String nevTXT5=text4.getText().toString();
                String nevTXT6=text5.getText().toString();
                //Integer idTxt=Integer.parseInt(String.valueOf(id.getText()));
*/
                Boolean checkinsertdata=db.adatbeadasa("alma1234","Null","Null","Null","Null","Null","Null","Null","Null","Null","Null","Null","Null");
                if(checkinsertdata==true){
                    Toast.makeText(fotokonyv1.this, "Siker", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(fotokonyv1.this, "faszom", Toast.LENGTH_SHORT).show();
                }
            }
        });


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int checkinsertdata=db.torlesminden();

                Toast.makeText(getBaseContext(),"please Create your Account First", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), " Hello World", Toast.LENGTH_SHORT).show();
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=db.getdata();
                if(res.getCount()==0){
                    Toast.makeText(fotokonyv1.this, "Nincs elem", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Id :"+res.getString(0)+"\n");
                    buffer.append("Nev :"+res.getString(1)+"\n");
                    buffer.append("Címstílus :"+res.getString(2)+"\n");
                    buffer.append("Elso :"+res.getString(3)+"\n");
                    buffer.append("Masodik :"+res.getString(4)+"\n");
                    buffer.append("Harmadik :"+res.getString(5)+"\n");
                    buffer.append("Negyedik :"+res.getString(6)+"\n");
                    buffer.append("Otodik :"+res.getString(7)+"\n");
                    buffer.append("Hatodik :"+res.getString(8)+"\n");
                    buffer.append("Hetedik :"+res.getString(9)+"\n");
                    buffer.append("Nyolcadik :"+res.getString(10)+"\n");
                    buffer.append("Kilencedik :"+res.getString(11)+"\n");
                    buffer.append("Tizedik :"+res.getString(12)+"\n");
                    buffer.append("Stilus :"+res.getString(13)+"\n"+"\n"+"\n");
                }

                    AlertDialog.Builder builder=new AlertDialog.Builder(fotokonyv1.this);
                    builder.setCancelable(true);
                    builder.setTitle("projektek");
                    builder.setMessage(buffer.toString());
                    builder.show();

                }
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* String nevTXT=nev.getText().toString();
                String cimstilTXT=cimstilus.getText().toString();
                String TXT3=text2.getText().toString();
                String nevTXT4=text3.getText().toString();
                String nevTXT5=text4.getText().toString();
                String nevTXT6=text5.getText().toString();
                //Integer idTxt=Integer.parseInt(String.valueOf(id.getText()));
*/
                int checkinsertdata2=db.updatecimstilus("ujcim","alma1234");

            }
        });
    }

}