package com.example.fotoalbum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fotoalbum.Projektek.Elso_Oldal_Projektek;
import com.example.fotoalbum.adatbazisfotokonyvhoz.fotokonyv1;
import com.example.fotoalbum.bejelentkezes.Bejelentkezes;
import com.example.fotoalbum.fotokonyv_oldalak.Elso_oldal;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class FOoldal extends AppCompatActivity {
DrawerLayout drawerLayout;
NavigationView navigationView;
Toolbar toolbar;
ActionBarDrawerToggle actionBarDrawerToggle;
ImageView img,img1;
Button btn,btn1;
FirebaseAuth mAuth;
    String nyelv="";

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
if(actionBarDrawerToggle.onOptionsItemSelected(item)){
    return true;
}


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_fooldal);

        mAuth=FirebaseAuth.getInstance();
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);
        actionBarDrawerToggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.menu_open,R.string.menu_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        img=findViewById(R.id.imageButton5);
        img1=findViewById(R.id.imageButton6);

        btn=findViewById(R.id.button5);
        btn1=findViewById(R.id.button6);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_profil:
                        nyelv = btn.getText().toString();
                        finish();
                        startActivity(getIntent());
                        if(nyelv.equals("Make a photobook")){
                            setLocal(FOoldal.this,"hu");
                            finish();
                            startActivity(getIntent());
                        }else if(nyelv.equals("Fotókönyvkészítés")){
                            setLocal(FOoldal.this,"en");
                            finish();
                            startActivity(getIntent());
                        }
                        Log.i("MENU_DRAWER_TAG","Language item is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Toast.makeText(FOoldal.this, "Nyelv átállitva", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_projektek:
                        Log.i("MENU_DRAWER_TAG","Projektek item is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Toast.makeText(FOoldal.this, "Projektek item is clicked", Toast.LENGTH_SHORT).show();
                        Intent intent3=new Intent(getApplicationContext(), Projektek_lista.class);
                        startActivity(intent3);
                        break;
                    case R.id.nav_kijelentkezes:
                        Log.i("MENU_DRAWER_TAG","Kijelentkezes item is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        mAuth.signOut();
                        Intent intent1=new Intent(getApplicationContext(), fotokonyv1.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_fooldal:
                        Log.i("MENU_DRAWER_TAG","Fooldal item is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent2=new Intent(getApplicationContext(), Varazslo.class);
                        startActivity(intent2);
                        break;
                }


                return false;
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Projekt_Nev_Megadas.class);
                startActivity(intent);
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FOoldal.this, "Még nincs funkció", Toast.LENGTH_SHORT).show();
                Intent intent4=new Intent(getApplicationContext(), Varazslo.class);
                startActivity(intent4);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Projekt_Nev_Megadas.class);
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FOoldal.this, "Még nincs funkció", Toast.LENGTH_SHORT).show();
                Intent intent4=new Intent(getApplicationContext(), Varazslo.class);
                startActivity(intent4);
            }
        });





    }
    public void setLocal(Activity activity, String langcode){
        Locale locale=new Locale(langcode);
        locale.setDefault(locale);
        Resources resources=activity.getResources();
        Configuration config=resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config,resources.getDisplayMetrics());
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(FOoldal.this,Bejelentkezes_ketto.class));
        }
    }
}
