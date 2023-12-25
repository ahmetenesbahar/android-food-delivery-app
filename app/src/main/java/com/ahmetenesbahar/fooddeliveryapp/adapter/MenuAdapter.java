package com.ahmetenesbahar.fooddeliveryapp.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmetenesbahar.fooddeliveryapp.R;
import com.ahmetenesbahar.fooddeliveryapp.databinding.ItemContainerMenuBinding;
import com.ahmetenesbahar.fooddeliveryapp.models.Item;
import com.ahmetenesbahar.fooddeliveryapp.models.Menu;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // Menu gibi burada da belirli şeylere ihtiyaç kalmayabilir UI otursun diye yapıyorum şuanda.

    private List<Item> items;

    public MenuAdapter(List<Item> items) {
        this.items = items;
    }

    //Generate implament methods dan gelenler bunlar hangilerini kullanacağımıza bakacağız


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
         Item da typelar 0 1 2 diye gidiyor şuan burada tek menü ile ilgili işlemler olduğundan 0 yazmaya gerek yok ama örneğin hem menü hem reklam sınıfım olsa 0 1 vs diye işlem yapacaktık
         */
        ItemContainerMenuBinding binding = ItemContainerMenuBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new MenuViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Burada da üstteki 0 1 mevzusu geçerli
        Menu menu = (Menu) items.get(position).getObject();
        ((MenuViewHolder) holder).setMenuData(menu);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    //Burada bitiyor aşağısı elimle yazdığım metodlar
    static class MenuViewHolder extends RecyclerView.ViewHolder {

        private ItemContainerMenuBinding binding;


        public MenuViewHolder(ItemContainerMenuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void setMenuData(Menu menu) {
            Picasso.get().load(menu.getRestaurantMenuImage()).into(binding.imageRestaurantMenu);
            binding.textRestaurantMenuTitle.setText(menu.getRestaurantMenuTitle());
        }


    }
}
