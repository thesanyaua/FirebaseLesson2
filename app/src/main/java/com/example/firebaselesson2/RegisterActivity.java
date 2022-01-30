package com.example.firebaselesson2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.firebaselesson2.databinding.ActivityRegisterBinding;
import com.example.firebaselesson2.model.RegisterViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    FirebaseAuth firebaseAuth;
    RegisterViewModel registerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        
        binding.buttonRegister.setOnClickListener(v -> {
            registerViewModel.registerNewUser(RegisterActivity.this, binding.textViewLogin.getText().toString(),
                    binding.textViewPassword.getText().toString(), binding.textViewPasswordControl.getText().toString(), firebaseAuth);
        });

        registerViewModel.getInfoRegister().observe(this, s -> Snackbar.make(binding.getRoot(), s, Snackbar.LENGTH_SHORT).show());


    }
}