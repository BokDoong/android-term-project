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
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id) VALUES ('우마이도', '010-4937-1765','롯데리아 앞', '5.0', 4);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id) VALUES ('라멘집입니다', '010-1541-6621','울산 남구 문수로75번길 21', '4.54', 4);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id) VALUES ('시미루 라멘', '010-6123-7453','울산 남구 대학로43번길 16 1층', '3.1', 4);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id) VALUES ('삼미', '010-1231-5234','울과대 앞', '4.0', 1);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id) VALUES ('정일품', '010-1234-3922','울과대 앞2', '4.5', 1);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id) VALUES ('본가어탕', '010-1561-1231','울산 남구 대학로1번길 35 1층 본가어탕', '4.64', 1);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id) VALUES ('돌돌파스타', '010-5930-1352','울산 남구 대학로84번길 12 양지빌딩 1층', '4.4', 3);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id) VALUES ('hi', '010-1235-6523','울산 남구 대학로84번길 12 양지빌딩 1층', '1.4', 3);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
}
