package com.example.firebaselesson2.model;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.firebaselesson2.MainActivity;
import com.example.firebaselesson2.RegisterActivity;
import com.example.firebaselesson2.StartActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends AndroidViewModel {

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void goToRegisterActivity(MainActivity activity) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }

    public void logIn(MainActivity activity, String login, String password, FirebaseAuth firebaseAuth) {
        if (login.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplication().getApplicationContext(), "Введите логин или пароль", Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuth.signInWithEmailAndPassword(login, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(activity, StartActivity.class);
                    intent.putExtra("key", firebaseAuth.getUid());
                    activity.startActivity(intent);
                } else {
                    Toast.makeText(getApplication().getApplicationContext(), "Не верный логин или пароль", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void userKeyVerification(MainActivity mainActivity, FirebaseAuth firebaseAuth) {
        if (firebaseAuth.getUid()!=null) {
            Intent intent = new Intent(mainActivity, StartActivity.class);
            intent.putExtra("key", firebaseAuth.getUid());
            mainActivity.startActivity(intent);

        }
    }
}
