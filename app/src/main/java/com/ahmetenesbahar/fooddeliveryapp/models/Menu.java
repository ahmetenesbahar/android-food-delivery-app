package com.ahmetenesbahar.fooddeliveryapp.models;

import java.io.Serializable;

public class Menu implements Serializable {
    // Buraya sonradan gerek kalmayabilir ben ui otursun diye yapıyorum bunu şuanda.
    private String RestaurantMenuImage;
    private String RestaurantMenuTitle;

    public Menu() {
    }
    public Menu(String restaurantMenuImage, String restaurantMenuTitle) {
        RestaurantMenuImage = restaurantMenuImage;
        RestaurantMenuTitle = restaurantMenuTitle;

    }

    public String getRestaurantMenuImage() {
        return RestaurantMenuImage;
    }

    public String getRestaurantMenuTitle() {
        return RestaurantMenuTitle;
    }

}
