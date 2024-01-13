package com.ahmetenesbahar.fooddeliveryapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
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

    private String CHANNEL_ID = "order_now_id";
    private String CHANNEL_NAME = "order_now_name";
    private String CHANNEL_DESCRIPTION = "SİPARİŞİNİZ ALINDI";
    private int DELAY_MILLISECONDS =5000;


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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription(CHANNEL_DESCRIPTION);

            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotificationAfterDelay() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                showNotification();
            }
        }, DELAY_MILLISECONDS);
    }

    private void showNotification() {
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.appicon);

        Intent menuIntent = new Intent(getActivity(), MenuActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, menuIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_active_24)
                .setLargeIcon(largeIcon)
                .setContentTitle("SİPARİŞİNİZ ALINDI")
                .setContentText("En geç 30 dk içerisinde kapında!!!")
                .setStyle(new NotificationCompat
                        .BigTextStyle()
                        .setBigContentTitle("SİPARİŞİNİZ ALINDI")
                        .bigText("Bizi tercih ettiğiniz için teşekkür ederiz.Bu bildirime tıklayarak alışverişe devam edebilirsiniz.")
                )
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) requireContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(2, builder.build());
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
                    showNotificationAfterDelay();
                }
            });

        }




        return view;
    }
}