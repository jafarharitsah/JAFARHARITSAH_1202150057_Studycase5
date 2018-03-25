package com.example.android.JAFARHARITSAH_1202150057_Studycase5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper extends SQLiteOpenHelper {

    //inisialisasi nama database dan versinyaa
    private static final String DATABASE_NAME = "studycase5.db";
    private static final int DATABASE_VERSION = 2;
    SQLiteDatabase db;

    public Helper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        //deklarasi databse yang dapat ditulis/writeable
        db =  getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //pembuatan tabel-tabel dalam database berdasarkan skema yang ditentukan
        //di kelas MyDatabase
        db.execSQL("create table "+ MyDatabase.DatabaseScheme.TABLE_NAME + " ( " +
                MyDatabase.DatabaseScheme.DATABASE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MyDatabase.DatabaseScheme.NAMA_TODO + " TEXT, " +
                MyDatabase.DatabaseScheme.DESKRIPSI + " TEXT, "+
                MyDatabase.DatabaseScheme.PRIORITAS + " TEXT);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //meng-drop tabel dengan nama sama jika sudah ada dan memulai membuat baru
        db.execSQL("DROP TABLE IF EXISTS " + MyDatabase.DatabaseScheme.TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String todo, String desc, String prio){

        //memasukkan data kedalam tabel yang ada berdasarkan inputan
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDatabase.DatabaseScheme.NAMA_TODO,todo);
        contentValues.put(MyDatabase.DatabaseScheme.DESKRIPSI,desc);
        contentValues.put(MyDatabase.DatabaseScheme.PRIORITAS,prio);
        //if data is not inserted results will be -1
        long result = db.insert(MyDatabase.DatabaseScheme.TABLE_NAME,null,contentValues);

        return result != -1;
    }

    public Cursor getAllData(){
        db = getWritableDatabase();
        return db.rawQuery("select * from "+ MyDatabase.DatabaseScheme.TABLE_NAME,null);
    }

    public boolean deleteDataSwipping(String id){
        return db.delete(MyDatabase.DatabaseScheme.TABLE_NAME, MyDatabase.DatabaseScheme.DATABASE_ID + "=" + id, null) > 0;
    }
}
