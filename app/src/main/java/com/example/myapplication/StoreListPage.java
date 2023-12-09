package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StoreListPage extends AppCompatActivity {
    Button beforeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list_page);
    }

    public void CategoryButtonClicked(View v) {
        if(beforeButton != null){
            beforeButton.setBackgroundResource(R.drawable.button_design);
            beforeButton.setTextColor(Color.parseColor("#000000"));
        }

        Button button = (Button) findViewById(v.getId());
        button.setBackgroundResource(R.drawable.clicked_button_design);
        button.setTextColor(Color.parseColor("#FFFFFF"));
        beforeButton = button;
    }
}