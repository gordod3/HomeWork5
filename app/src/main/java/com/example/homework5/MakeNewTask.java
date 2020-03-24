package com.example.homework5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MakeNewTask extends AppCompatActivity {
    Button cancel, save, choseDateDeadline;
    EditText title, description;
    TextView deadLineText;
    Date taskDeadline;
    Calendar calendar = Calendar.getInstance();
    DatePickerDialog picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_new_task);
        cancel = findViewById(R.id.task_cancel);
        save = findViewById(R.id.task_save);
        choseDateDeadline = findViewById(R.id.task_choseDate);
        deadLineText = findViewById(R.id.task_deadLineText);
        title = findViewById(R.id.task_title);
        description = findViewById(R.id.task_description);
    }

    public void taskAction(View view) {
        switch (view.getId()){
            case R.id.task_save:
                if (title.getText().toString().equals("")){
                    Toast.makeText(this, "Enter task title", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (description.getText().toString().equals("")){
                    Toast.makeText(this, "Enter task description", Toast.LENGTH_SHORT).show();
                    return;
                }
                Task task = new Task();
                task.deadline = taskDeadline;
                task.description = description.getText().toString();
                task.title = title.getText().toString();
                task.startDate = new Date();
                Intent intent = new Intent();
                intent.putExtra("task", task);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.task_choseDate:
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        deadLineText.setText(dayOfMonth + "." + (month + 1) + "." + year);
                        taskDeadline = new GregorianCalendar(year, month, dayOfMonth).getTime();
                    }
                }, year, month, day);
                picker.show();
                break;
            case R.id.task_cancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
