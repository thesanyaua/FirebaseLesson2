package com.example.firebaselesson2.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NoteInfoViewModel extends AndroidViewModel {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    DatabaseReference databaseReference;

    public NoteInfoViewModel(@NonNull Application application) {
        super(application);
    }


    public void deleteNote(String key){
          if(firebaseAuth.getUid()!=null) {
              databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
              databaseReference.child(key).removeValue();
          }

    }
}
