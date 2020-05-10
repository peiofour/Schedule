package com.pierrefournier.schedule;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.*;

public class CreateTask extends AppCompatActivity implements View.OnClickListener{

    private EditText taskNameEditText;
    private Spinner taskTypeSpinner;
    private TextView dateTextView;
    private TextView startHourTextView;
    private TextView endHourTextView;
    private Spinner remindNbSpinner;
    private Spinner remindIntervalSpinner;
    private EditText commentEditText;
    private TextView warningTextView;
    private Button createTaskButton;

    private String childID;
    private SimpleDateFormat df;


    private String taskType;
    private String date;
    private String hour;
    private String duration;
    private Integer recalls;
    private List<String> recurringDays;
    private String day;
    private String month;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;
    private int currentMonth;
    private int currentYear;
    private int currentDay;
    private int currentHour;
    private int currentMinute;
    private TimePickerDialog timePickerDialog;
    private String minutes;
    private String startHour;
    private String endHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        taskNameEditText = findViewById(R.id.TaskNameCreate);
        taskTypeSpinner = findViewById(R.id.TaskTypeCreate);
        dateTextView = findViewById(R.id.DateTaskCreate);
        startHourTextView = findViewById(R.id.StartHourTaskCreate);
        endHourTextView = findViewById(R.id.EndHourTaskCreate);
        remindNbSpinner = findViewById(R.id.RemindNbSpinner);
        remindIntervalSpinner = findViewById(R.id.RemindIntervalSpinner);
        commentEditText = findViewById(R.id.CommentTaskCreate);
        warningTextView = findViewById(R.id.WarningTaskCreateTextView);
        createTaskButton = findViewById(R.id.CreateTaskBtn);
        createTaskButton.setOnClickListener(this);

        df = new SimpleDateFormat("dd-MM-yyyy");
        dateTextView.setOnClickListener(this);
        startHourTextView.setOnClickListener(this);
        endHourTextView.setOnClickListener(this);

        childID = this.getSharedPreferences("user", Context.MODE_PRIVATE).getString("childID", null);

        List<String> taskTypeList = Arrays.asList("Vie quotidienne", "Scolaire", "Extrascolaire", "Ponctuelle", "Réveil", "Tâche butoire");
        ArrayAdapter<String> taskTypeListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, taskTypeList);
        taskTypeSpinner.setDropDownVerticalOffset(android.R.layout.simple_dropdown_item_1line);
        taskTypeSpinner.setAdapter(taskTypeListAdapter);
        taskTypeSpinner.setSelection(0);

        List<String> remindNbList = Arrays.asList("0","1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        ArrayAdapter<String> remindNbListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, remindNbList);
        remindNbSpinner.setDropDownVerticalOffset(android.R.layout.simple_dropdown_item_1line);
        remindNbSpinner.setAdapter(remindNbListAdapter);
        remindNbSpinner.setSelection(0);

        List<String> remindIntervalList = Arrays.asList("5min", "10min", "15min", "20min", "30min");
        ArrayAdapter<String> remindIntervalListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, remindIntervalList);
        remindIntervalSpinner.setDropDownVerticalOffset(android.R.layout.simple_dropdown_item_1line);
        remindIntervalSpinner.setAdapter(remindIntervalListAdapter);
        remindIntervalSpinner.setSelection(0);

        recurringDays = new ArrayList<String>();

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
        if(v == dateTextView){
            calendar = Calendar.getInstance();
            currentYear = calendar.get(Calendar.YEAR);
            currentMonth = calendar.get(Calendar.MONTH);
            currentDay = calendar.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(this, (datepicker, selectedYear, selectedMonth, selectedDay) -> {
                day = String.valueOf(selectedDay);
                month = String.valueOf(selectedMonth + 1);
                day = day.length() == 1 ? "0" + day : "" + selectedDay;
                month = month.length() == 1 ? "0" + month : "" + selectedMonth;
                dateTextView.setText(day + "/" + month + "/" + selectedYear);

                date = selectedYear + "-" + month + "-" + day;
            }, currentYear, currentMonth, currentDay);
            datePickerDialog.setTitle("Choisir la date");
            datePickerDialog.show();
        }

        if(v == startHourTextView || v == endHourTextView){
            calendar = Calendar.getInstance();
            currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            currentMinute = calendar.get(Calendar.MINUTE);
            timePickerDialog = new TimePickerDialog(CreateTask.this, (view, hourOfDay, minute) -> {
                minutes = String.valueOf(minute).length() == 1 ? "0" + minute : String.valueOf(minute);
                hour = String.valueOf(hourOfDay).length() == 1 ? "0" + hourOfDay : String.valueOf(hourOfDay);

                if(v == startHourTextView){
                    startHourTextView.setText(hour + ":" + minutes);
                    startHour = String.valueOf(startHourTextView.getText());
                }else if(v == endHourTextView){
                    endHourTextView.setText(hour + ":" + minutes);
                    endHour = String.valueOf(endHourTextView.getText());
                }
            }, currentHour, currentMinute, true);
            timePickerDialog.setTitle("Choisir l'heure");
            timePickerDialog.show();
        }

        if(v == createTaskButton){
            warningTextView.setVisibility(View.GONE);

            if(String.valueOf(taskNameEditText.getText()).replaceAll(" ", "").equals("") ||
                    String.valueOf(dateTextView.getText()).replaceAll(" ", "").equals("") ||
                    String.valueOf(startHourTextView.getText()).replaceAll(" ", "").equals("") ||
                    String.valueOf(endHourTextView.getText()).replaceAll(" ", "").equals("")){
                warningTextView.setVisibility(View.VISIBLE);
            }
        }


    }
}
