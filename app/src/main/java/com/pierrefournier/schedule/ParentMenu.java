package com.pierrefournier.schedule;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ParentMenu extends AppCompatActivity implements View.OnClickListener {

    Button addChildAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_menu);

        addChildAccountBtn = findViewById(R.id.addChildBtn);

        addChildAccountBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == addChildAccountBtn){
            Intent intent = new Intent(this, CreateChildAccount.class);
            startActivity(intent);
        }
    }
}
