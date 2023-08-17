package com.example.fotoalbum.adatbazisfotokonyvhoz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper1  extends SQLiteOpenHelper {
    public DBHelper1( Context context) {
        super(context, "Projektek.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
            DB.execSQL("create Table Fotokonyvek(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,Nev TEXT ,Cimstilus TEXT,Elso_oldal TEXT,Masodik_oldal TEXT,Harmadik_oldal TEXT,Negyedik_oldal TEXT,Otodik_oldal TEXT,Hatodik_oldal TEXT,Hetedik_oldal TEXT,Nyolcadik_oldal TEXT,Kilencedik_oldal TEXT,Tizedik_oldal TEXT,Stilus TEXT)");
    }
    //Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL
    //id,név,cím stílusa,fomrátum,papír,borító,stílus
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Fotokonyvek");
        //onCreate(DB);
    }


   public boolean adatbeadasa( String nev,String cimstilus,String elso,String masodik,String harmadik,String negyedik,String otodik,String hatodik,String hetedik,String nyolcadik,String kilencedik,String tizedik,String stilus)
   {
       //Integer id,
       SQLiteDatabase DB=this.getWritableDatabase();
       ContentValues contentValues=new ContentValues();
       //contentValues.put("Id",id);
       contentValues.put("Nev",nev);
       contentValues.put("Cimstilus",cimstilus);
       contentValues.put("Elso_oldal",elso);
       contentValues.put("Masodik_oldal",masodik);
       contentValues.put("Harmadik_oldal",harmadik);
       contentValues.put("Negyedik_oldal",negyedik);
       contentValues.put("Otodik_oldal",otodik);
       contentValues.put("Hatodik_oldal",hatodik);
       contentValues.put("Hetedik_oldal",hetedik);
       contentValues.put("Nyolcadik_oldal",nyolcadik);
       contentValues.put("Kilencedik_oldal",kilencedik);
       contentValues.put("Tizedik_oldal",tizedik);
       contentValues.put("Stilus",stilus);
        long result=DB.insert("Fotokonyvek",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
   }

    public int updatecimstilus(String cimstilus,String nev)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        DB.execSQL("UPDATE Fotokonyvek  SET Cimstilus = "+"'"+cimstilus+"'"+" where Nev= "+"'"+nev+"'");

        return 0;
    }
    public int update_elso(String elso,String nev)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        DB.execSQL("UPDATE Fotokonyvek  SET Elso_oldal = "+"'"+elso+"'"+" where Nev= "+"'"+nev+"'");

        return 0;
    }
    public int updatemasodik(String masodik,String nev)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        DB.execSQL("UPDATE Fotokonyvek  SET Masodik_oldal = "+"'"+masodik+"'"+" where Nev= "+"'"+nev+"'");

        return 0;
    }
    public int updateharmadik(String harmadik,String nev)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        DB.execSQL("UPDATE Fotokonyvek  SET Harmadik_oldal = "+"'"+harmadik+"'"+" where Nev= "+"'"+nev+"'");

        return 0;
    }
    public int updatenegyedik(String negyedik,String nev)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        DB.execSQL("UPDATE Fotokonyvek  SET Negyedik_oldal = "+"'"+negyedik+"'"+" where Nev= "+"'"+nev+"'");

        return 0;
    }
    public int updateototdik(String otodik,String nev)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        DB.execSQL("UPDATE Fotokonyvek  SET Otodik_oldal = "+"'"+otodik+"'"+" where Nev= "+"'"+nev+"'");

        return 0;
    }
    public int updatehatodik(String hatodik,String nev)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        DB.execSQL("UPDATE Fotokonyvek  SET Hatodik_oldal = "+"'"+hatodik+"'"+" where Nev= "+"'"+nev+"'");

        return 0;
    }public int updatehetedik(String hetedik,String nev)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        DB.execSQL("UPDATE Fotokonyvek  SET Hetedik_oldal = "+"'"+hetedik+"'"+" where Nev= "+"'"+nev+"'");

        return 0;
    }
    public int updatenyolcadik(String nyolcadik,String nev)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        DB.execSQL("UPDATE Fotokonyvek  SET Nyolcadik_oldal = "+"'"+nyolcadik+"'"+" where Nev= "+"'"+nev+"'");

        return 0;
    }
    public int updatekilencedik(String kilencedik,String nev)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        DB.execSQL("UPDATE Fotokonyvek  SET Kilencedik_oldal = "+"'"+kilencedik+"'"+" where Nev= "+"'"+nev+"'");

        return 0;
    }
    public int updatetizedik(String tizedik,String nev)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        DB.execSQL("UPDATE Fotokonyvek  SET Tizedik_oldal = "+"'"+tizedik+"'"+" where Nev= "+"'"+nev+"'");

        return 0;
    }

    public int updatestilus(String stilus,String nev)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        DB.execSQL("UPDATE Fotokonyvek  SET Stilus = "+"'"+stilus+"'"+" where Nev= "+"'"+nev+"'");

        return 0;
    }

    public int torlesminden()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.execSQL("DELETE FROM Fotokonyvek");
        //DB.execSQL("DROP TABLE IF EXISTS "+"Fotokonyvek");
        //DB.execSQL("create Table Fotokonyvek(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,Nev TEXT ,Cimstilus TEXT,Elso_oldal TEXT,Masodik_oldal TEXT,Harmadik_oldal TEXT,Negyedik_oldal TEXT,Otodik_oldal TEXT,Hatodik_oldal TEXT,Hetedik_oldal TEXT,Nyolcadik_oldal TEXT,Kilencedik_oldal TEXT,Tizedik_oldal TEXT,Stilus TEXT)");


        return 0;
    }

    public Cursor getdata(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select * From Fotokonyvek",null);
        return cursor;
    }
    /*public Cursor getCimStilus(String nev){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("SELECT Stilus FROM Fotokonyvek WHERE Nev="+"'"+nev+"'",null);
        return cursor;
    }*/
    public Cursor getStilus(String nev){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //String qry = "SELECT * FROM "+TABLE_NAME+" WHERE ID="+id;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Stilus FROM Fotokonyvek WHERE Nev="+"'"+nev+"'",null);
        return cursor;
    }
    public Cursor getCimStilus(String nev){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //String qry = "SELECT * FROM "+TABLE_NAME+" WHERE ID="+id;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Cimstilus FROM Fotokonyvek WHERE Nev="+"'"+nev+"'",null);
        return cursor;
    }
    public Cursor getElso_oldal(String nev){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //String qry = "SELECT * FROM "+TABLE_NAME+" WHERE ID="+id;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Elso_oldal FROM Fotokonyvek WHERE Nev="+"'"+nev+"'",null);
        return cursor;
    }
    public Cursor getMasodik_oldal(String nev){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //String qry = "SELECT * FROM "+TABLE_NAME+" WHERE ID="+id;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Masodik_oldal FROM Fotokonyvek WHERE Nev="+"'"+nev+"'",null);
        return cursor;
    }
    public Cursor getHarmadik_oldal(String nev){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //String qry = "SELECT * FROM "+TABLE_NAME+" WHERE ID="+id;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Harmadik_oldal FROM Fotokonyvek WHERE Nev="+"'"+nev+"'",null);
        return cursor;
    }
    public Cursor getNegyedik_oldal(String nev){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //String qry = "SELECT * FROM "+TABLE_NAME+" WHERE ID="+id;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Negyedik_oldal FROM Fotokonyvek WHERE Nev="+"'"+nev+"'",null);
        return cursor;
    }
    public Cursor getOtodik_oldal(String nev){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //String qry = "SELECT * FROM "+TABLE_NAME+" WHERE ID="+id;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Otodik_oldal FROM Fotokonyvek WHERE Nev="+"'"+nev+"'",null);
        return cursor;
    }
    public Cursor getHatodik_oldal(String nev){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //String qry = "SELECT * FROM "+TABLE_NAME+" WHERE ID="+id;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Hatodik_oldal FROM Fotokonyvek WHERE Nev="+"'"+nev+"'",null);
        return cursor;
    }
    public Cursor getHetedik_oldal(String nev){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //String qry = "SELECT * FROM "+TABLE_NAME+" WHERE ID="+id;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Hetedik_oldal FROM Fotokonyvek WHERE Nev="+"'"+nev+"'",null);
        return cursor;
    }
    public Cursor getNyolcadik_oldal(String nev){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //String qry = "SELECT * FROM "+TABLE_NAME+" WHERE ID="+id;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Nyolcadik_oldal FROM Fotokonyvek WHERE Nev="+"'"+nev+"'",null);
        return cursor;
    }
    public Cursor getKilencedik_oldal(String nev){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //String qry = "SELECT * FROM "+TABLE_NAME+" WHERE ID="+id;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Kilencedik_oldal FROM Fotokonyvek WHERE Nev="+"'"+nev+"'",null);
        return cursor;
    }
    public Cursor getTizedik_oldal(String nev){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //String qry = "SELECT * FROM "+TABLE_NAME+" WHERE ID="+id;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Tizedik_oldal FROM Fotokonyvek WHERE Nev="+"'"+nev+"'",null);
        return cursor;
    }

    public Cursor getListContent(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Fotokonyvek ",null);
        return cursor;
    }

}
