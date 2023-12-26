package com.ahmetenesbahar.fooddeliveryapp.models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String restaurantImage;
    private String restaurantTitle;

    List<Comment> comments;
    List<Menu> menus;

    public Restaurant( String restaurantImage, String restaurantTitle) {
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
}



