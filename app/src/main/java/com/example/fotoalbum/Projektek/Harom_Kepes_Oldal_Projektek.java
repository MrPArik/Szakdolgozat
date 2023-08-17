package com.example.fotoalbum.Projektek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fotoalbum.R;
import com.example.fotoalbum.adatbazisfotokonyvhoz.DBHelper1;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Harom_Kepes_Oldal_Projektek extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    Bitmap bitmap;
    StorageReference storageReference;
    TextView tw;
    String cim="",c="";
    ImageView kep1,kep2,kep3;
    Button btn;
    DBHelper1 db;
    LinearLayout l,l1,l2;
    int oldalszamertek_muvelet;
    String a="",b="",oldalszamertek="";
    String Kep="Harom_kepes_oldal_elso_kep",Kep2="Harom_kepes_oldal_masodik_kep",Kep3="Harom_kepes_oldal_harmadik_kep",KepNeveAdatb="",KepNeveAdatb2="",KepNeveAdatb3="";
    String oldalszamkep="";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_harom_kepes_oldal_projektek);


        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //constraintLayout=findViewById(R.id.pc3);
        constraintLayout=findViewById(R.id.pc3);
        tw=findViewById(R.id.textView7_projektek);
        kep1=findViewById(R.id.imageButton3kepes_1_1_projektek);
        kep2=findViewById(R.id.imageButton3kepes_1_2_projektek);
        kep3=findViewById(R.id.imageButton3kepes_1_3_projektek);
        btn=findViewById(R.id.btn3kepes_2_projektek);
        db=new DBHelper1(this);
        l=findViewById(R.id.l3_1_projektek);
        l1=findViewById(R.id.l3_2_projektek);
        l2=findViewById(R.id.l3_3_projektek);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("ss_nev");
            cim=value;
            int szam = Integer.parseInt(extras.getString("Oldalszam_tovabb"));
            oldalszamertek_muvelet=szam;

        }

        if(oldalszamertek_muvelet==2) {

            oldalszamkep="Masodik";
        }
        else if(oldalszamertek_muvelet==3){

            oldalszamkep="Harmadik";
        }
        else if(oldalszamertek_muvelet==4){

            oldalszamkep="Negyedik";
        }
        else if(oldalszamertek_muvelet==5){

            oldalszamkep="Otodik";
        }
        else if(oldalszamertek_muvelet==6){

            oldalszamkep="Hatodik";
        }
        else if(oldalszamertek_muvelet==7){

            oldalszamkep="Hetedik";
        }
        else if(oldalszamertek_muvelet==8){

            oldalszamkep="Nyolcadik";
        }
        else if(oldalszamertek_muvelet==9){

            oldalszamkep="Kilencedik";
        }
        else if(oldalszamertek_muvelet==10){

            oldalszamkep="Tizedik";
        }


        KepNeveAdatb=cim+"_"+oldalszamkep+"_"+Kep;
        KepNeveAdatb2=cim+"_"+oldalszamkep+"_"+Kep2;
        KepNeveAdatb3=cim+"_"+oldalszamkep+"_"+Kep3;
        tw.setText(KepNeveAdatb);




        Cursor cursor = db.getStilus(cim);
        cursor.moveToFirst();
        a=cursor.getString(0);




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
        tw.setText(String.valueOf(oldalszamertek_muvelet));

        if(oldalszamertek_muvelet==2) {
            Cursor cursor2 = db.getMasodik_oldal(cim);
            cursor2.moveToFirst();
            c = cursor2.getString(0);

        }
        else if(oldalszamertek_muvelet==3){
            Cursor cursor2 = db.getHarmadik_oldal(cim);
            cursor2.moveToFirst();
            c = cursor2.getString(0);

        }
        else if(oldalszamertek_muvelet==4){
            Cursor cursor2 = db.getNegyedik_oldal(cim);
            cursor2.moveToFirst();
            c = cursor2.getString(0);

        }
        else if(oldalszamertek_muvelet==5){
            Cursor cursor2 = db.getOtodik_oldal(cim);
            cursor2.moveToFirst();
            c = cursor2.getString(0);

        }
        else if(oldalszamertek_muvelet==6){
            Cursor cursor2 = db.getHatodik_oldal(cim);
            cursor2.moveToFirst();
            c = cursor2.getString(0);

        }
        else if(oldalszamertek_muvelet==7){
            Cursor cursor2 = db.getHetedik_oldal(cim);
            cursor2.moveToFirst();
            c = cursor2.getString(0);

        }
        else if(oldalszamertek_muvelet==8){
            Cursor cursor2 = db.getNyolcadik_oldal(cim);
            cursor2.moveToFirst();
            c = cursor2.getString(0);

        }
        else if(oldalszamertek_muvelet==9){
            Cursor cursor2 = db.getKilencedik_oldal(cim);
            cursor2.moveToFirst();
            c = cursor2.getString(0);

        }
        else if(oldalszamertek_muvelet==10){
            Cursor cursor2 = db.getTizedik_oldal(cim);
            cursor2.moveToFirst();
            c = cursor2.getString(0);

        }
        //tw.setText(String.valueOf(oldalszamertek_muvelet));
        //tw.setText(String.valueOf(KepNeveAdatb));
        oldalszamertek_muvelet=oldalszamertek_muvelet+1;
        oldalszamertek=String.valueOf(oldalszamertek_muvelet);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                else{
                    Intent intent8 = new Intent(getApplicationContext(), Utolso_Oldal_Projektek.class);
                    intent8.putExtra("ss_nev", cim);
                    intent8.putExtra("Oldalszam_tovabb",oldalszamertek);
                    startActivity(intent8);
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
            Toast.makeText(this, "valami gebasz van nagymester"+e.toString(), Toast.LENGTH_SHORT).show();

            document.close();
            Toast.makeText(this,"siker",Toast.LENGTH_SHORT).show();


            //openPdf();
        }
    }

    //private void openPdf() {

    // }

   protected void onStart() {
        super.onStart();

        storageReference = FirebaseStorage.getInstance().getReference("images/" + KepNeveAdatb);
        try {
            File localfile = File.createTempFile("localfile", ".jpg");
            storageReference.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                    Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                    kep1.setImageBitmap(bitmap);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        storageReference = FirebaseStorage.getInstance().getReference("images/" + KepNeveAdatb2);
        try {
            File localfile = File.createTempFile("localfile", ".jpg");
            storageReference.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                    Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                    kep2.setImageBitmap(bitmap);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        storageReference = FirebaseStorage.getInstance().getReference("images/" + KepNeveAdatb3);
        try {
            File localfile = File.createTempFile("localfile", ".jpg");
            storageReference.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                    Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                    kep3.setImageBitmap(bitmap);

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