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
import com.example.firebaselesson2.other.Note;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StartViewModel extends AndroidViewModel {
    MutableLiveData<List<Note>> notes = new MutableLiveData<>();
    UserAdapter userAdapter = new UserAdapter();

    public StartViewModel(@NonNull Application application) {
        super(application);
    }

    public void goToAddUserActivity(Context context, String key) {
        Intent intent = new Intent(context, AddUserActivity.class);
        intent.putExtra("key", key);
        context.startActivity(intent);
    }


    public LiveData<List<Note>> getListAllUsers(DatabaseReference db) {

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Note> list = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Note note = dataSnapshot.getValue(Note.class);
                    list.add(note);
                }
                notes.postValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplication().getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return notes;
    }

    public void filterUsers(String string) {
        List<Note> listFilterUsers = new ArrayList<>();
        for (Note note:notes.getValue()) {
            if (note.getHeading().toLowerCase().contains(string.toLowerCase())){
                listFilterUsers.add(note);
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
