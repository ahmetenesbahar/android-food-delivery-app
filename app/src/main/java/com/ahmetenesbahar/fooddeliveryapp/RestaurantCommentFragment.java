package com.ahmetenesbahar.fooddeliveryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
            Restaurant clickedRestaurant = (Restaurant) bundle.getSerializable("clickedRestaurant");

            if (clickedRestaurant != null) {
                String restaurantId = clickedRestaurant.getRestaurantId(); // Restoranın idsini al

                // Firebase veritabanından restoranı bul
                DatabaseReference restaurantRef = FirebaseDatabase.getInstance().getReference().child("Restaurants").child(restaurantId);

                // Restoranın commentsList'ini dinle
                restaurantRef.child("commentsList").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {

                            for (DataSnapshot commentSnapshot : snapshot.getChildren()) {
                                Comment comment = commentSnapshot.getValue(Comment.class);

                                items.add(new Item(0, comment));
                            }

                            Collections.reverse(items);

                            recyclerView.setAdapter(new CommentAdapter(items));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Hata durumunda işlemler
                    }
                });
            }

        }


        return view;
    }
}