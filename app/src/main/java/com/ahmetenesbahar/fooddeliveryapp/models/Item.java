package com.ahmetenesbahar.fooddeliveryapp.models;
// Burası birden fazla veri tipi göndermek için kullanılır. Bizim şuanlık 1 tane dolayısıyla RestaurantsAdapter içerisindeki if sorgusunu kaldırabiliriz.Zaten type 0 geliyor.
public class Item {

    private int type;
    private Object object;

    public Item(int type, Object object) {
        this.type = type;
        this.object = object;
    }

    public int getType() {
        return type;
    }

    public Object getObject() {
        return object;
    }
}
