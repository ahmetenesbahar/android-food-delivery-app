package com.ahmetenesbahar.fooddeliveryapp.models;

public class Menu {
    // Buraya sonradan gerek kalmayabilir ben ui otursun diye yapıyorum bunu şuanda.
    private String RestaurantMenuImage;
    private String RestaurantMenuTitle;

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
