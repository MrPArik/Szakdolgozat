package com.example.fotoalbum.Projektek;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fotoalbum.R;
import com.example.fotoalbum.adatbazisfotokonyvhoz.DBHelper1;
import com.example.fotoalbum.adatbazisfotokonyvhoz.fotokonyv1;
import com.example.fotoalbum.bejelentkezes.Bejelentkezes;
import com.example.fotoalbum.fotokonyv_oldalak.Elso_oldal;
import com.example.fotoalbum.fotokonyv_oldalak.F_harom_kepes_oldal;
import com.example.fotoalbum.fotokonyv_oldalak.Ket_kepes_oldal;
import com.example.fotoalbum.fotokonyv_oldalak.Utolso_oldal;
import com.example.fotoalbum.fotokonyv_oldalak.harom_kepes_oldal;
import com.example.fotoalbum.fotokonyv_oldalak.negy_kepes_oldal;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import android.os.Bundle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Elso_Oldal_Projektek extends AppCompatActivity {




    ConstraintLayout constraintLayout;
    Bitmap bitmap;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    ImageView Projektek_Elso_kep;
    Button Projektek_Elso_button;
    TextView tw,oldalszam;
    LinearLayout l,l1,l2;
    String a="",b="",cim="",c="",Kep="Elso_oldal_elso_kep";
    DBHelper1 db;
    int oldalszamertek_muvelet;
    String oldalszamertek="";
    String kepneve="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_elso_oldal_projektek);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        constraintLayout=findViewById(R.id.pc1);

        Projektek_Elso_kep=findViewById(R.id.imageView_projektek_elso);
        Projektek_Elso_button=findViewById(R.id.pbtn_elso);

        tw=findViewById(R.id.ptw);
        oldalszam=findViewById(R.id.pOldalszam);
        db=new DBHelper1(this);
        l=findViewById(R.id.pl);
        l1=findViewById(R.id.pl1);
        l2=findViewById(R.id.pl2);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("ss_nev");
            cim=value;
        }

        tw.setText(cim);
        kepneve=cim+"_"+Kep;



        Cursor cursor = db.getStilus(cim);
        cursor.moveToFirst();
        a=cursor.getString(0);
        //tw.setText(a);


        Cursor cursor1 = db.getCimStilus(cim);
        cursor1.moveToFirst();
        b=cursor1.getString(0);
        //tw.setText(b);

        Cursor cursor2 = db.getElso_oldal(cim);
        cursor2.moveToFirst();
        c = cursor2.getString(0);




        if(a.equals("Fekete háttér")|| a.equals("Black background")) {
            l.setBackgroundResource(R.color.black);
        }
        else if (a.equals("Barna háttér") || a.equals("Brown background") ){
            l.setBackgroundResource(R.color.brown);
        }
        else if (a.equals("Szürke háttér")|| a.equals("Grey background")){
            l.setBackgroundResource(R.color.gray);
        }
        else if (a.equals("Fehér háttér")|| a.equals("White background")){
            l.setBackgroundResource(R.color.white);
        }
        else if (a.equals("Rózsaszín háttér")|| a.equals("Pink background")){
            l.setBackgroundResource(R.color.pink);
        }
        else if (a.equals("Világos kék háttér")|| a.equals("Light blue background")){
            l.setBackgroundResource(R.color.lblue);
        }
        else if (a.equals("Fehér háttér ezüst keret")|| a.equals("White backgroung with grey frame ")){
            l.setBackgroundResource(R.color.silver);
            l1.setBackgroundResource(R.color.white);
            l2.setBackgroundResource(R.color.white);
        }

        if(b.equals("Arany színű")|| b.equals("Golden")) {
            tw.setTextColor(Color.parseColor("#FFD700"));
        }
        else if (b.equals("Ezüst színű")|| b.equals("Silver")){
            tw.setTextColor(Color.parseColor("#C0C0C0"));
        }
        else if (b.equals("Rozé arany")|| b.equals("Rose gold")){
            tw.setTextColor(Color.parseColor("#b76e79"));
        }
        else if (b.equals("Kék színű")|| b.equals("Blue")){
            tw.setTextColor(Color.parseColor("#15156a"));
        }
        else if (b.equals("Fekete színű")|| b.equals("Black")){
            tw.setTextColor(Color.parseColor("#000000"));
        }
        else if (b.equals("Rózsaszín színű")|| b.equals("Pink")){
            tw.setTextColor(Color.parseColor("#FF99FF"));
        }
        else if (b.equals("Bézs színű")|| b.equals("Beige")){
            tw.setTextColor(Color.parseColor("#DCCCB6"));
        }


        oldalszamertek_muvelet = Integer.parseInt(oldalszam.getText().toString());
        oldalszamertek_muvelet=oldalszamertek_muvelet+1;
        oldalszamertek=String.valueOf(oldalszamertek_muvelet);


        Projektek_Elso_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cursor cursor2 = db.getElso_oldal(cim);
                //cursor2.moveToFirst();
                //c=cursor2.getString(0);
                //teszt.setText(c);
                Log.d("size",""+constraintLayout.getWidth()+""+constraintLayout.getWidth());
                bitmap=LoadBitmap(constraintLayout,constraintLayout.getWidth(),constraintLayout.getHeight());
                createPdf();

                if(c.equals("Két képes oldal")) {
                    Intent intent4 = new Intent(getApplicationContext(), Ket_Kepes_Oldal_Projektek.class);
                    intent4.putExtra("ss_nev", cim);
                    intent4.putExtra("Oldalszam_tovabb",oldalszamertek);
                    startActivity(intent4);
                }
                else if(c.equals("Három képes oldal")) {
                    Intent intent5 = new Intent(getApplicationContext(), Harom_Kepes_Oldal_Projektek.class);
                    intent5.putExtra("ss_nev", cim);
                    intent5.putExtra("Oldalszam_tovabb",oldalszamertek);
                    startActivity(intent5);

                }
                else if(c.equals("Három képes oldal fordítva")) {
                    Intent intent6 = new Intent(getApplicationContext(), F_Harom_Kepes_Oldal_Projekt.class);
                    intent6.putExtra("ss_nev", cim);
                    intent6.putExtra("Oldalszam_tovabb",oldalszamertek);
                    startActivity(intent6);

                }
                else if(c.equals("Negykepes oldal")) {
                    Intent intent7 = new Intent(getApplicationContext(), Negy_Kepes_Projektek.class);
                    intent7.putExtra("ss_nev", cim);
                    intent7.putExtra("Oldalszam_tovabb",oldalszamertek);
                    startActivity(intent7);

                }


            }
        });





    }

    private Bitmap LoadBitmap(View v, int width, int height) {
        Bitmap bitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    private void createPdf(){
        WindowManager windowManager=(WindowManager)getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics=new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float width=displayMetrics.widthPixels;
        float height=displayMetrics.heightPixels;
        int convertWidth=(int)width,convertHeight=(int)height;

        PdfDocument document=new PdfDocument();
        PdfDocument.PageInfo pageInfo=new PdfDocument.PageInfo.Builder(convertWidth,convertHeight,1).create();
        PdfDocument.Page page=document.startPage(pageInfo);
        Canvas canvas=page.getCanvas();
        Paint paint=new Paint();
        canvas.drawPaint(paint);
        bitmap=Bitmap.createScaledBitmap(bitmap,convertWidth,convertHeight,true);
        canvas.drawBitmap(bitmap,0,0,null);
        document.finishPage(page);

        oldalszamertek_muvelet=oldalszamertek_muvelet-1;
        String targetPdf="/sdcard/Download/"+cim+" "+oldalszamertek_muvelet+". oldal"+".pdf";
        //String targetPdf="/sdcard/Download/random.pdf";
        File file;
        file=new File(targetPdf);
        try{
            document.writeTo(new FileOutputStream(file));

        }
        catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, "valami nem sikerült"+e.toString(), Toast.LENGTH_SHORT).show();

            document.close();
            Toast.makeText(this,"siker",Toast.LENGTH_SHORT).show();


            //openPdf();
        }
    }

    //private void openPdf() {

   // }

    protected void onStart() {
        super.onStart();

        storageReference = FirebaseStorage.getInstance().getReference("images/" + kepneve);
        try {
            File localfile = File.createTempFile("localfile", ".jpg");
            storageReference.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                    Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                    Projektek_Elso_kep.setImageBitmap(bitmap);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}