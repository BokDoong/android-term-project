package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mycontacts.db";
    private static final int DATABASE_VERSION = 3;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // 테이블 생성
        db.execSQL("CREATE TABLE category ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT" +
                ");");

        db.execSQL("CREATE TABLE store ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "tel TEXT, " +
                "address TEXT, " +
                "rating TEXT, " +
                "category_id INTEGER, " +
                "FOREIGN KEY (category_id) REFERENCES category(_id)" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
}
