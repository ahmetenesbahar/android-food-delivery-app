package com.ahmetenesbahar.fooddeliveryapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Restaurant implements Parcelable {
    private String restaurantImage;
    private String restaurantTitle;

    List<Comment> comments;
    List<Menu> menus;

    public Restaurant() {
    }

    public Restaurant(String restaurantImage, String restaurantTitle) {
        this.restaurantImage = restaurantImage;
        this.restaurantTitle = restaurantTitle;
        this.comments = new ArrayList<>();
        this.menus = new ArrayList<>();

    }

    public String  getRestaurantImage() {
        return restaurantImage;
    }

    public String getRestaurantTitle() {
        return restaurantTitle;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void addMenu(Menu menu) {
        menus.add(menu);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(restaurantImage);
        dest.writeString(restaurantTitle);
        dest.writeTypedList(comments);
        dest.writeTypedList(menus);
    }
}



