package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SQLiteDatabase db;
    DBHelper helper;
    ArrayList<LatLng> stores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        helper = new DBHelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = helper.getReadableDatabase();
        }
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    // NULL이 아닌 GoogleMap 객체를 파라미터로 제공해 줄 수 있을 때 호출
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
    }
    public void markCategory(View view) {
        Button button = (Button) findViewById(view.getId());
        Cursor cursor;
        Cursor categoryCursor;
        int category=0;

        categoryCursor = db.rawQuery("SELECT * FROM category", null);
        mMap.clear();
        while(categoryCursor.moveToNext()){
            if(categoryCursor.getString(1).equals(button.getText())){
                category = categoryCursor.getInt(0);
                break;
            }
        }
        cursor = db.rawQuery("SELECT * FROM store", null);
        LatLng location ;
        while(cursor.moveToNext()){
            location = new LatLng(cursor.getFloat(7), cursor.getFloat(8));
            if(cursor.getInt(5) == category){
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(location);
                markerOptions.title(cursor.getString(1));
                markerOptions.snippet(cursor.getString(4));
                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
            }
        }
    }
}