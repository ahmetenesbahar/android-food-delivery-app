package com.ahmetenesbahar.fooddeliveryapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmetenesbahar.fooddeliveryapp.R;
import com.ahmetenesbahar.fooddeliveryapp.models.Item;
import com.ahmetenesbahar.fooddeliveryapp.models.Restaurant;

import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder> {
    private List<Item> items;

    public RestaurantsAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RestaurantViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_restaurants,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
            Restaurant restaurant = (Restaurant) items.get(position).getObject();
            ((RestaurantViewHolder) holder).setRestaurantData(restaurant);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {
private ImageView restaurantImage;
private TextView restaurantTitle;
            public RestaurantViewHolder(@NonNull View itemView) {
                super(itemView);
                restaurantImage=itemView.findViewById(R.id.imageRestaurant);
                restaurantTitle=itemView.findViewById(R.id.textRestaurantTitle);


            }

            void setRestaurantData(Restaurant restaurant){
                restaurantImage.setImageResource(restaurant.getRestaurantImage());
                restaurantTitle.setText(restaurant.getRestaurantTitle());

            }
    }
}
