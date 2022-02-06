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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddUserViewModel extends AndroidViewModel {
    MutableLiveData<Integer> color = new MutableLiveData<>();

    public AddUserViewModel(@NonNull Application application) {
        super(application);
    }

    public void addUserInDataBase(DatabaseReference dr, String keyPost, String timeCreate, String heading, String text, int color) {
        if (keyPost == null) {
            dr.push().setValue(new Note(timeCreate, heading, color, text));
        } else {
            dr.child(keyPost).setValue(new Note(timeCreate, heading, color, text));
        }
    }


    public ArrayAdapter getArrayAdapter() {
        List<String> color = new ArrayList<>();
        color.add("Красный");
        color.add("Жёлтый");
        color.add("Зелений");
        color.add("Голубой");
        color.add("Розовий");
        color.add("Серый");
        color.add("Оранжевый");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplication().getApplicationContext(), android.R.layout.simple_spinner_item, color);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return arrayAdapter;
    }

    public String getDataCreate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMM yyyy HH:mm", Locale.getDefault());
        String timeCreate = simpleDateFormat.format(new Date());
        return timeCreate;
    }

    public LiveData<Integer> getColor() {
        return color;
    }
}
