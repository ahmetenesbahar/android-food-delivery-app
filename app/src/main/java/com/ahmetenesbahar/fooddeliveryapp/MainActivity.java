package com.ahmetenesbahar.fooddeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signInClicked(View view) {
        setContentView(R.layout.activity_main);
    }

    public void signUpClicked(View view) {
        setContentView(R.layout.sign_up);
    }
}