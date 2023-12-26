package com.ahmetenesbahar.fooddeliveryapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Menu implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

    }
}
