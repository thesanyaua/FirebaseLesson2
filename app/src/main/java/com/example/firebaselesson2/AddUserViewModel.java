package com.example.firebaselesson2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.firebaselesson2.other.User;
import com.google.firebase.database.DatabaseReference;

public class AddUserViewModel extends AndroidViewModel {

    public AddUserViewModel(@NonNull Application application) {
        super(application);
    }

    public void addUserInDataBase(DatabaseReference dr, String id, String name, String surname, String phoneNumber) {
        dr.push().setValue(new User(id, name, surname, phoneNumber));
    }
}
