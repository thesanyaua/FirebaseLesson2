package com.example.firebaselesson2.model;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.firebaselesson2.MainActivity;
import com.example.firebaselesson2.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterViewModel extends AndroidViewModel {

    public RegisterViewModel(@NonNull Application application) {
        super(application);
    }

    public void registerNewUser(RegisterActivity registerActivity, String login, String password, FirebaseAuth firebaseAuth) {
        if(login.isEmpty()||password.isEmpty()){
            Toast.makeText(getApplication().getApplicationContext(), "Введите логин или пароль", Toast.LENGTH_SHORT).show();
        }else {
            firebaseAuth.createUserWithEmailAndPassword(login, password).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplication().getApplicationContext(), "Регестрация прошла успешно", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(registerActivity, MainActivity.class);
                    registerActivity.startActivity(intent);
                }else {
                    Toast.makeText(getApplication().getApplicationContext(), "Данные введены не правельно или такой пользователь существует", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
