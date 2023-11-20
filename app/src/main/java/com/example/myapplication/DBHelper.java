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
                "image_url TEXT," +
                "FOREIGN KEY (category_id) REFERENCES category(_id)" +
                ");");

        // 카테고리
        db.execSQL("INSERT INTO category (name) VALUES ('한식');");
        db.execSQL("INSERT INTO category (name) VALUES ('중식');");
        db.execSQL("INSERT INTO category (name) VALUES ('양식');");
        db.execSQL("INSERT INTO category (name) VALUES ('일식');");
        db.execSQL("INSERT INTO category (name) VALUES ('카페');");
        db.execSQL("INSERT INTO category (name) VALUES ('술집');");
        db.execSQL("INSERT INTO category (name) VALUES ('분식');");
        db.execSQL("INSERT INTO category (name) VALUES ('아시아');");
        db.execSQL("INSERT INTO category (name) VALUES ('패스트푸드');");
        db.execSQL("INSERT INTO category (name) VALUES ('레스토랑');");

        // 가게
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url) VALUES ('우마이도', '010-4937-1765','롯데리아 앞', '5.0', 4, 'https://my-bucket-for-spring.s3.ap-northeast-2.amazonaws.com/46eaa2aa-5592-463a-883d-63511d4086e1%E3%85%8A%E3%85%81.jpeg');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
}
