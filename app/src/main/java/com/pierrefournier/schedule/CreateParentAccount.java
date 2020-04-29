package com.pierrefournier.schedule;

import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.pierrefournier.schedule.model.Parent;

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

        createAccountBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == createAccountBtn){

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
                System.out.println("TEST 5");
            }
            //Verify that password and passwordBis matches
            else if(!String.valueOf(password.getText()).equals(String.valueOf(passwordBis.getText()))){
                badPassword.setVisibility(View.VISIBLE);
            }

            //If all is ok, create parent account
            else{
                Parent parent = new Parent(String.valueOf(surname.getText()), String.valueOf(name.getText()), String.valueOf(email.getText()),
                        String.valueOf(password.getText()), String.valueOf(phone.getText()));
            }
        }
    }
}
