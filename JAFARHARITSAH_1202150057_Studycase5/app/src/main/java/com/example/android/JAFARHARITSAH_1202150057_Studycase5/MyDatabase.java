package com.example.android.JAFARHARITSAH_1202150057_Studycase5;

import android.provider.BaseColumns;

    //class yang digunakan untuk mendefinisikan skema database
public class MyDatabase {

    public static final class DatabaseScheme implements BaseColumns {
        public static final String TABLE_NAME = "table_name";
        public static final String DATABASE_ID = "ID";
        public static final String NAMA_TODO ="todoname";
        public static final String DESKRIPSI = "description";
        public static final String PRIORITAS = "priority";
    }


}
