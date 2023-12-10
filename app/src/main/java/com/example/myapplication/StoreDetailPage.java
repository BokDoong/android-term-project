package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class StoreDetailPage extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    SQLiteDatabase db;
    DBHelper helper;
    ArrayList<LatLng> stores = new ArrayList<>();
    Bitmap bitmap;
    ImageView imageView;
    TextView storeName;
    TextView category;
    TextView address;
    TextView rating;
    TextView phone;
    String store = "밥";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail_page);
        storeName = (TextView) findViewById(R.id.name_id);
        category = (TextView) findViewById(R.id.category_id);
        address = (TextView) findViewById(R.id.address_id);
        rating = (TextView) findViewById(R.id.rating_id);
        phone = (TextView) findViewById(R.id.phone_id);
        imageView = (ImageView) findViewById(R.id.store_image);
        helper = new DBHelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = helper.getReadableDatabase();
        }
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapDetail);
        mapFragment.getMapAsync(this);
        hingguri();

    }

    public void hingguri() {
        Cursor store_cursor;

        // 가게명으로 데이터베이스 조회
        store_cursor = db.rawQuery("SELECT * FROM store WHERE name='" + store + "';", null);
        if (store_cursor.moveToNext()) {
            // 카테고리 이름 조회
            Cursor category_cursor = db.rawQuery("SELECT name FROM category WHERE _id='" + (store_cursor.getInt(5)+1) + "';", null);
            // 이미지 url 로 이미지 출력
            showStoreImage(store_cursor.getString(6));
            storeName.setText(store_cursor.getString(1));
            rating.setText(store_cursor.getString(4));
            address.setText(store_cursor.getString(3));
            category.setText(store_cursor.getString(5));
            phone.setText(store_cursor.getString(2));
            if (category_cursor.moveToNext()) {
                category.setText(category_cursor.getString(0));
            }
        }
    }

    // 이미지 url로 불러 오기
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

    // NULL이 아닌 GoogleMap 객체를 파라미터로 제공해 줄 수 있을 때 호출
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM store WHERE name='" + store + "';", null);

        LatLng location ;
        if(cursor.moveToNext()){
            location = new LatLng(cursor.getFloat(7), cursor.getFloat(8));
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(location);
            markerOptions.title(cursor.getString(1));
            markerOptions.snippet(cursor.getString(4));
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
        }
    }
}
