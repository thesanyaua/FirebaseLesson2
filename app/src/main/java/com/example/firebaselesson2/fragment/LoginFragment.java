package com.example.firebaselesson2.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.firebaselesson2.R;
import com.example.firebaselesson2.databinding.FragmentLoginBinding;
import com.example.firebaselesson2.model.LoginViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        if (firebaseAuth.getUid() != null) {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_startFragment, loginViewModel.getBundle());
        }

        binding.textViewRegister.setOnClickListener(v -> loginViewModel.goToRegisterActivity(getContext()));

        binding.buttonLogIn.setOnClickListener(v -> {
            loginViewModel.logIn(binding.textViewLogin.getText().toString(), binding.textViewPassword.getText().toString()).observe(getViewLifecycleOwner(), aBoolean -> {
                if (aBoolean) {
                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_startFragment, loginViewModel.getBundle());
                }
            });
        });


        loginViewModel.getInfoLogIn().observe(getViewLifecycleOwner(), s -> Snackbar.make(binding.getRoot(), s, Snackbar.LENGTH_SHORT).show());
    }


}