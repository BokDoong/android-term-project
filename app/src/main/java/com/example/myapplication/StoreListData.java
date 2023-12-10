package com.example.myapplication;

public class StoreListData {
    public String imageUrl;
    public String storeName;
    public String categoryName;
    public String rating;
    public String location;
    public Long heart;

    public StoreListData(String imageUrl, String storeName, String categoryName, String rating, String location, Long heart) {
        this.imageUrl = imageUrl;
        this.storeName = storeName;
        this.categoryName = categoryName;
        this.rating = rating;
        this.location = location;
        this.heart = heart;
    }
}
