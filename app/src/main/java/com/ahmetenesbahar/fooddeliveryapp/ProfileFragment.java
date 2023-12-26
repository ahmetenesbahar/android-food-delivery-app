package com.ahmetenesbahar.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {
    ImageView profileImage;
    EditText adress, phone;
    Button updateButton, singOutButton;

    FirebaseAuth auth;
    FirebaseDatabase database;

    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Users");

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        profileImage = view.findViewById(R.id.imageViewProfileImage);
        adress = view.findViewById(R.id.inputProfileAdress);
        phone = view.findViewById(R.id.inputProfilePhone);
        updateButton = view.findViewById(R.id.buttonUpdateProfile);
        singOutButton = view.findViewById(R.id.signOutButton);


        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        String uid = currentUser.getUid();
        DatabaseReference userRef = databaseRef.child(uid);


        singOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

        //Buradaki işlem üzerine istenirse tüm bilgileri güncelleyebiliriz :).

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String adressText = adress.getText().toString();
                        String phoneText = phone.getText().toString();

                        dataSnapshot.getRef().child("adress").setValue(adressText);
                        dataSnapshot.getRef().child("phoneNumber").setValue(phoneText);

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


        // getUserData(

        return view;
    }


}