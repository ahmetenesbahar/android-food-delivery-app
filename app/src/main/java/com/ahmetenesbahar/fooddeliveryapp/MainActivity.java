package com.ahmetenesbahar.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ahmetenesbahar.fooddeliveryapp.databinding.ActivityMainBinding;
import com.ahmetenesbahar.fooddeliveryapp.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends FullScreenBaseActivity {

    private ActivityMainBinding binding;

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    DatabaseReference reference;
    UUID uuid = UUID.randomUUID();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        database=FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();


        if (user != null) {
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void signInClicked(View view) {
        setContentView(R.layout.activity_main);
    }

    public void signUpClicked(View view) {
        setContentView(R.layout.sign_up);
    }

    public void signUpButtonClicked(View view) {
        EditText inputEmail = findViewById(R.id.inputEmail);
        String email = inputEmail.getText().toString().trim();
        EditText inputPassword = findViewById(R.id.inputPassword);
        String password = inputPassword.getText().toString().trim();
        EditText inputPhoneNumber = findViewById(R.id.inputPhoneNumber);
        String phoneNumber = inputPhoneNumber.getText().toString().trim();
        EditText inputAdress = findViewById(R.id.inputAdress);
        String adress = inputAdress.getText().toString().trim();
        EditText inputLastName = findViewById(R.id.inputLastName);
        String lastName = inputLastName.getText().toString().trim();
        EditText inputFirstName = findViewById(R.id.inputFirstName);
        String firstName = inputFirstName.getText().toString().trim();
        String userId = uuid.toString();

        Log.d("SignUp", "Email: " + email);
        Log.d("SignUp", "Password: " + password);
        Log.d("SignUp", "PhoneNumber: " + phoneNumber);
        Log.d("SignUp", "Adress: " + adress);
        Log.d("SignUp", "LastName: " + lastName);
        Log.d("SignUp", "FirstName: " + firstName);

        if (email.length()==0 || password.length()==0 || phoneNumber.length()==0 || adress.length()==0 || lastName.length()==0 || firstName.length()==0) {
            Toast.makeText(this, "Please fill in all the required fields.", Toast.LENGTH_LONG).show();


        } else {

            Users user = new Users(firstName, lastName, phoneNumber, adress, email, password);
            reference = database.getReference("Users");
            reference.child(userId).setValue(user);

            reference.child(userId).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    auth.createUserWithEmailAndPassword(email, password);
                    Toast.makeText(MainActivity.this, "User created successfully.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void signInButtonClicked(View view) {
        String email = binding.inputEmail.getText().toString();
        String password = binding.inputPassword.getText().toString();

        if (email.matches("") || password.matches("")) {
            Toast.makeText(this, "Email and password are required!", Toast.LENGTH_LONG).show();
        } else {
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}