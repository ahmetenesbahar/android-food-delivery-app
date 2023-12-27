package com.ahmetenesbahar.fooddeliveryapp.models;

import java.io.Serializable;

public class Menu implements Serializable {

    private String restaurantMenuImage;
    private String restaurantMenuTitle;

    public Menu() {
    }

    public Menu(String restaurantMenuImage, String restaurantMenuTitle) {
        this.restaurantMenuImage = restaurantMenuImage;
        this.restaurantMenuTitle = restaurantMenuTitle;
    }

    public String getRestaurantMenuImage() {
        return restaurantMenuImage;
    }

    public String getRestaurantMenuTitle() {
        return restaurantMenuTitle;
    }

}
