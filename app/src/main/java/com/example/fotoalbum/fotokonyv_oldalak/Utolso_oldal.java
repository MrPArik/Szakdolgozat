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

public class Utolso_oldal extends AppCompatActivity {

    ImageView under;
    Button btn,szerkeszt;
    String a="",cim="",c="";
    LinearLayout l,l1,l2;
    ImageButton img;
    DBHelper1 db;
    TextView Oldalszam;
    String oldalszamertek="";
    int oldalszamertek_muvelet;
    int szerkesztszam=1;
    //
    Uri imageUri;


    String Kep="Utolso_oldal_elso_kep",KepNeveAdatb="";
    StorageReference storageReference;
    ProgressDialog progressDialog;
    //
    private final int GALLERY_REQ_CODE=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_utolso_oldal);

        //
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        //

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        db=new DBHelper1(this);
        btn=findViewById(R.id.btn_utso);
        l=findViewById(R.id.l1_1);
        l1=findViewById(R.id.l1_2);
        l2=findViewById(R.id.l1_3);
        img=findViewById(R.id.imageButton_utso);
        Oldalszam=findViewById(R.id.textView5);
        szerkeszt=findViewById(R.id.button8);
        under=findViewById(R.id.imageView7);
        under.setVisibility(View.INVISIBLE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("ss_nev");
            cim=value;
            int szam = Integer.parseInt(extras.getString("Oldalszam_tovabb"));
            oldalszamertek_muvelet=szam;
        }

        Oldalszam.setText(String.valueOf(oldalszamertek_muvelet));
        //
        KepNeveAdatb=cim+"_"+Kep;
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

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                uploadImage();
                l.setBackgroundResource(R.color.black);
                Intent intent=new Intent(getApplicationContext(), FOoldal.class);
                startActivity(intent);
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


                        Toast.makeText(Utolso_oldal.this,"Successfully Uploaded",Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                Toast.makeText(Utolso_oldal.this,"Failed to Upload",Toast.LENGTH_SHORT).show();


            }
        });

    }
}