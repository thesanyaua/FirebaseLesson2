package com.example.firebaselesson2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.firebaselesson2.databinding.ActivityAddUserBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddUserActivity extends AppCompatActivity {
    ActivityAddUserBinding binding;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_user);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(bundle.getString("key"));

        AddUserViewModel addUserViewModel = new ViewModelProvider(this).get(AddUserViewModel.class);


        binding.buttonAddUser.setOnClickListener(v -> {
            addUserViewModel.addUserInDataBase(databaseReference, databaseReference.getKey(), binding.textViewName.getText().toString(), binding.textViewSurname.getText().toString(),
                    binding.textViewPhoneNumber.getText().toString());
            finish();
        });


    }


}