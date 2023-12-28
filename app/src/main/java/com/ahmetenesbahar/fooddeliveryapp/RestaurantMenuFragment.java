package com.ahmetenesbahar.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.ahmetenesbahar.fooddeliveryapp.adapter.MenuAdapter;
import com.ahmetenesbahar.fooddeliveryapp.adapter.RestaurantsAdapter;
import com.ahmetenesbahar.fooddeliveryapp.databinding.FragmentRestaurantMenuBinding;
import com.ahmetenesbahar.fooddeliveryapp.databinding.FragmentRestaurantsBinding;
import com.ahmetenesbahar.fooddeliveryapp.models.Comment;
import com.ahmetenesbahar.fooddeliveryapp.models.Item;
import com.ahmetenesbahar.fooddeliveryapp.models.Menu;
import com.ahmetenesbahar.fooddeliveryapp.models.Restaurant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;


public class RestaurantMenuFragment extends Fragment {

    private FragmentRestaurantMenuBinding binding;
    MenuAdapter adapter;


    public RestaurantMenuFragment() {
        // Required empty public constructor
    }


    public static RestaurantMenuFragment newInstance(Restaurant restaurant) {
        RestaurantMenuFragment fragment = new RestaurantMenuFragment();
        Bundle args = new Bundle();
        args.putSerializable("clickedRestaurant", restaurant);
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
        adapter = new MenuAdapter(new ArrayList<>());
        List<Item> items = new ArrayList<>();


        Bundle bundle = getArguments();
        if (bundle != null) {
            Restaurant restaurantMenu = (Restaurant) bundle.getSerializable("clickedRestaurant");

            restaurantMenu.getMenus().forEach(menu -> {
                items.add(new Item(0, menu));

            });


            recyclerView.setAdapter(new MenuAdapter(items));

            binding.orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), CommentActivity.class);
                    intent.putExtra("clickedRestaurant", restaurantMenu);
                    startActivity(intent);
                }
            });

        }


        return view;
    }
}