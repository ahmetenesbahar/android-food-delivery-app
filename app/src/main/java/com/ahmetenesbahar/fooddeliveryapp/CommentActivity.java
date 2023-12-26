package com.ahmetenesbahar.fooddeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ahmetenesbahar.fooddeliveryapp.databinding.ActivityCommentBinding;
import com.ahmetenesbahar.fooddeliveryapp.databinding.ActivityMenuBinding;

public class CommentActivity extends AppCompatActivity {
    ActivityCommentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommentActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}