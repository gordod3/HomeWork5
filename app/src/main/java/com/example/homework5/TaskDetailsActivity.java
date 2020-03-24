package com.example.homework5;

import androidx.annotation.Nullable;
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
    Intent intent;
    Task task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        intent = getIntent();
        if (intent == null) {
            Toast.makeText(this, "Task not found", Toast.LENGTH_SHORT).show();
            finish();
        } else{
            task = (Task) intent.getSerializableExtra("task");
            checkBox = findViewById(R.id.details_checkBox);
            title = findViewById(R.id.details_title);
            description = findViewById(R.id.details_description);
            deadLine = findViewById(R.id.details_deadline);
            startDate = findViewById(R.id.details_start_date);
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
        intent.putExtra("editTask", task);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2){
            if (resultCode == RESULT_OK){
                task = (Task)data.getSerializableExtra("newTask");
            }else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Task edit is canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void saveTask(View view) {
        Intent intent = new Intent();
        intent.putExtra("newTask", task);
        setResult(RESULT_OK, intent);
        finish();
    }
}
