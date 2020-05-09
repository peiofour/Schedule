package com.pierrefournier.schedule;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

public class WeekPlanning extends AppCompatActivity {

    private Button previousWeekBtn;
    private Button nextWeekBtn;
    private TextView weekTextView;
    private LinearLayout addTaskLayout;
    private Button addTaskButton;
    private RecyclerView tasksRecyclerView;

    private DocumentReference childReference;

    private Database bdd;
    private SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_planning);
        previousWeekBtn = findViewById(R.id.BackWeekBtn);
        nextWeekBtn = findViewById(R.id.NextWeekBtn);
        weekTextView = findViewById(R.id.WeekTextView);
        addTaskLayout = findViewById(R.id.AddTaskLayout);
        addTaskButton = findViewById(R.id.AddTaskButton);

        bdd = new Database();
        prefs = getSharedPreferences("user", Context.MODE_PRIVATE);

        bdd.getUserRef(prefs.getString("userID", null))
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        assert documentSnapshot != null;
                        if(documentSnapshot.exists()){
                            if(documentSnapshot.getBoolean("isParent")){
                                addTaskLayout.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
    }
}
