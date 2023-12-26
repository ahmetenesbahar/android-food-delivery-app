package com.ahmetenesbahar.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmetenesbahar.fooddeliveryapp.adapter.RestaurantsAdapter;
import com.ahmetenesbahar.fooddeliveryapp.databinding.FragmentRestaurantsBinding;
import com.ahmetenesbahar.fooddeliveryapp.models.Item;
import com.ahmetenesbahar.fooddeliveryapp.models.Restaurant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsFragment extends Fragment {

    private FragmentRestaurantsBinding binding;

    DatabaseReference databaseReference;

    RestaurantsAdapter adapter;


    public RestaurantsFragment() {
    }

    public static RestaurantsFragment newInstance(String param1, String param2) {
        RestaurantsFragment fragment = new RestaurantsFragment();
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
        binding = FragmentRestaurantsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        RecyclerView recyclerView = binding.restaurantsRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new RestaurantsAdapter(new ArrayList<>());
        databaseReference= FirebaseDatabase.getInstance().getReference("Restaurants");

        List<Item> items = new ArrayList<>();
        //Buradan itibaren

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot restaurantSnapshot : dataSnapshot.getChildren()) {

                    Restaurant restaurant = restaurantSnapshot.getValue(Restaurant.class);

                    items.add(new Item(0, restaurant));

                }


                recyclerView.setAdapter(new RestaurantsAdapter(items));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error yeri
            }
        });







        recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View childView = rv.findChildViewUnder(e.getX(), e.getY());
                if (childView != null && gestureDetector.onTouchEvent(e)) {
                    // Burada tıklanan öğenin pozisyonunu alabiliyoruz
                    int position = rv.getChildAdapterPosition(childView);

                    // Tıklanan öğenin verilerini almaya yarıyor
                    Item clickedItem = items.get(position);
                    Restaurant clickedRestaurant = (Restaurant) clickedItem.getObject();
                    RestaurantFragment restaurantFragment = new RestaurantFragment();

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("clickedRestaurant", clickedRestaurant);
                    restaurantFragment.setArguments(bundle);


                    // Tıklanan öğenin verilerine göre bir işlem yapmaya yarıyor
                    // Örneğin, ProfileFragment'a gitmek için

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, restaurantFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();


                }
                return false;
            }
        });


        return view;
    }
}