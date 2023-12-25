package com.ahmetenesbahar.fooddeliveryapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmetenesbahar.fooddeliveryapp.adapter.CommentAdapter;
import com.ahmetenesbahar.fooddeliveryapp.adapter.MenuAdapter;
import com.ahmetenesbahar.fooddeliveryapp.databinding.FragmentRestaurantCommentBinding;
import com.ahmetenesbahar.fooddeliveryapp.models.Comment;
import com.ahmetenesbahar.fooddeliveryapp.models.Item;

import java.util.ArrayList;
import java.util.List;


public class RestaurantCommentFragment extends Fragment {
    private FragmentRestaurantCommentBinding binding;

    public RestaurantCommentFragment() {
        // Required empty public constructor
    }


    public static RestaurantCommentFragment newInstance(String param1, String param2) {
        RestaurantCommentFragment fragment = new RestaurantCommentFragment();
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

        binding = FragmentRestaurantCommentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        RecyclerView recyclerView = binding.restaurantCommentRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Item> items = new ArrayList<>();

        Comment comment1 = new Comment("31", "Ahmet", "Çok güzel bir yemekti.", "https://www.karaca.com/blog/wp-content/uploads/2023/02/lezzetli-hamburger-tarifi.webp", 2);
        items.add(new Item(0, comment1));
        Comment comment2 = new Comment("32", "Ahmet", "Çok güzel bir yemekti.", "https://cdn.pixabay.com/photo/2017/03/23/19/57/asparagus-2169305_960_720.jpg", 3);
        items.add(new Item(0, comment2));

        recyclerView.setAdapter(new CommentAdapter(items));

        return view;
    }
}