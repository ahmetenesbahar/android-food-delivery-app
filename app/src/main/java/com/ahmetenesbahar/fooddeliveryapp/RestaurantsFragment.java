package com.ahmetenesbahar.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class RestaurantsFragment extends Fragment {

    private FragmentRestaurantsBinding binding;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public RestaurantsFragment() {
    }

    public static RestaurantsFragment newInstance(String param1, String param2) {
        RestaurantsFragment fragment = new RestaurantsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRestaurantsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        RecyclerView recyclerView = binding.restaurantsRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Item> items = new ArrayList<>();

        // Restoran öğeleri
        Restaurant restaurant1 = new Restaurant(R.drawable.background_image_signin, "Katkı Döner Dürüm 1");
        items.add(new Item(0, restaurant1));

        Restaurant restaurant2 = new Restaurant(R.drawable.background_image_signin, "Katkı Döner Dürüm 2");
        items.add(new Item(0, restaurant2));

        Restaurant restaurant3 = new Restaurant(R.drawable.background_organik, "Katkı Döner Dürüm 3");
        items.add(new Item(0, restaurant3));

        Restaurant restaurant4 = new Restaurant(R.drawable.background_organik, "Katkı Döner Dürüm 3");
        items.add(new Item(0, restaurant4));

        Restaurant restaurant5 = new Restaurant(R.drawable.background_organik, "Katkı Döner Dürüm 3");
        items.add(new Item(0, restaurant5));

        Restaurant restaurant6 = new Restaurant(R.drawable.background_organik, "Katkı Döner Dürüm 3");
        items.add(new Item(0, restaurant6));

        recyclerView.setAdapter(new RestaurantsAdapter(items));

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
                    // Burada tıklanan öğenin pozisyonunu alabilirsiniz
                    int position = rv.getChildAdapterPosition(childView);

                    // Tıklanan öğenin verilerini alabilirsiniz
                    Item clickedItem = items.get(position);

                    // Tıklanan öğenin verilerine göre bir işlem yapabilirsiniz
                    // Örneğin, ProfileFragment'a gitmek için
                    RestaurantFragment restaurantFragment = new RestaurantFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout , restaurantFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();


                }
                return false;
            }
        });


        return view;
    }
}
