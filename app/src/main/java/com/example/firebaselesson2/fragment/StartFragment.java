package com.example.firebaselesson2.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.firebaselesson2.R;
import com.example.firebaselesson2.databinding.FragmentStartBinding;
import com.example.firebaselesson2.model.StartViewModel;
import com.example.firebaselesson2.other.Note;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

        //Настройка toolBar
        binding.toolBar.inflateMenu(R.menu.menu_start_fragment);

        MenuItem searchItem = binding.toolBar.getMenu().findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        binding.toolBar.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.exit) {
                startViewModel.signOut();
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_startFragment_to_loginFragment);
            }
            return false;
        });

        //Add ListNote
        startViewModel.getListAllUsers(databaseReference).observe(getViewLifecycleOwner(), users -> {
            Collections.reverse(users);
            startViewModel.getUserAdapter().setListInAdapter(users);
            binding.ProgressBar.setVisibility(View.GONE);
            binding.recyclerViewUser.setAdapter(startViewModel.getUserAdapter());
        });
        //Add KEY
        startViewModel.getListKey().observe(getViewLifecycleOwner(), strings -> {
            Collections.reverse(strings);
            startViewModel.getUserAdapter().setKeyPostKeyUserAndActivity(strings, key, getActivity());
        });



        binding.addUser.setOnClickListener(v -> startViewModel.goToAddUserActivity(getContext(), key));

    }

}