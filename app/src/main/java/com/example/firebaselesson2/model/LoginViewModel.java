package com.example.firebaselesson2.model;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.firebaselesson2.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends AndroidViewModel {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    MutableLiveData<Boolean> authorization = new MutableLiveData<>();
    MutableLiveData<String> infoLogIn = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }


    public void goToRegisterActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    public LiveData<Boolean> logIn(String login, String password) {
        if (login.isEmpty() || password.isEmpty()) {
            infoLogIn.postValue("Введите логин или пароль");
        } else {
            firebaseAuth.signInWithEmailAndPassword(login, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    authorization.postValue(true);
                } else {
                    authorization.postValue(false);
                    infoLogIn.postValue("Не верный логин или пароль");
                }
            });
        }
        return authorization;
    }

    public LiveData<String> getInfoLogIn() {
        return infoLogIn;
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("key", firebaseAuth.getUid());
        return bundle;
    }

}
