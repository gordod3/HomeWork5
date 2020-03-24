package com.example.homework5;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainViewHolder extends RecyclerView.ViewHolder {
    TaskClickListener listener;
    TextView number, taskTitle;
    CheckBox checkBox;
    Task task;
    public MainViewHolder(@NonNull View itemView) {
        super(itemView);
        number = itemView.findViewById(R.id.number);
        taskTitle = itemView.findViewById(R.id.task);
        checkBox = itemView.findViewById(R.id.checkBox);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTaskClick(task);//???
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCheckBoxClick(task, checkBox);
            }
        });
    }
}
