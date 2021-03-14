package com.example.register_tilloop1_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import static android.text.TextUtils.isEmpty;
import static android.util.Patterns.EMAIL_ADDRESS;

public class Login extends AppCompatActivity{

    Button btnLogin;
    TextInputEditText tbEmail, tbPassword;
    ArrayList<TextInputLayout> loginLayout;
    String reg_name, reg_fname, reg_email, reg_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            reg_name = bundle.getString("name");
            reg_fname = bundle.getString("fname");
            reg_email = bundle.getString("email");
            reg_password = bundle.getString("password");
        }

        loginLayout = new ArrayList<TextInputLayout>();
        loginLayout.add((TextInputLayout)findViewById(R.id.layout1));
        loginLayout.add((TextInputLayout)findViewById(R.id.layout2));

        tbEmail = (TextInputEditText) findViewById(R.id.tbEmail);
        tbPassword = (TextInputEditText)findViewById(R.id.tbPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);
    }

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

    public void login(View view){

        String email, password;
        Toast toast;
        Bundle bundle = new Bundle();
        Intent i = new Intent(this, MainActivity.class);

        if(!IfEmpty(loginLayout)) {
            //Login user if data is valid
            email = tbEmail.getText().toString();
            password = tbPassword.getText().toString();
            if(email.equals(reg_email) && password.equals(reg_password)) {
                toast = Toast.makeText(getApplicationContext(), "Congratulations! You are now logged in!", Toast.LENGTH_LONG);
                bundle.putString("email", reg_email);
                bundle.putString("password", reg_password);
                bundle.putString("name", reg_name);
                bundle.putString("fname", reg_fname);
                i.putExtras(bundle);
            }
            else{
                toast = Toast.makeText( getApplicationContext(),"Sorry! Please register first!", Toast.LENGTH_LONG);
            }
            toast.show();
            startActivity(i);
        }
    }
}