package com.pierrefournier.schedule;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateTask extends AppCompatActivity implements View.OnClickListener{

    private EditText taskNameEditText;
    private Spinner taskTypeSpinner;
    private EditText dateEditText;
    private EditText startHourEditText;
    private EditText endHourEditText;
    private Spinner remindNbSpinner;
    private Spinner remindIntervalSpinner;
    private EditText commentEditText;
    private TextView warningTextView;
    private Button createTaskButton;

    private String childID;

    private String taskType;
    private String date;
    private String hour;
    private String duration;
    private Integer recalls;
    private List<String> recurringDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        taskNameEditText = findViewById(R.id.TaskNameCreate);
        taskTypeSpinner = findViewById(R.id.TaskTypeCreate);
        dateEditText = findViewById(R.id.DateTaskCreate);
        startHourEditText = findViewById(R.id.StartHourTaskCreate);
        endHourEditText = findViewById(R.id.EndHourTaskCreate);
        remindNbSpinner = findViewById(R.id.RemindNbSpinner);
        remindIntervalSpinner = findViewById(R.id.RemindIntervalSpinner);
        commentEditText = findViewById(R.id.CommentTaskCreate);
        warningTextView = findViewById(R.id.WarningTaskCreateTextView);
        createTaskButton = findViewById(R.id.CreateTaskBtn);
        createTaskButton.setOnClickListener(this);

        childID = this.getSharedPreferences("user", Context.MODE_PRIVATE).getString("childID", null);

        List<String> taskTypeList = Arrays.asList("Vie quotidienne", "Scolaire", "Extrascolaire", "Ponctuelle", "Réveil", "Tâche butoire");
        ArrayAdapter<String> taskTypeListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, taskTypeList);
        taskTypeSpinner.setDropDownVerticalOffset(android.R.layout.simple_dropdown_item_1line);
        taskTypeSpinner.setAdapter(taskTypeListAdapter);
        taskTypeSpinner.setSelection(0);

        List<String> remindNbList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        ArrayAdapter<String> remindNbListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, remindNbList);
        remindNbSpinner.setDropDownVerticalOffset(android.R.layout.simple_dropdown_item_1line);
        remindNbSpinner.setAdapter(remindNbListAdapter);

        List<String> remindIntervalList = Arrays.asList("5min", "10min", "15min", "20min", "30min");
        ArrayAdapter<String> remindIntervalListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, remindIntervalList);
        remindIntervalSpinner.setDropDownVerticalOffset(android.R.layout.simple_dropdown_item_1line);
        remindIntervalSpinner.setAdapter(remindIntervalListAdapter);


    }

    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked){
            recurringDays.add(view.getTransitionName());
        } else{
            recurringDays.remove(view.getTransitionName());
        }
    }

    @Override
    public void onClick(View v) {

    }
}
