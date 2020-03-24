package com.example.homework5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TaskClickListener {
    RecyclerView recyclerView;
    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        adapter = new MainAdapter();
        adapter.listener = this;
        recyclerView.setAdapter(adapter);
    }

    public void makeNewTask(View view) {
        Intent intent = new Intent(MainActivity.this, MakeNewTask.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK) {
                Task task = (Task) data.getSerializableExtra("task");
                adapter.addTask(task);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Task creation canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onTaskClick(Task task) {
        Intent intent = new Intent(MainActivity.this, TaskDetailsActivity.class);
        intent.putExtra("task", task);
        startActivity(intent);
    }

    @Override
    public void onCheckBoxClick(Task task, CheckBox checkBox) {
        task.isDone = checkBox.isChecked();
    }
}
