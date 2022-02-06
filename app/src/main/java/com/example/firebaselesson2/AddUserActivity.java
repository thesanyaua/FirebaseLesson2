package com.example.firebaselesson2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.firebaselesson2.databinding.ActivityAddUserBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddUserActivity extends AppCompatActivity {
    ActivityAddUserBinding binding;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    int color = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_user);

        binding.textViewHeading.setText(bundle.getString("Header"));
        binding.textViewText.setText(bundle.getString("Text"));

        if (firebaseAuth.getUid() != null) {
            databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        }


        AddUserViewModel addUserViewModel = new ViewModelProvider(this).get(AddUserViewModel.class);
        binding.toolBar.setNavigationOnClickListener(v -> super.onBackPressed());

        binding.spinner.setAdapter(addUserViewModel.getArrayAdapter());
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                addUserViewModel.color.postValue(Integer.parseInt(String.valueOf(id)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addUserViewModel.getColor().observe(this, integer -> {
            color = integer;
            if (integer == 0) {
                binding.colorImage.setBackgroundResource(R.color.background_red);
            } else if (integer == 1) {
                binding.colorImage.setBackgroundResource(R.color.background_yellow);
            } else if (integer == 2) {
                binding.colorImage.setBackgroundResource(R.color.background_green);
            } else if (integer == 3) {
                binding.colorImage.setBackgroundResource(R.color.background_blue);
            } else if (integer == 4) {
                binding.colorImage.setBackgroundResource(R.color.background_pink);
            } else if (integer == 5) {
                binding.colorImage.setBackgroundResource(R.color.background_grey);
            } else if (integer == 6) {
                binding.colorImage.setBackgroundResource(R.color.background_orange);
            }
            });

            binding.buttonAddUser.setOnClickListener(v -> {
                addUserViewModel.addUserInDataBase(databaseReference, bundle.getString("keyPost"), addUserViewModel.getDataCreate(),
                        binding.textViewHeading.getText().toString(), binding.textViewText.getText().toString(), color);
                finish();
            });
        }


    }