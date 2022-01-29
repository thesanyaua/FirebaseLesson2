package com.example.firebaselesson2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.firebaselesson2.databinding.ActivityStartBinding;
import com.example.firebaselesson2.model.StartViewModel;
import com.example.firebaselesson2.other.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class StartActivity extends AppCompatActivity {
    ActivityStartBinding binding;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    StartViewModel startViewModel;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        key = bundle.getString("key");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_start);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(key);

        startViewModel = new ViewModelProvider(this).get(StartViewModel.class);

        binding.addUser.setOnClickListener(v -> startViewModel.goToAddUserActivity(this, key));

        binding.imageExit.setOnClickListener(v -> {
            startViewModel.signOut();
            finish();
        });


        startViewModel.getListAllUsers(databaseReference).observe(this, users -> {
            startViewModel.getUserAdapter().setListInAdapter(users);
            binding.recyclerViewUser.setAdapter(startViewModel.getUserAdapter());
        });


        binding.searchName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                startViewModel.filterUsers(newText);
                return true;
            }
        });
    }
}