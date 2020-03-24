package com.example.homework5;

import android.widget.CheckBox;

public interface TaskClickListener {
    void onTaskClick(Task task);
    void onCheckBoxClick(Task task, CheckBox checkBox);
}
