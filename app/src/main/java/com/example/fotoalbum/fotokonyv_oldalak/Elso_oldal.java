package com.example.fotoalbum.fotokonyv_oldalak;

import androidx.activity.result.contract.ActivityResultContracts;
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
import android.graphics.Color;
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
import com.example.fotoalbum.FOoldal;
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

public class Elso_oldal extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ImageView under;
    TextView tw,oldalszam;
    Button btn,btn1,szerkeszt;
    int oldalszamertek_muvelet;
    String oldalszamertek="";
    String a="",b="",cim="",c="",d="harom_kepes_oldal";
    String Kep="Elso_oldal_elso_kep",KepNeveAdatb="";
    LinearLayout l,l1,l2;
    ImageButton img;
    int szerkesztszam=1;
    DBHelper1 db;
    Uri imageUri;
    StorageReference storageReference;
    ProgressDialog progressDialog;

    private final int GALLERY_REQ_CODE=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_elso_oldal);


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        //kep

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        btn1=findViewById(R.id.button7);
        btn=findViewById(R.id.btn_elso);
        szerkeszt=findViewById(R.id.Elso_Oldal_Szerkeszt);
        oldalszam=findViewById(R.id.Oldalszam);
        l=findViewById(R.id.l);
        l1=findViewById(R.id.l1);
        l2=findViewById(R.id.l2);
        img=findViewById(R.id.imageButton_elso);
        db=new DBHelper1(this);
        tw=findViewById(R.id.tw);

        under=findViewById(R.id.imageView2);
        under.setVisibility(View.INVISIBLE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("cim1");
           cim=value;
        }
        tw.setText(cim);

        //oldalszamertek_muvelet = oldalszam.getText().toString();

        oldalszamertek_muvelet = Integer.parseInt(oldalszam.getText().toString());
        oldalszamertek_muvelet=oldalszamertek_muvelet+1;
        oldalszamertek=String.valueOf(oldalszamertek_muvelet);



        KepNeveAdatb=cim+"_"+Kep;

        Cursor cursor = db.getStilus(cim);
        cursor.moveToFirst();
        a=cursor.getString(0);     //ittt hiba van az adatb-ben valahol



        Cursor cursor1 = db.getCimStilus(cim);
        cursor1.moveToFirst();
        b=cursor1.getString(0);


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



       /* btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                l.setBackgroundResource(R.color.black);
                Intent intent=new Intent(getApplicationContext(), harom_kepes_oldal.class);
                startActivity(intent);
            }

        });*/

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cursor cursor2 = db.getElso_oldal(cim);
                //cursor2.moveToFirst();
                //c=cursor2.getString(0);
                //teszt.setText(c);
                if(c.equals("Két képes oldal")) {
                    uploadImage();
                    Intent intent4 = new Intent(getApplicationContext(), Ket_kepes_oldal.class);
                    intent4.putExtra("ss_nev", cim);
                    intent4.putExtra("Oldalszam_tovabb",oldalszamertek);
                    startActivity(intent4);

                }
                 else if(c.equals("Három képes oldal")) {
                    uploadImage();
                    Intent intent5 = new Intent(getApplicationContext(), harom_kepes_oldal.class);
                    intent5.putExtra("ss_nev", cim);
                    intent5.putExtra("Oldalszam_tovabb",oldalszamertek);
                    startActivity(intent5);

                }
                else if(c.equals("Három képes oldal fordítva")) {
                    uploadImage();
                    Intent intent6 = new Intent(getApplicationContext(), F_harom_kepes_oldal.class);
                    intent6.putExtra("ss_nev", cim);
                    intent6.putExtra("Oldalszam_tovabb",oldalszamertek);
                    startActivity(intent6);

                }
                else if(c.equals("Negykepes oldal")) {
                    uploadImage();
                    Intent intent7 = new Intent(getApplicationContext(), negy_kepes_oldal.class);
                    intent7.putExtra("ss_nev", cim);
                    intent7.putExtra("Oldalszam_tovabb",oldalszamertek);
                    startActivity(intent7);

                }

            }
        });

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

    }
    private void selectImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);

    }

    private void uploadImage() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("File feltöltése...");
        progressDialog.show();


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now = new Date();
        String fileName = formatter.format(now);
        storageReference = FirebaseStorage.getInstance().getReference("images/"+KepNeveAdatb); //kep21 a kép neve


        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        Toast.makeText(Elso_oldal.this,"Sikeres feltöltés",Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                Toast.makeText(Elso_oldal.this,"Failed to Upload",Toast.LENGTH_SHORT).show();


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
    }




    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.item2:
                uploadImage();
                Toast.makeText(this, "Három képes oldallt választotta", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(), harom_kepes_oldal.class);
                intent.putExtra("ss_nev",cim);
                startActivity(intent);
                return true;
            case R.id.item1:
                //selectImage();
                uploadImage();
                Toast.makeText(this, "Két képes oldallt választotta", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(getApplicationContext(), Ket_kepes_oldal.class);
                intent1.putExtra("ss_nev",cim);
                intent1.putExtra("Oldalszam_tovabb",oldalszamertek);
                startActivity(intent1);
                return true;
            case R.id.item3:
                uploadImage();
                Toast.makeText(this, "Három képes oldallt választotta (tükrözve)", Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent(getApplicationContext(), F_harom_kepes_oldal.class);
                intent2.putExtra("ss_nev",cim);
                startActivity(intent2);
                return true;
            case R.id.item4:
                uploadImage();
                Toast.makeText(this, "Három képes oldallt választotta", Toast.LENGTH_SHORT).show();
                Intent intent3=new Intent(getApplicationContext(), negy_kepes_oldal.class);
                intent3.putExtra("ss_nev",cim);
                startActivity(intent3);
                return true;
            default:
                return false;
        }
    }



}

