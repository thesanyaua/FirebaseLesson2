package com.example.firebaselesson2;

import android.app.Application;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.firebaselesson2.other.Note;
import com.google.firebase.database.DatabaseReference;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AddUserViewModel extends AndroidViewModel {
    MutableLiveData<Integer> color = new MutableLiveData<>();

    public AddUserViewModel(@NonNull Application application) {
        super(application);
    }

    public void addUserInDataBase(DatabaseReference dr, String id, String heading, String text, int color) {
        dr.push().setValue(new Note(id, heading, color, text));
    }


    public ArrayAdapter getArrayAdapter() {
        List<String> color = new ArrayList<>();
        color.add("Красный");
        color.add("Жёлтый");
        color.add("Зелений");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplication().getApplicationContext(), android.R.layout.simple_spinner_item, color);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return arrayAdapter;
    }

    public LiveData<Integer> getColor() {
        return color;
    }
}
