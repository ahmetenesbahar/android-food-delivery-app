package com.ahmetenesbahar.fooddeliveryapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmetenesbahar.fooddeliveryapp.databinding.ItemContainerRestaurantsBinding;
import com.ahmetenesbahar.fooddeliveryapp.models.Item;
import com.ahmetenesbahar.fooddeliveryapp.models.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder> {
    private List<Item> items;




    public RestaurantsAdapter(List<Item> items) {
        this.items = items;

    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerRestaurantsBinding binding = ItemContainerRestaurantsBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new RestaurantViewHolder(binding);
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
        private ItemContainerRestaurantsBinding binding;

        public RestaurantViewHolder(ItemContainerRestaurantsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void setRestaurantData(Restaurant restaurant){
            Picasso.get().load(restaurant.getRestaurantImage()).into(binding.imageRestaurant);
            binding.textRestaurantTitle.setText(restaurant.getRestaurantTitle());

        }
    }
}