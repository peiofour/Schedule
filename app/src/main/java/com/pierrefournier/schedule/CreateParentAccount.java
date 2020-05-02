package com.pierrefournier.schedule;

import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pierrefournier.schedule.model.Parent;

import java.util.HashMap;
import java.util.Map;

public class CreateParentAccount extends AppCompatActivity implements View.OnClickListener{

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

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_parent_account);

        createAccountBtn = findViewById(R.id.createAccountBtn);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        passwordBis = findViewById(R.id.passwordBis);
        missInfo = findViewById(R.id.missInfoTxt);
        badMail = findViewById(R.id.badMailTxt);
        badPassword = findViewById(R.id.badPasswordTxt);
        badPhone = findViewById(R.id.badPhoneTxt);

        createAccountBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == createAccountBtn){

            missInfo.setVisibility(View.GONE);
            badMail.setVisibility(View.GONE);
            badPassword.setVisibility(View.GONE);
            badPhone.setVisibility(View.GONE);

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

            //If all is ok, create parent account
            else{

                Parent parent = new Parent(String.valueOf(surname.getText()), String.valueOf(name.getText()), String.valueOf(email.getText()),
                        String.valueOf(password.getText()), String.valueOf(phone.getText()));

                db = FirebaseFirestore.getInstance();

                Map<String, Object> nParent = new HashMap<>();
                nParent.put("firstName", parent.getFirstName());
                nParent.put("lastName", parent.getLastName());
                nParent.put("email", parent.getEmail());
                nParent.put("phone", parent.getPhone());
                nParent.put("password", parent.getPassword());
                nParent.put("isParent", true);

                String TAG = "testFirebasesStore ";

                db.collection("users")
                        .add(parent)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {

                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
            }
        }
    }
}
