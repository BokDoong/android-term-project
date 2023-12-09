package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

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
        LatLng UMID = new LatLng(35.54209, 129.2606);
        LatLng YH = new LatLng(35.54974, 129.2615);
        LatLng B = new LatLng(35.54075, 129.2554);
        LatLng MCD = new LatLng(35.55043, 129.2598);
        LatLng HGJ = new LatLng(35.54037, 129.2557);
        LatLng GL = new LatLng(35.54097, 129.2555);
        LatLng ACKTK = new LatLng(35.54273, 129.2603);
        LatLng SGNKGS = new LatLng(35.54134, 129.2608);
        LatLng SOBO = new LatLng(35.54316, 129.2604);
        LatLng MLH = new LatLng(35.54727, 129.2597);
        LatLng[] names = {UMID,YH,B,MCD,HGJ, GL, ACKTK, SGNKGS, SOBO, MLH};

        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM store", null);

        for(int i=0; i<10; i++){
            cursor.moveToNext();
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(names[i]);
            markerOptions.title(cursor.getString(1));
            markerOptions.snippet(cursor.getString(4));
            mMap.addMarker(markerOptions);
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UMID, 15));
    }
}