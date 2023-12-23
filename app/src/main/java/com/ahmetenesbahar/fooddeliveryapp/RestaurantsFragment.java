package com.ahmetenesbahar.fooddeliveryapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmetenesbahar.fooddeliveryapp.adapter.RestaurantsAdapter;
import com.ahmetenesbahar.fooddeliveryapp.models.Item;
import com.ahmetenesbahar.fooddeliveryapp.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RestaurantsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RestaurantsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RestaurantsFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_restaurants, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.restaurantsRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //!Burada KENDİM RESTORANT YARATTIM VE BUNU RECYCLERVIEW İÇİNDE GÖSTERMEK İSTİYORUM

        List<Item> items = new ArrayList<>();

        //Restaurant
        Restaurant restaurant1= new Restaurant(R.drawable.background_image_signin, "Katkı Döner Dürüm 1","Zıbab Yaparız Ivj Yaparız");
        items.add(new Item(0,restaurant1));
        Restaurant restaurant2= new Restaurant(R.drawable.background_image_signin, "Katkı Döner Dürüm 2","Zıbab Yaparız Ivj Yaparız");
        items.add(new Item(0,restaurant2));
        Restaurant restaurant3= new Restaurant(R.drawable.background_organik, "Katkı Döner Dürüm 3","Zıbab Yaparız Ivj Yaparız");
        items.add(new Item(0,restaurant3));
        Restaurant restaurant4= new Restaurant(R.drawable.background_organik, "Katkı Döner Dürüm 3","Zıbab Yaparız Ivj Yaparız");
        items.add(new Item(0,restaurant4));
        Restaurant restaurant5= new Restaurant(R.drawable.background_organik, "Katkı Döner Dürüm 3","Zıbab Yaparız Ivj Yaparız");
        items.add(new Item(0,restaurant5));
        Restaurant restaurant6= new Restaurant(R.drawable.background_organik, "Katkı Döner Dürüm 3","Zıbab Yaparız Ivj Yaparız");
        items.add(new Item(0,restaurant6));

        recyclerView.setAdapter(new RestaurantsAdapter(items));

        return view;
    }
}