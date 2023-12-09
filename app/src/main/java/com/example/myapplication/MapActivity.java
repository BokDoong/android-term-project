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
        LatLng DMJ = new LatLng(35.54433, 129.2610);
        LatLng JBSH = new LatLng(35.54074, 129.2606);
        LatLng DDPST = new LatLng(35.54195, 129.2607);
        LatLng HCS = new LatLng(35.54530, 129.2612);
        LatLng IH = new LatLng(35.55198, 129.2683);
        LatLng RMJ = new LatLng(35.54046, 129.2624);
        LatLng HWD = new LatLng(35.54055, 129.2603);
        LatLng TDS = new LatLng(35.54297, 129.2604);
        LatLng CCDGS = new LatLng(35.54142, 129.2610);
        LatLng SSMCG = new LatLng(35.53974, 129.2585);
        LatLng GMDD = new LatLng(35.54301, 129.2613);
        LatLng HLMJ = new LatLng(35.54203, 129.2600);
        LatLng CUCR = new LatLng(35.54150, 129.2600);
        LatLng ISD = new LatLng(35.54130, 129.2604);
        LatLng SSCG = new LatLng(35.55086, 129.2594);
        LatLng DJIYG = new LatLng(35.54819, 129.2604);
        LatLng DKDK = new LatLng(35.54156, 129.2593);
        LatLng DSBGD = new LatLng(35.54317, 129.2602);
        LatLng PABS = new LatLng(35.54231, 129.2599);
        LatLng TSRM = new LatLng(35.54319, 129.2602);
        LatLng JTG = new LatLng(35.54110, 129.2600);
        LatLng UDDBG = new LatLng(35.54316, 129.2608);
        LatLng AJIC = new LatLng(35.54097, 129.2593);
        LatLng SJDBG = new LatLng(35.54295, 129.2601);
        LatLng GDJ = new LatLng(35.54096, 129.2559);
        LatLng BGK = new LatLng(35.54869, 129.2624);
        LatLng MDND = new LatLng(35.54116, 129.2619);
        LatLng MSTC = new LatLng(35.54671, 129.2591);
        LatLng CCKTK = new LatLng(35.54289, 129.2609);
        LatLng WTBG = new LatLng(35.54110, 129.2609);
        LatLng YLWLIT = new LatLng(35.54798, 129.2576);
        LatLng SGAK = new LatLng(35.54350, 129.2607);
        LatLng RRCST = new LatLng(35.54220, 129.2611);
        LatLng SRDA = new LatLng(35.54349, 129.2603);
        LatLng SGRCF = new LatLng(35.53544, 129.2515);
        LatLng CRMLT = new LatLng(35.54157, 129.2613);
        LatLng PGD = new LatLng(35.54935, 129.2615);
        LatLng[] names = {UMID,YH,B,MCD,HGJ, GL, ACKTK, SGNKGS, SOBO, MLH};
        ArrayList<LatLng> stores = new ArrayList<>();
        stores.add(UMID);
        stores.add(YH);
        stores.add(B);
        stores.add(MCD);
        stores.add(HGJ);
        stores.add(GL);
        stores.add(ACKTK);
        stores.add(SGNKGS);
        stores.add(SOBO);
        stores.add(MLH);
        stores.add(DMJ);
        stores.add(JBSH);
        stores.add(DDPST);
        stores.add(HCS);
        stores.add(IH);
        stores.add(RMJ);
        stores.add(HWD);
        stores.add(TDS);
        stores.add(CCDGS);
        stores.add(SSMCG);
        stores.add(GMDD);
        stores.add(HLMJ);
        stores.add(CUCR);
        stores.add(ISD);
        stores.add(SSCG);
        stores.add(DJIYG);
        stores.add(DKDK);
        stores.add(DSBGD);
        stores.add(PABS);
        stores.add(TSRM);
        stores.add(JTG);
        stores.add(UDDBG);
        stores.add(AJIC);
        stores.add(SJDBG);
        stores.add(GDJ);
        stores.add(BGK);
        stores.add(MDND);
        stores.add(MSTC);
        stores.add(CCKTK);
        stores.add(WTBG);
        stores.add(YLWLIT);
        stores.add(SGAK);
        stores.add(RRCST);
        stores.add(SRDA);
        stores.add(SGRCF);
        stores.add(CRMLT);
        stores.add(PGD);

        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM store", null);
        for(int i = 0; i <stores.size(); i++){
            cursor.moveToNext();
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(stores.get(i));
            markerOptions.title(cursor.getString(1));
            markerOptions.snippet(cursor.getString(4));
            mMap.addMarker(markerOptions);
        }

//        for(int i=0; i<10; i++){
//            cursor.moveToNext();
//            MarkerOptions markerOptions = new MarkerOptions();
//            markerOptions.position(names[i]);
//            markerOptions.title(cursor.getString(1));
//            markerOptions.snippet(cursor.getString(4));
//            mMap.addMarker(markerOptions);
//        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UMID, 16));
    }
}