package com.example.firebaselesson2.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firebaselesson2.R;
import com.example.firebaselesson2.databinding.FragmentStartBinding;
import com.example.firebaselesson2.model.StartViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StartFragment extends Fragment {
    FragmentStartBinding binding;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    StartViewModel startViewModel;
    String key;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (getArguments() != null) {
            key = getArguments().getString("key");
        }
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(key);
        startViewModel = new ViewModelProvider(this).get(StartViewModel.class);

        startViewModel.getListAllUsers(databaseReference).observe(getViewLifecycleOwner(), users -> {
            startViewModel.getUserAdapter().setListInAdapter(users);
            binding.recyclerViewUser.setAdapter(startViewModel.getUserAdapter());
        });

        binding.addUser.setOnClickListener(v -> startViewModel.goToAddUserActivity(getContext(), key));

        binding.imageExit.setOnClickListener(v -> {
            startViewModel.signOut();
            Navigation.findNavController(v).navigate(R.id.action_startFragment_to_loginFragment);
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