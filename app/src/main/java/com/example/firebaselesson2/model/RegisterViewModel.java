package com.example.firebaselesson2.model;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.firebaselesson2.MainActivity;
import com.example.firebaselesson2.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterViewModel extends AndroidViewModel {
    MutableLiveData<String> infoRegister = new MutableLiveData<>();


    public RegisterViewModel(@NonNull Application application) {
        super(application);
    }

    public void registerNewUser(RegisterActivity registerActivity, String login, String password, String passwordControl, FirebaseAuth firebaseAuth) {
        if(login.isEmpty()||password.isEmpty()){
            infoRegister.postValue("Введите логин или пароль");
        }else if (!password.equals(passwordControl)) {
            infoRegister.postValue("Пароли не совпадают");
        }else {
            firebaseAuth.createUserWithEmailAndPassword(login, password).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    infoRegister.postValue("Регестрация прошла успешно");
                    Intent intent = new Intent(registerActivity, MainActivity.class);
                    registerActivity.startActivity(intent);
                }else {
                    infoRegister.postValue("Данные введены не правельно или такой пользователь существует");
                }
            });
        }
    }

    public LiveData<String> getInfoRegister() {
        return infoRegister;
    }
}
