package com.example.firebaselesson2.adapters;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaselesson2.R;
import com.example.firebaselesson2.databinding.UserLayoutBinding;
import com.example.firebaselesson2.other.Note;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder> {
    List<Note> list;


    @NonNull
    @Override
    public UserAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserLayoutBinding binding = UserLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UserAdapterViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterViewHolder holder, int position) {
        Note note = list.get(position);
        holder.binding.textViewUserName.setText(note.getHeading());
        colorHead(holder, note.getColor());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setListInAdapter(List<Note> users) {
        list = users;
        notifyDataSetChanged();
    }

    public void colorHead(UserAdapterViewHolder holder, int id) {
        if(id==0) {
            holder.binding.head.setBackgroundResource(R.color.purple_700);
            holder.binding.relativeLayout.setBackgroundResource(R.color.background_red);
        }else if(id==1) {
            holder.binding.head.setBackgroundResource(R.color.yellow_header);
            holder.binding.relativeLayout.setBackgroundResource(R.color.background_yellow);
        }else if(id==2) {
            holder.binding.head.setBackgroundResource(R.color.teal_700);
            holder.binding.relativeLayout.setBackgroundResource(R.color.background_green);
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
