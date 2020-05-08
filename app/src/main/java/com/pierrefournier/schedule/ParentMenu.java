package com.pierrefournier.schedule;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParentMenu extends AppCompatActivity implements View.OnClickListener {

    private Button addChildAccountBtn;
    private SharedPreferences prefs;

    private RecyclerView childrenRecyclerView;
    private ChildrenListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DocumentReference parentReference;

    private void updateChildrenListAdapter(){
        parentReference.get()
                .addOnCompleteListener(task -> {
                    DocumentSnapshot document = task.getResult();
                    if(document != null && document.exists()){
                        List<DocumentReference> childArray = (List<DocumentReference>) document.get("children");
                        List<String> childrenId = new ArrayList<>();

                        for(DocumentReference child : Objects.requireNonNull(childArray)){
                            childrenId.add(child.getId());
                        }
                        mAdapter = new ChildrenListAdapter(childrenId);
                        childrenRecyclerView.setAdapter(mAdapter);
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_menu);
        addChildAccountBtn = findViewById(R.id.createChildAccountBtn);
        childrenRecyclerView = findViewById(R.id.ChildrenRecyclerView);
        addChildAccountBtn.setOnClickListener(this);

        prefs = getSharedPreferences("user", Context.MODE_PRIVATE);
        Database bdd = new Database();
        parentReference = bdd.getUserRef(prefs.getString("userID", null));

        layoutManager = new LinearLayoutManager(this);
        childrenRecyclerView.setLayoutManager(layoutManager);

        parentReference.addSnapshotListener((snapshot, e) -> {
            if(e != null){
                Log.w("onEvent failed", e);
            }
            if(snapshot != null && snapshot.exists()){
                updateChildrenListAdapter();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == addChildAccountBtn){
            Intent intent = new Intent(this, CreateChildAccount.class);
            startActivity(intent);
        }
    }
}
