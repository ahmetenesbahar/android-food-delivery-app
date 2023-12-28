package com.ahmetenesbahar.fooddeliveryapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetenesbahar.fooddeliveryapp.adapter.RestaurantsAdapter;
import com.ahmetenesbahar.fooddeliveryapp.databinding.ActivityCommentBinding;
import com.ahmetenesbahar.fooddeliveryapp.databinding.ActivityMenuBinding;
import com.ahmetenesbahar.fooddeliveryapp.models.Comment;
import com.ahmetenesbahar.fooddeliveryapp.models.Item;
import com.ahmetenesbahar.fooddeliveryapp.models.Restaurant;
import com.ahmetenesbahar.fooddeliveryapp.models.Users;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class CommentActivity extends AppCompatActivity {
    ActivityCommentBinding binding;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    DatabaseReference databaseReference;
    DatabaseReference restauranteReference;
    Uri imageData;
    ActivityResultLauncher<Intent> activityResultLauncher;
    ActivityResultLauncher<String> permissionLauncher;

    private TextView editTextComment;
    private Button buttonSubmit;
    private RatingBar ratingBar;
    private float selectedRating;
    private String editTextCommentText;
    private String userName;
    private String userId;

    public Restaurant clickedRestaurant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Users");
        restauranteReference = database.getReference("Restaurants");


        registerLauncher();
        setContentView(binding.getRoot());


        editTextComment = binding.editTextComment;
        buttonSubmit = binding.buttonSubmit;
        ratingBar = binding.ratingBar;

        Comment newComment = new Comment();


        //Sayfa açıldığı gibi datayı çek
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("clickedRestaurant")) {
            clickedRestaurant = (Restaurant) intent.getSerializableExtra("clickedRestaurant");

        }

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editTextCommentText = editTextComment.getText().toString();
                Log.v("CommentActivity ", "onClick: yorum  " + editTextCommentText);
                selectedRating = ratingBar.getRating();
                Log.v("CommentActivtiy", "onClick: puan  " + selectedRating);

                FirebaseUser currentUser = auth.getCurrentUser();


                userId = currentUser.getUid();
                databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Check if the user exists in the database
                        if (dataSnapshot.exists()) {
                            Users currentUser = dataSnapshot.getValue(Users.class);

                            userName = (currentUser.getFirstName() + currentUser.getLastName());

                            Comment newComment = new Comment(userId, userName, editTextCommentText, "asd", selectedRating);

                            writeNewComment(newComment, clickedRestaurant);

                            Intent intent = new Intent(CommentActivity.this, MenuActivity.class);
                            startActivity(intent);
                            finish();


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

        });


    }


    public void selectImageClicked(View view) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Snackbar.make(view, "Permission needed for galery", Snackbar.LENGTH_INDEFINITE).setAction("Give permission", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // izin isteme
                        permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE);
                    }
                }).show();
            } else {
                // ask permission
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            }


        } else {
            Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncher.launch(intentToGallery);

        }


    }

    private void registerLauncher() {
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    Intent intentFromResult = result.getData();
                    if (intentFromResult != null) {
                        imageData = intentFromResult.getData();
                        binding.imageView.setImageURI(imageData);
                    }
                }

            }
        });
        permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    // galeriye yönlendirme
                    Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncher.launch(intentToGallery);
                } else {
                    Toast.makeText(CommentActivity.this, "Permission needed please go to settings", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void writeNewComment(Comment newComment, Restaurant clickedRestaurant) {
        // Assuming you have a "commentsList" node under each restaurant to store the list
        DatabaseReference commentsListRef = restauranteReference.child(clickedRestaurant.getRestaurantId()).child("commentsList");

        // Create a new HashMap to store comment data
        HashMap<String, Object> commentData = new HashMap<>();
        commentData.put("userId", newComment.getUserId());
        commentData.put("userName", newComment.getUserName());
        commentData.put("commentText", newComment.getCommentText());
        commentData.put("commentImage", newComment.getCommentImage());
        commentData.put("commentRating", newComment.getCommentRating());

        // Push the comment data to the list
        DatabaseReference newCommentRef = commentsListRef.push();
        String commentId = newCommentRef.getKey();
        commentData.put("commentId", commentId);

        newCommentRef.setValue(commentData);
    }


    public DatabaseReference getDatabaseReference() {
        return databaseReference;


    }
}