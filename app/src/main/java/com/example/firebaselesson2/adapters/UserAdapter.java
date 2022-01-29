package com.example.firebaselesson2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaselesson2.databinding.UserLayoutBinding;
import com.example.firebaselesson2.other.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder> {
    List<User> list;


    @NonNull
    @Override
    public UserAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserLayoutBinding binding = UserLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UserAdapterViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterViewHolder holder, int position) {
        User user = list.get(position);
        holder.binding.textViewUserName.setText(user.getName());
        holder.binding.textViewUserSurname.setText(user.getSurname());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setListInAdapter(List<User> users) {
        list = users;
        notifyDataSetChanged();
    }

    static class UserAdapterViewHolder extends RecyclerView.ViewHolder {
        UserLayoutBinding binding;

        public UserAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
