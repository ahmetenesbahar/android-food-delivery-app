package com.ahmetenesbahar.fooddeliveryapp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Restaurant implements Serializable {
    private String restaurantImage;
    private String restaurantTitle;

    private String restaurantId;

    List<Comment> comments;
    List<Menu> menus;


    public Restaurant() {
    }

    public Restaurant(String restaurantImage, String restaurantTitle, String restaurantId) {
        this.restaurantImage = restaurantImage;
        this.restaurantTitle = restaurantTitle;
        this.restaurantId = restaurantId;
        this.comments = new ArrayList<>();
        this.menus = new ArrayList<>();

    }

    public String getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(String restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

    public String getRestaurantTitle() {
        return restaurantTitle;
    }

    public void setRestaurantTitle(String restaurantTitle) {
        this.restaurantTitle = restaurantTitle;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}