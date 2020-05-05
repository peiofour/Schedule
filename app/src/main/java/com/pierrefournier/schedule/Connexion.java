package com.pierrefournier.schedule;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;
import java.util.Objects;

public class Connexion extends AppCompatActivity implements View.OnClickListener {

    EditText email;
    EditText password;
    Button connectBtn;
    TextView missInfoTxt;
    TextView wrongMailPw;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        email = findViewById(R.id.connectEmail);
        password = findViewById(R.id.connectPassword);
        connectBtn = findViewById(R.id.connectMenuBtn);
        missInfoTxt = findViewById(R.id.connectInfoMissing);
        wrongMailPw = findViewById(R.id.wronMailPw);

        connectBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == connectBtn){

            missInfoTxt.setVisibility(View.GONE);
            wrongMailPw.setVisibility(View.GONE);

            String emailString = String.valueOf(email.getText());
            String pwString = String.valueOf(password.getText());

            //Verify that there are mail and password
            if(!emailString.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\" +
                    ".[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$") ||
                    pwString.equals("")){
                missInfoTxt.setVisibility(View.VISIBLE);
            }

            //If there are mail and password
            else {
                db = FirebaseFirestore.getInstance();

                db.collection("users")
                        .whereEqualTo("email", emailString)
                        .get()
                        .addOnCompleteListener(task -> {
                            if(task.isSuccessful()){

                                if(Objects.requireNonNull(task.getResult()).size() == 0){
                                    wrongMailPw.setVisibility(View.VISIBLE);
                                }
                                else {
                                    Map<String, Object> result = task.getResult().getDocuments().get(0).getData();
                                    String resultPw = (String) Objects.requireNonNull(result).get("password");
                                    if(resultPw.equals(pwString)){
                                        String userID = task.getResult().getDocuments().get(0).getId();
                                        SharedPreferences prefs = getSharedPreferences("user", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = prefs.edit();
                                        editor.putString("userID", userID);
                                        editor.commit();
                                        finish();
                                    }
                                    else{
                                        wrongMailPw.setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                            else {
                                Log.d("TAG error : ", "Error getting documents: ", task.getException());
                            }
                        });
            }

        }
    }
}
