package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

        storeList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                System.out.println("진짜 지랄하지마라");
                Toast.makeText(getBaseContext(), "asdfasdf", Toast.LENGTH_SHORT).show();
            }
        });
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
                        store_cursor.getString(4), store_cursor.getString(3)));
            }
        }

        // 어댑터에게 데이터 변경을 알리고 리스트뷰를 갱신
        storeListAdapter.notifyDataSetChanged();
    }

    public class StoreListAdapter extends ArrayAdapter<StoreListData> {
        private final Activity context;
        public StoreListAdapter(Activity context) {
            super(context, R.layout.store_list, storeListDatas);
            this.context = context;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.store_list, null, true);

            ImageView storeImage = (ImageView) rowView.findViewById(R.id.store_list_image);
            showStoreImage(storeListDatas.get(i).imageUrl, storeImage);
            TextView storeName = (TextView) rowView.findViewById(R.id.store_name);
            storeName.setText(storeListDatas.get(i).storeName);
            TextView categoryName = (TextView) rowView.findViewById(R.id.category_name);
            categoryName.setText(storeListDatas.get(i).categoryName);
            TextView rating = (TextView) rowView.findViewById(R.id.rating);
            rating.setText(storeListDatas.get(i).rating);
            TextView location = (TextView) rowView.findViewById(R.id.location);
            location.setText(storeListDatas.get(i).location);

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
                //메인 Thread는 별도의 작업 Thread가 작업을 완료할 때까지 대기해야 한다.
                //join() 호출하여 별도의 작업 Thread가 종료될 때까지 메인 Thread가 기다리도록 한다.
                //join() 메서드는 InterruptedException을 발생시킨다.
                uThread.join();

                //작업 Thread에서 이미지를 불러오는 작업을 완료한 뒤
                //UI 작업을 할 수 있는 메인 Thread에서 ImageView에 이미지 지정
                imageView.setImageBitmap(bitmap);
            }catch (InterruptedException e){
                System.out.println("에러3");
            }
        }
    }
}