package com.ahmetenesbahar.fooddeliveryapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmetenesbahar.fooddeliveryapp.adapter.MenuAdapter;
import com.ahmetenesbahar.fooddeliveryapp.adapter.RestaurantsAdapter;
import com.ahmetenesbahar.fooddeliveryapp.databinding.FragmentRestaurantMenuBinding;
import com.ahmetenesbahar.fooddeliveryapp.databinding.FragmentRestaurantsBinding;
import com.ahmetenesbahar.fooddeliveryapp.models.Item;
import com.ahmetenesbahar.fooddeliveryapp.models.Menu;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;


public class RestaurantMenuFragment extends Fragment {

    private FragmentRestaurantMenuBinding binding;

    public RestaurantMenuFragment() {
        // Required empty public constructor
    }


    public static RestaurantMenuFragment newInstance(String param1, String param2) {
        RestaurantMenuFragment fragment = new RestaurantMenuFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRestaurantMenuBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        RecyclerView recyclerView = binding.restaurantMenuRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Item> items = new ArrayList<>();

        // Restoran menü öğeleri

        Menu menuEleman1 = new Menu("https://i.lezzet.com.tr/images-xxlarge-secondary/tekirdag-kahvalti-mekanlari-tekirdagin-en-iyi-11-kahvalticisi-4ce7a817-3925-4998-a4a3-9bee73eec748.jpg", "Kahvaltı");
        items.add(new Item(0, menuEleman1));
        Menu menuEleman2 = new Menu("https://www.karaca.com/blog/wp-content/uploads/2023/02/lezzetli-hamburger-tarifi.webp", "Hamburger");
        items.add(new Item(0, menuEleman2));
        Menu menuEleman3 = new Menu("https://images.deliveryhero.io/image/fd-tr/LH/xsw7-hero.jpg", "Pizza");
        items.add(new Item(0, menuEleman3));
        Menu menuEleman4 = new Menu("https://d17wu0fn6x6rgz.cloudfront.net/img/w/tarif/mgt/patates-kizartmasi.webp", "Patates Kızartması");
        items.add(new Item(0, menuEleman4));
        Menu menuEleman5 = new Menu("https://www.koylummantievi.com/wp-content/uploads/2022/03/kola-kutukola-cocacola-2021-01-10.jpg", "Kola");
        items.add(new Item(0, menuEleman5));

        recyclerView.setAdapter(new MenuAdapter(items));


        return view;
    }
}