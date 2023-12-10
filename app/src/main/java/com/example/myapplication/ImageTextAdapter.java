package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageTextAdapter extends BaseAdapter {

    private Context context;
    private int[] imageIds;
    private String[] textArray;

    public ImageTextAdapter(Context context, int[] imageIds, String[] textArray) {
        this.context = context;
        this.imageIds = imageIds;
        this.textArray = textArray;
    }

    @Override
    public int getCount() {
        return imageIds.length;
    }

    @Override
    public Object getItem(int position) {
        return textArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.grid_items, viewGroup, false);
        }

        position %= imageIds.length;

        ImageView image = (ImageView) view.findViewById(R.id.main_category_iv);
        TextView textView = view.findViewById(R.id.main_category_tv);

        image.setImageResource(imageIds[position]);
        textView.setText(textArray[position]);

        return view;
    }
}