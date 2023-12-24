package com.ahmetenesbahar.fooddeliveryapp.models;

public class Restaurant {
    private int RestaurantImage;
    private String RestaurantTitle, restaurant;

    public Restaurant(int restaurantImage, String restaurantTitle) {
        RestaurantImage = restaurantImage;
        RestaurantTitle = restaurantTitle;

    }

    public int getRestaurantImage() {
        return RestaurantImage;
    }

    public String getRestaurantTitle() {
        return RestaurantTitle;
    }


}
