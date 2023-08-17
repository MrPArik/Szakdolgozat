package com.example.fotoalbum.bejelentkezes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Bejelentkezes.db";

    public DBHelper(Context context) {
        super(context, "login.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table felhasznalok(felhasznalonev TEXT primary key,jelszo TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists felhasznalok");
    }

    public Boolean insertData(String felhasznalonev, String jelszo) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("felhasznalonev", felhasznalonev);
        contentValues.put("jelszo", jelszo);
        long result = MyDB.insert("felhasznalok", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean checkfelhasznalonev(String felhasznalonev) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from felhasznalok where felhasznalonev=?", new String[]{felhasznalonev});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkfelhasznalonevjelszo(String felhasznalonev, String jelszo) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from felhasznalok where felhasznalonev=? and jelszo=?", new String[]{felhasznalonev, jelszo});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}

















