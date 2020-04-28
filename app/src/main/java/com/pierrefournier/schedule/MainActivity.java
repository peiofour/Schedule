package com.pierrefournier.schedule;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button connexionBtn;
    Button createAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connexionBtn = findViewById(R.id.connectBtn);
        createAccountBtn = findViewById(R.id.createAccountBtn);

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
}
