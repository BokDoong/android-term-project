package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class StoreListPage extends AppCompatActivity {
    private Button beforeCategoryButton;
    private ListView storeList;
    private ArrayList<StoreListData> storeListDatas = new ArrayList<>();
    private Bitmap bitmap;
    DBHelper helper;
    SQLiteDatabase db;
    StoreListAdapter storeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list_page);

        // DB 초기화
        helper = new DBHelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = helper.getReadableDatabase();
        }

        // 리스트 뷰 + 어댑터 연결
        storeListAdapter = new StoreListAdapter(StoreListPage.this);
        storeList = (ListView) findViewById(R.id.store_list);
        storeList.setAdapter(storeListAdapter);

        // 메인 -> 카테고리 페이지
        Intent secondIntent = getIntent();
        String category = secondIntent.getStringExtra("카테고리");
        movedByMainPage(category);

        // ListView 이벤트
        // 1. ListView 클릭했을 때 -> 상세페이지 이동
        storeList.setOnItemClickListener((parent, v, position, id) -> {
            Intent myIntent = new Intent(StoreListPage.this, StoreDetailPage.class);
            myIntent.putExtra("가게", storeListDatas.get(position).storeName);
            startActivity(myIntent);
        });
    }

    public void onClick(View v){
        finish();
    }
    public void CategoryButtonClicked(View v) {

        Cursor category_cursor;
        Cursor store_cursor;

        // 기존의 가게 데이터 초기화
        storeListDatas.clear();

        // 이미 선택된 버튼 기본 상태로 전환
        if(beforeCategoryButton != null){
            beforeCategoryButton.setBackgroundResource(R.drawable.button_design);
            beforeCategoryButton.setTextColor(Color.parseColor("#000000"));
        }

        // 버튼 선택
        Button button = (Button) findViewById(v.getId());
        button.setBackgroundResource(R.drawable.clicked_button_design);
        button.setTextColor(Color.parseColor("#FFFFFF"));
        beforeCategoryButton = button;

        // DB 에서 선택된 카테고리의 가게 데이터 ArrayList 에 추가
        // 1. 선택된 버튼의 텍스트(카테고리) 추출
        // 2. 해당 카테고리의 Id 조회
        String selectedCategory = button.getText().toString();
        category_cursor = db.rawQuery("SELECT * FROM category WHERE name='" + selectedCategory + "';", null);
        if (category_cursor.moveToNext()) {
            // 3. 카테고리 Id 를 갖는 가게 조회 + ArrayList 에 추가
            store_cursor = db.rawQuery("SELECT * FROM store WHERE category_id='" + (category_cursor.getInt(0)-1) + "';", null);
            while (store_cursor.moveToNext()) {
                storeListDatas.add(new StoreListData(store_cursor.getString(6), store_cursor.getString(1), selectedCategory,
                        store_cursor.getString(4), store_cursor.getString(3), store_cursor.getInt(9)));
            }
        }

        // 어댑터에게 데이터 변경을 알리고 리스트뷰를 갱신
        storeListAdapter.notifyDataSetChanged();
    }

    public void movedByMainPage(String category) {

        String[] storeArray = {"한식", "레스토랑", "중식", "양식", "일식", "카페",
                "술집", "분식", "아시아", "패스트푸드"};

        int index = 0;
        for (int i = 0; i < 10; i++) {
            if (storeArray[i].equals(category)) {
                index = i;
                break;
            }
        }

        int startButtonId = R.id.btn1;
        Button button = (Button) findViewById(startButtonId + index);
        button.performClick();
    }

    public class StoreListAdapter extends ArrayAdapter<StoreListData> {
        private final Activity context;
        private Cursor cursor;
        public StoreListAdapter(Activity context) {
            super(context, R.layout.store_list, storeListDatas);
            this.context = context;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = context.getLayoutInflater();

            View rowView = inflater.inflate(R.layout.store_list, null, true);

            ImageView storeImage = (ImageView) rowView.findViewById(R.id.store_list_image);
            TextView storeName = (TextView) rowView.findViewById(R.id.store_name);
            TextView categoryName = (TextView) rowView.findViewById(R.id.category_name);
            TextView rating = (TextView) rowView.findViewById(R.id.rating);
            TextView location = (TextView) rowView.findViewById(R.id.location);
            ImageButton heartButton = (ImageButton) rowView.findViewById(R.id.heartButton);

            showStoreImage(storeListDatas.get(i).imageUrl, storeImage);
            storeName.setText(storeListDatas.get(i).storeName);
            categoryName.setText(storeListDatas.get(i).categoryName);
            rating.setText(storeListDatas.get(i).rating);
            location.setText(storeListDatas.get(i).location);
            // 1이면 꽉찬 하트 설정
            if(storeListDatas.get(i).heart == 1){
                heartButton.setImageResource(R.drawable.heart);
            }
            heartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 0,1 판별
                    if (storeListDatas.get(i).heart == 0) {
                        // db.rawQuery("SELECT * FROM category WHERE name='" + selectedCategory + "';", null);
                        // 0 이면 1 로 DB 에서 바꾸고, 꽉 찬 하트로 바꾸기
                        db.execSQL("UPDATE store SET heart=1 WHERE name='" + storeName + "';");
                        storeListDatas.get(i).heart = 1;
                        heartButton.setImageResource(R.drawable.heart);
                    } else {
                        // 1 이면 0 으로 DB 에서 바꾸고, 빈 하트로 바꾸기
                        db.execSQL("UPDATE store SET heart=0 WHERE name='" + storeName + "';");
                        storeListDatas.get(i).heart = 0;
                        heartButton.setImageResource(R.drawable.unselected_heart);
                    }
                }
            });
            return rowView;
        }

        private void showStoreImage(String imgUrl, ImageView imageView) {

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
                        System.out.println("에러1");
                    }catch (IOException e){
                        System.out.println("에러2");
                    }
                }
            };
            uThread.start(); // 작업 Thread 실행
            try{
                uThread.join();
                imageView.setImageBitmap(bitmap);
            }catch (InterruptedException e){
                System.out.println("에러3");
            }

        }
    }
}