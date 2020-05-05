package com.pierrefournier.schedule;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.pierrefournier.schedule.model.Child;

public class CreateChildAccount extends AppCompatActivity implements View.OnClickListener {

    Button createAccountBtn;
    EditText name;
    EditText surname;
    EditText email;
    EditText phone;
    EditText password;
    EditText passwordBis;
    TextView missInfo;
    TextView badMail;
    TextView badPassword;
    TextView badPhone;
    TextView mailUsed;

    Database bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_child_account);

        createAccountBtn = findViewById(R.id.createAccountBtnChild);
        name = findViewById(R.id.nameChild);
        surname = findViewById(R.id.surnameChild);
        email = findViewById(R.id.emailChild);
        phone = findViewById(R.id.phoneChild);
        password = findViewById(R.id.passwordChild);
        passwordBis = findViewById(R.id.passwordBisChild);
        missInfo = findViewById(R.id.missInfoTxtChild);
        badMail = findViewById(R.id.badMailTxtChild);
        badPassword = findViewById(R.id.badPasswordTxtChild);
        badPhone = findViewById(R.id.badPhoneTxtChild);
        mailUsed = findViewById(R.id.mailUsedTxtChild);

        createAccountBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == createAccountBtn){
            missInfo.setVisibility(View.GONE);
            badMail.setVisibility(View.GONE);
            badPassword.setVisibility(View.GONE);
            badPhone.setVisibility(View.GONE);
            mailUsed.setVisibility(View.GONE);

            //Verify if there are all value in cases
            if(String.valueOf(name.getText()).replaceAll(" ", "").equals("") ||
                    String.valueOf(surname.getText()).replaceAll(" ", "").equals("") ||
                    String.valueOf(email.getText()).replaceAll(" ", "").equals("") ||
                    String.valueOf(phone.getText()).replaceAll(" ", "").equals("") ||
                    String.valueOf(password.getText()).replaceAll(" ", "").equals("") ||
                    String.valueOf(passwordBis.getText()).replaceAll(" ", "").equals("")){

                missInfo.setVisibility(View.VISIBLE);
            }
            //Verify that email text matches with email regex
            else if(!String.valueOf(email.getText()).matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\" +
                    ".[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")){
                badMail.setVisibility(View.VISIBLE);
            }

            //verify phone number
            else if(!String.valueOf(phone.getText()).matches("^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$")){
                badPhone.setVisibility(View.VISIBLE);
            }
            //Verify that password and passwordBis matches
            else if(!String.valueOf(password.getText()).equals(String.valueOf(passwordBis.getText()))){
                badPassword.setVisibility(View.VISIBLE);
            }

            //If all ok, create Child account

            else{
                Child child = new Child(String.valueOf(surname.getText()), String.valueOf(name.getText()), String.valueOf(email.getText()),
                        String.valueOf(password.getText()), String.valueOf(phone.getText()));

                bdd = new Database();
                bdd.getUsersCollection()
                        .whereEqualTo("email", child.getEmail())
                        .get()
                        .addOnCompleteListener(task -> {
                            if(task.isSuccessful()){
                                bdd.getUsersCollection()
                                        .add(child)
                                        .addOnSuccessListener(documentReference -> {
                                            Log.d("Success", documentReference.getId());
                                            SharedPreferences prefs = this.getSharedPreferences("user", Context.MODE_PRIVATE);

                                            bdd.getUserRef(prefs.getString("userID", null))
                                                    .update("children", FieldValue.arrayUnion(documentReference));

                                            finish();
                                        });
                            }
                        });

            }
        }
    }
}
