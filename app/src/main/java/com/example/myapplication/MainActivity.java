package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;

    EditText storeNameForSearchCategory;
    TextView editResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DBHelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = helper.getReadableDatabase();
        }

        storeNameForSearchCategory = (EditText) findViewById(R.id.searchCategoryText);
        editResult = (TextView) findViewById(R.id.resultView);

        // 더미 데이터 추가
        insertDummyData(db);
    }

    // 모든 카테고리 조회
    public void searchAllCategory(View target) {
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM category", null);

        String s="Id         Name \r\n";
        while (cursor.moveToNext()) {
            s += cursor.getString(0) + "    ";
            s += cursor.getString(1) + "    \r\n";
        }
        editResult.setText(s);
    }

    // 모든 가게 조회
    public void searchAllStore(View target) {
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM store", null);

        String s="Id         Name            Tel            Address            Rating \r\n";
        while (cursor.moveToNext()) {
            s += cursor.getString(0) + "    ";
            s += cursor.getString(1) + "    ";
            s += cursor.getString(2) + "    ";
            s += cursor.getString(3) + "    ";
            s += cursor.getString(4) + "    \r\n";
        }

        editResult.setText(s);
    }

    public void searchCategory(View target) {
        String storeName = storeNameForSearchCategory.getText().toString();
        Cursor store_cursor;

        // 가게명으로 카테고리 id 조회
        store_cursor = db.rawQuery("SELECT category_id FROM store WHERE name='" + storeName + "';", null);

        if (store_cursor.moveToNext()) {
            // 카테고리 이름 조회
            int categoryId = store_cursor.getInt(0);
            Cursor category_cursor = db.rawQuery("SELECT name FROM category WHERE _id='" + categoryId + "';", null);

            // 결과
            if (category_cursor.moveToNext()) {
                editResult.setText(category_cursor.getString(0));
            }
        } else {
            editResult.setText("DB에 없는 가게명 입니다.");
        }
    }

    private void insertDummyData(SQLiteDatabase db) {
        // 카테고리
        db.execSQL("INSERT INTO category VALUES (1, '한식');");
        db.execSQL("INSERT INTO category VALUES (2, '중식');");
        db.execSQL("INSERT INTO category VALUES (3, '양식');");
        db.execSQL("INSERT INTO category VALUES (4, '일식');");
        db.execSQL("INSERT INTO category VALUES (5, '카페');");
        db.execSQL("INSERT INTO category VALUES (6, '술집');");
        db.execSQL("INSERT INTO category VALUES (7, '분식');");
        db.execSQL("INSERT INTO category VALUES (8, '아시아');");
        db.execSQL("INSERT INTO category VALUES (9, '패스트푸드');");
        db.execSQL("INSERT INTO category VALUES (10, '레스토랑');");

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
}