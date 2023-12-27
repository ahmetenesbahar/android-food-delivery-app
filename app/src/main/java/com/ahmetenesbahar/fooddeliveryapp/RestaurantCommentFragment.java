package com.ahmetenesbahar.fooddeliveryapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmetenesbahar.fooddeliveryapp.adapter.CommentAdapter;
import com.ahmetenesbahar.fooddeliveryapp.adapter.MenuAdapter;
import com.ahmetenesbahar.fooddeliveryapp.databinding.FragmentRestaurantCommentBinding;
import com.ahmetenesbahar.fooddeliveryapp.models.Comment;
import com.ahmetenesbahar.fooddeliveryapp.models.Item;
import com.ahmetenesbahar.fooddeliveryapp.models.Restaurant;

import java.util.ArrayList;
import java.util.List;


public class RestaurantCommentFragment extends Fragment {
    private FragmentRestaurantCommentBinding binding;

    public RestaurantCommentFragment() {
        // Required empty public constructor
    }


    public static RestaurantCommentFragment newInstance(Restaurant restaurant) {
        RestaurantCommentFragment fragment = new RestaurantCommentFragment();
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

        binding = FragmentRestaurantCommentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        RecyclerView recyclerView = binding.restaurantCommentRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Item> items = new ArrayList<>();


        Bundle bundle = getArguments();
        if (bundle != null) {
            Restaurant restaurantComment = (Restaurant) bundle.getSerializable("clickedRestaurant");

            restaurantComment.getComments().forEach(comment -> {
                Log.d("comment", comment.getCommentImage());
                items.add(new Item(0, comment));
            });


            recyclerView.setAdapter(new CommentAdapter(items));

        }


        return view;
    }
}