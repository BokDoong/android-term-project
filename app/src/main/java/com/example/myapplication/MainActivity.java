package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;

    EditText storeNameForSearchCategory;
    TextView editResult;
    ImageView imageView;
    Bitmap bitmap;
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

        imageView = findViewById(R.id.imageView);

        storeNameForSearchCategory = (EditText) findViewById(R.id.searchCategoryText);
        editResult = (TextView) findViewById(R.id.resultView);
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

    public void searchDetailInfo(View target) {
        String storeName = storeNameForSearchCategory.getText().toString();
        Cursor store_cursor;

        // 가게명으로 카테고리 id 조회
        store_cursor = db.rawQuery("SELECT * FROM store WHERE name='" + storeName + "';", null);

        if (store_cursor.moveToNext()) {
            // 카테고리 이름 조회
            Cursor category_cursor = db.rawQuery("SELECT name FROM category WHERE _id='" + store_cursor.getInt(5) + "';", null);

            // 커서에서 결과 읽어오기
            String s="Name      Tel                        Address      Rating    Category \r\n";
            // 가게 정보
            s += store_cursor.getString(1) + "  ";
            s += store_cursor.getString(2) + "  ";
            s += store_cursor.getString(3) + "  ";
            s += store_cursor.getString(4) + "         ";

            // 이미지 url 로 이미지 출력
            showStoreImage(store_cursor.getString(6));

            if (category_cursor.moveToNext()) {
                s += category_cursor.getString(0);
            }

            // 출력
            editResult.setText(s);
        } else {
            editResult.setText("DB에 없는 가게명 입니다.");
        }
    }

    private void showStoreImage(String imgUrl) {
        Thread uThread = new Thread() {
            @Override
            public void run(){
                try{
                    // 이미지 URL 경로
                    URL url = new URL(imgUrl);

                    // web에서 이미지를 가져와 ImageView에 저장할 Bitmap을 만든다.
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true); // 서버로부터 응답 수신
                    conn.connect(); //연결된 곳에 접속할 때 (connect() 호출해야 실제 통신 가능함)

                    InputStream is = conn.getInputStream(); //inputStream 값 가져오기
                    bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 변환

                }catch (MalformedURLException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        };

        uThread.start(); // 작업 Thread 실행

        try{
            //메인 Thread는 별도의 작업 Thread가 작업을 완료할 때까지 대기해야 한다.
            //join() 호출하여 별도의 작업 Thread가 종료될 때까지 메인 Thread가 기다리도록 한다.
            //join() 메서드는 InterruptedException을 발생시킨다.
            uThread.join();

            //작업 Thread에서 이미지를 불러오는 작업을 완료한 뒤
            //UI 작업을 할 수 있는 메인 Thread에서 ImageView에 이미지 지정
            imageView.setImageBitmap(bitmap);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}