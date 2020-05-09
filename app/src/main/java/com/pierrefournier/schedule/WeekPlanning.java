package com.pierrefournier.schedule;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Objects;

public class WeekPlanning extends AppCompatActivity implements View.OnClickListener{

    private Button previousWeekBtn;
    private Button nextWeekBtn;
    private TextView weekTextView;
    private LinearLayout addTaskLayout;
    private Button addTaskButton;
    private RecyclerView tasksRecyclerView;

    private DocumentReference childReference;
    private Task<DocumentSnapshot> userDocSnapshot;

    private Database bdd;
    private SharedPreferences prefs;
    private String childID;
    private String userID;
    private Boolean isParent = false;

    private String firstDayWeek;
    private String lastDayWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_planning);
        previousWeekBtn = findViewById(R.id.BackWeekBtn);
        nextWeekBtn = findViewById(R.id.NextWeekBtn);
        weekTextView = findViewById(R.id.WeekTextView);
        addTaskLayout = findViewById(R.id.AddTaskLayout);
        addTaskButton = findViewById(R.id.AddTaskButton);

        previousWeekBtn.setOnClickListener(this);
        nextWeekBtn.setOnClickListener(this);
        addTaskButton.setOnClickListener(this);

        bdd = new Database();
        prefs = getSharedPreferences("user", Context.MODE_PRIVATE);
        userID = prefs.getString("userID", null);

        bdd.getUserRef(userID)
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        assert documentSnapshot != null;
                        if(documentSnapshot.exists()){
                            if(documentSnapshot.getBoolean("isParent")){
                                addTaskLayout.setVisibility(View.VISIBLE);
                                addTaskButton.setClickable(true);
                                childID = prefs.getString("childID", null);
                                isParent = true;
                            }
                        }
                    }
                });

    }


    @Override
    public void onClick(View v) {
        if(v == addTaskButton){
            Intent intent = new Intent(this, CreateTask.class);
            startActivity(intent);
        }
    }
}
