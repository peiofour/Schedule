package com.pierrefournier.schedule;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ParentMenu extends AppCompatActivity implements View.OnClickListener {

    private Button addChildAccountBtn;
    private SharedPreferences prefs;
    private Database bdd;

    private RecyclerView childrenRecyclerView;
    private ChildrenListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<String> childrenId = new ArrayList<String>();

    private void getChildrenId(){
        bdd = new Database();
        prefs = getSharedPreferences("user", Context.MODE_PRIVATE);
        bdd.getUserRef(prefs.getString("userID", null))
                .get()
                .addOnCompleteListener(task -> {
                    DocumentSnapshot document = task.getResult();
                    if(document != null && document.exists()){
                        ArrayList<DocumentReference> childArray = (ArrayList<DocumentReference>) document.get("children");
                        for(DocumentReference child : childArray){
                            childrenId.add(child.getId());
                            Log.d("Child ID", child.getId());
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_menu);
        addChildAccountBtn = findViewById(R.id.createChildAccountBtn);
        addChildAccountBtn.setOnClickListener(this);

        childrenRecyclerView = findViewById(R.id.ChildrenRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        childrenRecyclerView.setLayoutManager(layoutManager);

        getChildrenId();

        mAdapter = new ChildrenListAdapter(childrenId);
        childrenRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        if(v == addChildAccountBtn){
            Intent intent = new Intent(this, CreateChildAccount.class);
            startActivity(intent);
        }
    }
}
