package com.example.homework5;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {
    ArrayList<Task> data = new ArrayList<>();
    TaskClickListener listener;

    public void addTask(Task task) {
        data.add(task);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.main_view_holder, parent, false);
        MainViewHolder newViewHolder = new MainViewHolder(view);//???
        newViewHolder.listener = listener;
        return newViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.checkBox.setChecked(data.get(position).isDone);
        holder.task = data.get(position);
        holder.number.setText("" + (position+1));
        holder.taskTitle.setText(data.get(position).title);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
