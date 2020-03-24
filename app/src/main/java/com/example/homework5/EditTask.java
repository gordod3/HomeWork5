package com.example.homework5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EditTask extends AppCompatActivity {
    EditText title, description;
    Date taskDeadline, taskStartDate;
    TextView startDate, deadlineDate;
    Calendar calendar = Calendar.getInstance();
    DatePickerDialog picker;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        checkBox = findViewById(R.id.edit_checkBox);
        title = findViewById(R.id.edit_title);
        description = findViewById(R.id.edit_description);
        deadlineDate = findViewById(R.id.edit_deadLineText);
        startDate = findViewById(R.id.edit_startDateText);
        Task task = (Task) getIntent().getSerializableExtra("editTask");
        checkBox.setChecked(task.isDone);
        title.setText(task.title);
        description.setText(task.description);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        startDate.setText(format.format(task.startDate));
        deadlineDate.setText(format.format(task.deadline));
    }

    public void EditAction(View view) {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        switch (view.getId()){
            case R.id.edit_choseStartDate:
                picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        startDate.setText(dayOfMonth + "." + (month + 1) + "." + year);
                        taskStartDate = new GregorianCalendar(year, month, dayOfMonth).getTime();
                    }
                }, year, month, day);
                picker.show();
                break;
                case R.id.edit_choseDateDeadline:
                    picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            deadlineDate.setText(dayOfMonth + "." + (month + 1) + "." + year);
                            taskDeadline = new GregorianCalendar(year, month, dayOfMonth).getTime();
                        }
                    }, year, month, day);
                    picker.show();
                break;
                case R.id.edit_save:
                    if (title.getText().toString().equals("")){
                        Toast.makeText(this, "Edit task title", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (description.getText().toString().equals("")){
                        Toast.makeText(this, "Edit task description", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent();
                    Task task = (Task) getIntent().getSerializableExtra("editTask");
                    task.isDone = checkBox.isChecked();
                    task.title = title.getText().toString();
                    task.description = description.getText().toString();
                    task.startDate = taskStartDate;
                    task.deadline = taskDeadline;
                    intent.putExtra("NewTask", task);
                    setResult(RESULT_OK, intent);
                    finish();
                break;
                case R.id.edit_cancel:
                    setResult(RESULT_CANCELED);
                    finish();
                break;
        }
    }
}
