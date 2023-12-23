package com.ahmetenesbahar.fooddeliveryapp.models;

public class Restaurant {
    private int RestaurantImage;
    private String RestaurantTitle, restaurant;

    public Restaurant(int restaurantImage, String restaurantTitle, String restaurant) {
        RestaurantImage = restaurantImage;
        RestaurantTitle = restaurantTitle;
        this.restaurant = restaurant;
    }

    public int getRestaurantImage() {
        return RestaurantImage;
    }

    public String getRestaurantTitle() {
        return RestaurantTitle;
    }

    public String getRestaurant() {
        return restaurant;
    }
}
