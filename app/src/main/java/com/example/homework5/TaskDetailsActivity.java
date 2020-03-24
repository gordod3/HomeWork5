package com.example.homework5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class TaskDetailsActivity extends AppCompatActivity {
    TextView title, description, startDate, deadLine;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(this, "Task not found", Toast.LENGTH_SHORT).show();
            finish();
        } else{
            checkBox = findViewById(R.id.details_checkBox);
            title = findViewById(R.id.details_title);
            description = findViewById(R.id.details_description);
            deadLine = findViewById(R.id.details_deadline);
            startDate = findViewById(R.id.details_start_date);
            Task task = (Task) intent.getSerializableExtra("task");
            title.setText(task.title);
            description.setText(task.description);
            checkBox.setChecked(task.isDone);
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            startDate.setText(format.format(task.startDate));
            deadLine.setText(format.format(task.deadline));
        }
    }

    public void editTask(View view) {
        Intent intent = new Intent(TaskDetailsActivity.this, EditTask.class);
        Task task = (Task) getIntent().getSerializableExtra("task");
        intent.putExtra("task", task);
        startActivityForResult(intent, 2);
    }
}
