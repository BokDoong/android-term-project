package com.example.myapplication;

import android.media.Image;
import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;

public class RealMainPage extends AppCompatActivity {

    // 10개의 이미지 및 텍스트를 어댑터에 추가
    int[] imageIds = {R.drawable.korean, R.drawable.chinese, R.drawable.western,
            R.drawable.japanese, R.drawable.cafe, R.drawable.bar,
            R.drawable.boonsik, R.drawable.asia, R.drawable.fastfood,
            R.drawable.restaurant};
    String[] textArray = {"한식", "중식", "양식", "일식", "카페",
            "술집", "분식", "아시아", "패스트푸드", "레스토랑"};

    private GridView gridView;
    private ImageTextAdapter imageTextAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main_page);

        gridView = (GridView) findViewById(R.id.grid_view);
        imageTextAdapter = new ImageTextAdapter(this, imageIds, textArray);

        gridView.setAdapter(imageTextAdapter);
    }
}