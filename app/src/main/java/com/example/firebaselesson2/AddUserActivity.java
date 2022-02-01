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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddUserActivity extends AppCompatActivity {
    ActivityAddUserBinding binding;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    int color = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_user);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(bundle.getString("key"));

        AddUserViewModel addUserViewModel = new ViewModelProvider(this).get(AddUserViewModel.class);
        binding.toolBar.setNavigationIcon(R.drawable.ic_back);
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
                binding.colorImage.setBackgroundResource(R.color.purple_700);
            } else if (integer == 1) {
                binding.colorImage.setBackgroundResource(R.color.yellow_header);
            } else if (integer == 2) {
                binding.colorImage.setBackgroundResource(R.color.teal_700);
            }
        });

        binding.buttonAddUser.setOnClickListener(v -> {
            addUserViewModel.addUserInDataBase(databaseReference, databaseReference.getKey(), binding.textViewHeading.getText().toString(), binding.textViewText.getText().toString(),
                    color);
            finish();
        });
    }


}