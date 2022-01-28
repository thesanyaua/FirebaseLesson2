package com.example.firebaselesson2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.firebaselesson2.databinding.ActivityMainBinding;
import com.example.firebaselesson2.model.LoginViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private LoginViewModel loginViewModel;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.buttonRegister.setOnClickListener(v -> loginViewModel.goToRegisterActivity(this));

        binding.buttonLogIn.setOnClickListener(v -> {
            loginViewModel.logIn(this, binding.textViewLogin.getText().toString(), binding.textViewPassword.getText().toString(), firebaseAuth);
        });

    }
}