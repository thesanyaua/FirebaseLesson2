package com.example.firebaselesson2.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaselesson2.AddUserActivity;
import com.example.firebaselesson2.R;
import com.example.firebaselesson2.databinding.UserLayoutBinding;
import com.example.firebaselesson2.other.Note;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder> {
    List<Note> list;
    List<String> keyPostList;
    String keyUser;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    Activity activity;
    Context context;


    @NonNull
    @Override
    public UserAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserLayoutBinding binding = UserLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UserAdapterViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterViewHolder holder, int position) {
        databaseReference = firebaseDatabase.getReference(keyUser);
        Note note = list.get(position);
        //key post
        String keyPost = keyPostList.get(position);
        holder.binding.textStartLetter.setText(note.getHeading());
        holder.binding.textViewUserName.setText(note.getHeading());
        holder.binding.textData.setText(note.getTimeCreate());
        holder.binding.textViewtextNote.setText(note.getText());
        colorHead(holder, note.getColor());
        holder.binding.delete.setOnClickListener(v -> {
            getDialog(keyPost).show();
            notifyDataSetChanged();
        });
        holder.binding.cardNote.setOnClickListener(v -> {
            activity.startActivity(startActivityAddUserActivity(activity, keyPost, note.getColor(), note.getHeading(), note.getText()));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setListInAdapter(List<Note> users) {
        list = users;
        notifyDataSetChanged();
    }

    public void setKeyPostKeyUserAndActivity(List<String> listKeyPost, String keyUser, Activity activity) {
        this.keyUser = keyUser;
        this.activity = activity;
        keyPostList = listKeyPost;
    }

    public Dialog getDialog(String keyDeleteNote) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Удаление заметки");
        builder.setMessage("Вы действительно хотите удалить заметку");
        builder.setPositiveButton("Удалить", (dialog, which) -> databaseReference.child(keyDeleteNote).removeValue());
        builder.setNegativeButton("Отмена", (dialog, which) -> dialog.cancel());
        return builder.create();
    }

    public Intent startActivityAddUserActivity(Activity activity, String keyPost, int color, String header, String text) {
        Intent intent = new Intent(activity, AddUserActivity.class);
        intent.putExtra("keyPost", keyPost);
        intent.putExtra("Header", header);
        intent.putExtra("Text", text);
        intent.putExtra("color", color);
        return intent;
    }


    public void colorHead(UserAdapterViewHolder holder, int id) {
        if (id == 0) {
            holder.binding.relativeLayout.setBackgroundResource(R.color.background_red);
        } else if (id == 1) {
            holder.binding.relativeLayout.setBackgroundResource(R.color.background_yellow);
        } else if (id == 2) {
            holder.binding.relativeLayout.setBackgroundResource(R.color.background_green);
        } else if (id == 3) {
            holder.binding.relativeLayout.setBackgroundResource(R.color.background_blue);
        } else if (id == 4) {
            holder.binding.relativeLayout.setBackgroundResource(R.color.background_pink);
        } else if (id == 5) {
            holder.binding.relativeLayout.setBackgroundResource(R.color.background_grey);
        } else if (id == 6) {
            holder.binding.relativeLayout.setBackgroundResource(R.color.background_orange);
        }
    }

    static class UserAdapterViewHolder extends RecyclerView.ViewHolder {
        UserLayoutBinding binding;

        public UserAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
