package com.ahmetenesbahar.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView profileImage;
    EditText adress,phone;
    Button updateButton,singOutButton;

    FirebaseAuth auth;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_profile, container, false);
       profileImage= view.findViewById(R.id.imageViewProfileImage);
       adress= view.findViewById(R.id.inputProfileAdress);
       phone= view.findViewById(R.id.inputProfilePhone);
       updateButton= view.findViewById(R.id.buttonUpdateProfile);
         singOutButton= view.findViewById(R.id.signOutButton);
         auth= FirebaseAuth.getInstance();

         singOutButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 auth.signOut();
                 Intent intent = new Intent(getActivity(), MainActivity.class);
                 startActivity(intent);
                 getActivity().finish();

             }
         });


         // getUserData(

       return view;
    }





    /*
    void getUserData(){
        FirebaseUtil.currentUserDetails().get().addOnCompleteLis ... cart curt
    }
     */
}