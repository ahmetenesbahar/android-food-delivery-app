package com.ahmetenesbahar.fooddeliveryapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmetenesbahar.fooddeliveryapp.databinding.FragmentRestaurantBinding;
import com.ahmetenesbahar.fooddeliveryapp.databinding.FragmentRestaurantsBinding;


public class RestaurantFragment extends Fragment {

    FragmentRestaurantBinding binding;


    public RestaurantFragment() {
        // Required empty public constructor
    }


    public static RestaurantFragment newInstance(String param1, String param2) {
        RestaurantFragment fragment = new RestaurantFragment();
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

        binding = FragmentRestaurantBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        replaceFragment(new RestaurantMenuFragment());

        binding.restaurantNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.restaurant_menu) {
                replaceFragment(new RestaurantMenuFragment());
            } else if (itemId == R.id.restaurant_comments) {
                replaceFragment(new RestaurantCommentFragment());
            }
            return true;
        });
        
        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.restaurant_layout, fragment);
        fragmentTransaction.commit();
    }
}