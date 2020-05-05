package com.pierrefournier.schedule;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button connexionBtn;
    Button createAccountBtn;
    Database bdd;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connexionBtn = findViewById(R.id.connectMenuBtn);
        createAccountBtn = findViewById(R.id.createAccountMenuBtn);

        connexionBtn.setOnClickListener(this);
        createAccountBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == connexionBtn){
            Intent intent = new Intent(this, Connexion.class);
            startActivity(intent);
        }

        if(v == createAccountBtn){
            Intent intent = new Intent(this, CreateParentAccount.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = this.getSharedPreferences("user", Context.MODE_PRIVATE);

        if(prefs.contains("userID")){
            userID = prefs.getString("userID", null);
            bdd = new Database();
            bdd.getUserRef(userID).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    DocumentSnapshot doc = task.getResult();
                    if(Objects.requireNonNull(doc).exists()){
                        if(Objects.requireNonNull(doc.getBoolean("isParent"))){
                            Log.d("Parent", "c'est good");
                            Intent intent = new Intent(getApplicationContext(), ParentMenu.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                            startActivity(intent);
                            finish();
                        } else if(!Objects.requireNonNull(doc.getBoolean("isParent"))){
                            Log.d("Kid", "c'est good");
                            Intent intent = new Intent(getApplicationContext(), ChildMenu.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Log.e("Error", "No isParent boolean");
                        }
                    }
                } else {
                    Log.d("error", "get failed with ", task.getException());
                }
            });
        }
    }
}
