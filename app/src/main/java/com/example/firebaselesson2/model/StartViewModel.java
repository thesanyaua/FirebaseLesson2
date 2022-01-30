package com.example.firebaselesson2.model;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.firebaselesson2.AddUserActivity;
import com.example.firebaselesson2.adapters.UserAdapter;
import com.example.firebaselesson2.other.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StartViewModel extends AndroidViewModel {
    MutableLiveData<List<User>> users = new MutableLiveData<>();
    UserAdapter userAdapter = new UserAdapter();

    public StartViewModel(@NonNull Application application) {
        super(application);
    }

    public void goToAddUserActivity(Context context, String key) {
        Intent intent = new Intent(context, AddUserActivity.class);
        intent.putExtra("key", key);
        context.startActivity(intent);
    }


    public LiveData<List<User>> getListAllUsers(DatabaseReference db) {

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<User> list = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    list.add(user);
                }
                users.postValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplication().getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return users;
    }

    public void filterUsers(String string) {
        List<User> listFilterUsers = new ArrayList<>();
        for (User user:users.getValue()) {
            if (user.getName().toLowerCase().contains(string.toLowerCase())){
                listFilterUsers.add(user);
            }
            getUserAdapter().setListInAdapter(listFilterUsers);
        }
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    public UserAdapter getUserAdapter() {
        return userAdapter;
    }


}
