package com.pierrefournier.schedule;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Connexion extends AppCompatActivity implements View.OnClickListener {

    EditText email;
    EditText password;
    Button connectBtn;
    TextView missInfoTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        email = findViewById(R.id.connectEmail);
        password = findViewById(R.id.connectPassword);
        connectBtn = findViewById(R.id.connectMenuBtn);
        missInfoTxt = findViewById(R.id.connectInfoMissing);

        connectBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == connectBtn){

            missInfoTxt.setVisibility(View.GONE);

            //Verify that there are mail and password
            if(String.valueOf(email.getText()).replaceAll(" ", "").equals("") ||
                    String.valueOf(password.getText()).replaceAll(" ", "").equals("")){
                missInfoTxt.setVisibility(View.VISIBLE);
            }

        }
    }
}
