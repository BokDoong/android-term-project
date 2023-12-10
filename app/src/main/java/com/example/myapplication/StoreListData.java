package com.example.myapplication;

public class StoreListData {
    public String imageUrl;
    public String storeName;
    public String categoryName;
    public String rating;
    public String location;

    public StoreListData(String imageUrl, String storeName, String categoryName, String rating, String location) {
        this.imageUrl = imageUrl;
        this.storeName = storeName;
        this.categoryName = categoryName;
        this.rating = rating;
        this.location = location;
    }
}
