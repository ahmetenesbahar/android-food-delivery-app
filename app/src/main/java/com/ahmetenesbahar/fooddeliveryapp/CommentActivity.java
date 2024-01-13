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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Firebase;
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
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class CommentActivity extends AppCompatActivity {
    ActivityCommentBinding binding;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
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
   public  String downloadUrl;


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
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        registerLauncher();
        setContentView(binding.getRoot());

        editTextComment = binding.editTextComment;
        buttonSubmit = binding.buttonSubmit;
        ratingBar = binding.ratingBar;

        Comment newComment = new Comment();

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

                UUID uuid = UUID.randomUUID();
                String commentImage = "commentImages/" + uuid + ".jpg";

                if (imageData != null) {
                    storageReference.child(commentImage).putFile(imageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            StorageReference newReference = firebaseStorage.getReference(commentImage);
                            newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = uri.toString();

                                    databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.exists()) {
                                                Users currentUser = dataSnapshot.getValue(Users.class);
                                                userName = (currentUser.getFirstName() + " " + currentUser.getLastName());

                                                Comment newComment = new Comment(userId, userName, editTextCommentText, downloadUrl, selectedRating);
                                                writeNewComment(newComment, clickedRestaurant);

                                                Intent intent = new Intent(CommentActivity.this, MenuActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            // Handle error
                                        }
                                    });
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CommentActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
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
        DatabaseReference restaurantCommentsRef = restauranteReference.child(clickedRestaurant.getRestaurantId()).child("comments").push();
        String commentId = restaurantCommentsRef.getKey();


        DatabaseReference commentsListRef = restauranteReference.child(clickedRestaurant.getRestaurantId()).child("commentsList");


        // Comment datayı kaydetmek için yeni bir hashmap yarattık
        HashMap<String, Object> commentData = new HashMap<>();
        commentData.put("commentId", commentId);
        commentData.put("userId", newComment.getUserId());
        commentData.put("userName", newComment.getUserName());
        commentData.put("commentText", newComment.getCommentText());
        commentData.put("commentImage", newComment.getCommentImage());
        commentData.put("commentRating", newComment.getCommentRating());

        // Comment datayı listeye pushladık.
        commentsListRef.child(commentId).setValue(commentData);


    }



}