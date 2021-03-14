package com.example.register_tilloop1_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Iterator;

import static android.text.TextUtils.isEmpty;

/************************************************************************************
 *
 *  The Login activity gets user email address and password. If the user is already
 *  registered, the details will persist in the bundle passed onto the activity. The
 *  details will be checked with the stored values, and appropriate message will be
 *  shown to user if the details are valid or invalid, as the case may be.
 *
 * @author Pallavi Tilloo
 * https://github.com/pallavitilloo/AndroidRegisterLogin
 *
 *************************************************************************************/


public class Login extends AppCompatActivity{

    Button btnLogin;
    TextInputEditText tbEmail, tbPassword;
    ArrayList<TextInputLayout> loginLayout;
    String reg_name, reg_fname, reg_email, reg_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //If user is registered and then tries to login, Bundle will have the user data
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            reg_name = bundle.getString("firstname");
            reg_fname = bundle.getString("familyname");
            reg_email = bundle.getString("email");
            reg_password = bundle.getString("password");
        }

        loginLayout = new ArrayList<>();
        loginLayout.add((TextInputLayout)findViewById(R.id.layout1));
        loginLayout.add((TextInputLayout)findViewById(R.id.layout2));

        tbEmail = (TextInputEditText) findViewById(R.id.tbEmail);
        tbPassword = (TextInputEditText)findViewById(R.id.tbPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);
    }

    /************************************************************************************
     *
     *  IFEmpty method takes an array list of Text Input Layouts as argument and checks
     *  the fields for empty values.
     *
     *************************************************************************************/
    private boolean IfEmpty(ArrayList<TextInputLayout> layouts){
        Iterator<TextInputLayout> iterator = layouts.iterator();
        String strText;
        boolean isInvalid = false;
        while(iterator.hasNext()){
            TextInputLayout layout = iterator.next();
            strText = layout.getEditText().getText().toString();

            //Check if any field is empty
            if(isEmpty(strText)){
                layout.setErrorEnabled(true);
                layout.setError("Input required!");
                isInvalid = true;
            }
        }
        return isInvalid;
    }

    /************************************************************************************
     *
     *  The method login is called when Login button is clicked. It checks the
     *  values provided if they are not empty, it gets the user details and checks
     *  against the values received from the MainActivity. The values persist only until
     *  the app instance is running.
     *
     *************************************************************************************/
    public void login(View view){

        String email, password;
        Toast toast;
        Bundle bundle = new Bundle();
        Intent i = new Intent(this, MainActivity.class);

        if(!IfEmpty(loginLayout)) {

            //Get the details entered by user
            email = tbEmail.getText().toString();
            password = tbPassword.getText().toString();

            //Check if the entered values match the ones with which the user registered
            if(email.equals(reg_email) && password.equals(reg_password)) {

                //Login user if data is valid
                toast = Toast.makeText(getApplicationContext(), "Congratulations! You are now logged in!", Toast.LENGTH_LONG);

                //Pass the values back to the activity to be started next
                bundle.putString("email", reg_email);
                bundle.putString("password", reg_password);
                bundle.putString("firstname", reg_name);
                bundle.putString("familyname", reg_fname);
                i.putExtras(bundle);
            }
            else{
                //The details don't match
                toast = Toast.makeText( getApplicationContext(),"Sorry! Please register first!", Toast.LENGTH_LONG);
            }
            toast.show();
            startActivity(i);
        }
    }
}