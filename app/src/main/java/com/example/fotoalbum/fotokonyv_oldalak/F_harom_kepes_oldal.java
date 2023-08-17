package com.example.fotoalbum.fotokonyv_oldalak;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;
import com.example.fotoalbum.R;
import com.example.fotoalbum.adatbazisfotokonyvhoz.DBHelper1;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class F_harom_kepes_oldal extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    ImageView under;
    Button btn,szerkeszt;
    int oldalszamertek_muvelet;
    String a="",cim="",oldalszamertek="",c="";
    LinearLayout l,l1,l2;
    ImageButton img,img1,img2;
    DBHelper1 db;
    int szerkesztszam=1;
    TextView Oldalszam;
//
    Uri imageUri,imageUri1,imageUri2;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    String Kep="F_Harom_kepes_oldal_elso_kep",Kep2="F_Harom_kepes_oldal_masodik_kep",Kep3="F_Harom_kepes_oldal_harmadik_kep",KepNeveAdatb="",KepNeveAdatb2="",KepNeveAdatb3="";
    String oldalszamkep="";
    //
    private final int GALLERY_REQ_CODE=1000;
    private final int GALLERY_REQ_CODE1=100;
    private final int GALLERY_REQ_CODE2=10000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_fharom_kepes_oldal);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        //


        db=new DBHelper1(this);
        btn=findViewById(R.id.btnf3kepes_2);

        l=findViewById(R.id.lf3_1);
        l1=findViewById(R.id.lf3_2);
        l2=findViewById(R.id.lf3_3);
        img=findViewById(R.id.imageButtonf3kepes_1_1);
        img1=findViewById(R.id.imageButtonf3kepes_1_2);
        img2=findViewById(R.id.imageButtonf3kepes_1_3);
        Oldalszam=findViewById(R.id.textView8);
        szerkeszt=findViewById(R.id.FHArom_kepes_oldal_szerkeszt);
        under=findViewById(R.id.imageView3);
        under.setVisibility(View.INVISIBLE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("ss_nev");
            cim=value;
            int szam = Integer.parseInt(extras.getString("Oldalszam_tovabb"));
            oldalszamertek_muvelet=szam;
        }


        if(oldalszamertek_muvelet==2) {
            Cursor cursor2 = db.getMasodik_oldal(cim);
            cursor2.moveToFirst();
            c = cursor2.getString(0);
            oldalszamkep="Masodik";
        }
        else if(oldalszamertek_muvelet==3){
            Cursor cursor2 = db.getHarmadik_oldal(cim);
            cursor2.moveToFirst();
            c = cursor2.getString(0);
            oldalszamkep="Harmadik";
        }
        else if(oldalszamertek_muvelet==4){
            Cursor cursor2 = db.getNegyedik_oldal(cim);
            cursor2.moveToFirst();
            c = cursor2.getString(0);
            oldalszamkep="Negyedik";
        }else if(oldalszamertek_muvelet==5){
            Cursor cursor2 = db.getOtodik_oldal(cim);
            cursor2.moveToFirst();
            c = cursor2.getString(0);
            oldalszamkep="Otodik";
        }
        else if(oldalszamertek_muvelet==6){
            Cursor cursor2 = db.getHatodik_oldal(cim);
            cursor2.moveToFirst();
            c = cursor2.getString(0);
            oldalszamkep="Hatodik";
        }
        else if(oldalszamertek_muvelet==7){
            Cursor cursor2 = db.getHetedik_oldal(cim);
            cursor2.moveToFirst();
            c = cursor2.getString(0);
            oldalszamkep="Hetedik";
        }
        else if(oldalszamertek_muvelet==8){
            Cursor cursor2 = db.getNyolcadik_oldal(cim);
            cursor2.moveToFirst();
            c = cursor2.getString(0);
            oldalszamkep="Nyolcadik";
        }
        else if(oldalszamertek_muvelet==9){
            Cursor cursor2 = db.getKilencedik_oldal(cim);
            cursor2.moveToFirst();
            c = cursor2.getString(0);
            oldalszamkep="Kilencedik";
        }
        else if(oldalszamertek_muvelet==10){
            Cursor cursor2 = db.getTizedik_oldal(cim);
            cursor2.moveToFirst();
            c = cursor2.getString(0);
            oldalszamkep="Tizedik";
        }

        Oldalszam.setText(String.valueOf(oldalszamertek_muvelet));
        oldalszamertek_muvelet=oldalszamertek_muvelet+1;
        oldalszamertek=String.valueOf(oldalszamertek_muvelet);

        //
        KepNeveAdatb=cim+"_"+oldalszamkep+"_"+Kep;
        KepNeveAdatb2=cim+"_"+oldalszamkep+"_"+Kep2;
        KepNeveAdatb3=cim+"_"+oldalszamkep+"_"+Kep3;
        //


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

        szerkeszt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent2=new Intent();
                intent2.setType("image/*");
                intent2.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent2,101);*/

                if(szerkesztszam==1){
                    szerkesztszam=szerkesztszam+1;
                    under.setVisibility(View.VISIBLE);


                }
                else if(szerkesztszam==2){
                    szerkesztszam=szerkesztszam-1;
                    under.setVisibility(View.INVISIBLE);
                }


            }
        });



        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Cursor cursor2 = db.getElso_oldal(cim);
                //cursor2.moveToFirst();
                //c=cursor2.getString(0);
                //teszt.setText(c);
                if(c.equals("Két képes oldal")) {
                    uploadImage();
                    uploadImage1();
                    uploadImage2();
                    Intent intent4 = new Intent(getApplicationContext(), Ket_kepes_oldal.class);
                    intent4.putExtra("ss_nev", cim);
                    intent4.putExtra("Oldalszam_tovabb",oldalszamertek);
                    startActivity(intent4);
                }
                else if(c.equals("Három képes oldal")) {
                    uploadImage();
                    uploadImage1();
                    uploadImage2();
                    Intent intent5 = new Intent(getApplicationContext(), harom_kepes_oldal.class);
                    intent5.putExtra("ss_nev", cim);
                    intent5.putExtra("Oldalszam_tovabb",oldalszamertek);
                    startActivity(intent5);

                }
                else if(c.equals("Három képes oldal fordítva")) {
                    uploadImage();
                    uploadImage1();
                    uploadImage2();
                    Intent intent6 = new Intent(getApplicationContext(), F_harom_kepes_oldal.class);
                    intent6.putExtra("ss_nev", cim);
                    intent6.putExtra("Oldalszam_tovabb",oldalszamertek);
                    startActivity(intent6);

                }
                else if(c.equals("Negykepes oldal")) {
                    uploadImage();
                    uploadImage1();
                    uploadImage2();
                    Intent intent7 = new Intent(getApplicationContext(), negy_kepes_oldal.class);
                    intent7.putExtra("ss_nev", cim);
                    intent7.putExtra("Oldalszam_tovabb",oldalszamertek);
                    startActivity(intent7);

                }
                else{
                    uploadImage();
                    uploadImage1();
                    uploadImage2();
                    Intent intent8 = new Intent(getApplicationContext(), Utolso_oldal.class);
                    intent8.putExtra("ss_nev", cim);
                    intent8.putExtra("Oldalszam_tovabb",oldalszamertek);
                    startActivity(intent8);

                }

            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(szerkesztszam==1) {
                    selectImage();
                }
                else if(szerkesztszam==2) {
                    Intent dsPhotoEditorIntent = new Intent(getApplicationContext(), DsPhotoEditorActivity.class);
                    dsPhotoEditorIntent.setData(imageUri);

                    dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "Fotokonyv_kepek");

                    int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION, DsPhotoEditorActivity.TOOL_CROP};

                    dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);
                    startActivityForResult(dsPhotoEditorIntent, 200);
                }

            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(szerkesztszam==1) {
                    selectImage1();
                }
                else if(szerkesztszam==2) {
                    Intent dsPhotoEditorIntent = new Intent(getApplicationContext(), DsPhotoEditorActivity.class);
                    dsPhotoEditorIntent.setData(imageUri1);

                    dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "Fotokonyv_kepek");

                    int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION, DsPhotoEditorActivity.TOOL_CROP};

                    dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);
                    startActivityForResult(dsPhotoEditorIntent, 2000);
                }


            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(szerkesztszam==1) {
                    selectImage2();
                }
                else if(szerkesztszam==2) {
                    Intent dsPhotoEditorIntent = new Intent(getApplicationContext(), DsPhotoEditorActivity.class);
                    dsPhotoEditorIntent.setData(imageUri2);

                    dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "Fotokonyv_kepek");

                    int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION, DsPhotoEditorActivity.TOOL_CROP};

                    dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);
                    startActivityForResult(dsPhotoEditorIntent, 20000);
                }


            }
        });
    }

    public void showPopup (View v){
        PopupMenu popup=new PopupMenu(this ,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data != null && data.getData() != null){

            imageUri = data.getData();
            img.setImageURI(imageUri);
        }
        if (requestCode == 200 && data != null && data.getData() != null){
            Intent intent2=new Intent();
            intent2.setData(data.getData());
            img.setImageURI(intent2.getData());
            imageUri = data.getData();
        }
        if (requestCode == 1000 && data != null && data.getData() != null){

            imageUri1 = data.getData();
            img1.setImageURI(imageUri1);
        }
        if (requestCode == 2000 && data != null && data.getData() != null){
            Intent intent2=new Intent();
            intent2.setData(data.getData());
            img1.setImageURI(intent2.getData());
            imageUri1 = data.getData();
        }
        if (requestCode == 10000 && data != null && data.getData() != null){

            imageUri2 = data.getData();
            img2.setImageURI(imageUri2);
        }
        if (requestCode == 20000 && data != null && data.getData() != null){
            Intent intent2=new Intent();
            intent2.setData(data.getData());
            img2.setImageURI(intent2.getData());
            imageUri2 = data.getData();
        }

    }
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.item2:
                Toast.makeText(this, "Három képes oldallt választotta", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(), harom_kepes_oldal.class);
                intent.putExtra("ss_nev",cim);
                startActivity(intent);
                return true;
            case R.id.item1:
                Toast.makeText(this, "Két képes oldallt választotta", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(getApplicationContext(), Ket_kepes_oldal.class);
                intent1.putExtra("ss_nev",cim);
                startActivity(intent1);
                return true;
            case R.id.item3:
                Toast.makeText(this, "Három képes oldallt választotta (tükrözve)", Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent(getApplicationContext(), F_harom_kepes_oldal.class);
                intent2.putExtra("ss_nev",cim);
                startActivity(intent2);
                return true;
            case R.id.item4:
                Toast.makeText(this, "Három képes oldallt választotta", Toast.LENGTH_SHORT).show();
                Intent intent3=new Intent(getApplicationContext(), negy_kepes_oldal.class);
                intent3.putExtra("ss_nev",cim);
                startActivity(intent3);
                return true;
            default:
                return false;
        }
    }
    private void selectImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);

    }

    private void uploadImage() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading File....");
        progressDialog.show();


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now = new Date();
        String fileName = formatter.format(now);
        storageReference = FirebaseStorage.getInstance().getReference("images/"+KepNeveAdatb); //kep21 a kép neve


        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        Toast.makeText(F_harom_kepes_oldal.this,"Successfully Uploaded",Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                Toast.makeText(F_harom_kepes_oldal.this,"Failed to Upload",Toast.LENGTH_SHORT).show();


            }
        });

    }
    private void selectImage1() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1000);

    }

    private void uploadImage1() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading File....");
        progressDialog.show();


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now = new Date();
        String fileName = formatter.format(now);
        storageReference = FirebaseStorage.getInstance().getReference("images/"+KepNeveAdatb2); //kep21 a kép neve


        storageReference.putFile(imageUri1)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        Toast.makeText(F_harom_kepes_oldal.this,"Successfully Uploaded",Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                Toast.makeText(F_harom_kepes_oldal.this,"Failed to Upload",Toast.LENGTH_SHORT).show();


            }
        });

    }
    private void selectImage2() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,10000);

    }

    private void uploadImage2() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading File....");
        progressDialog.show();


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now = new Date();
        String fileName = formatter.format(now);
        storageReference = FirebaseStorage.getInstance().getReference("images/"+KepNeveAdatb3); //kep21 a kép neve


        storageReference.putFile(imageUri2)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        Toast.makeText(F_harom_kepes_oldal.this,"Successfully Uploaded",Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                Toast.makeText(F_harom_kepes_oldal.this,"Failed to Upload",Toast.LENGTH_SHORT).show();


            }
        });

    }
}
