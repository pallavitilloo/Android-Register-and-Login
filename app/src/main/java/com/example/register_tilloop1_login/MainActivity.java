package com.example.register_tilloop1_login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/************************************************************************************
 *
 *  The main activity class does the following -
 *  1. Capture on click events for the activity home page
 *  2. If called after registration, hold the user details and pass onto Login
 * @author Pallavi Tilloo
 * https://github.com/pallavitilloo/AndroidRegisterLogin
 *
 *************************************************************************************/

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnRegister, btnLogin;
    String reg_name, reg_fname, reg_email, reg_password;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);

        //If some other activity started this one, Bundle will have the user data
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            reg_name = bundle.getString("firstname");
            reg_fname = bundle.getString("familyname");
            reg_email = bundle.getString("email");
            reg_password = bundle.getString("password");

            tvTitle.setText("Welcome " + reg_name + " " + reg_fname); //To change the welcome screen
        }
        else{
            reg_name = reg_fname = reg_email = reg_password = "";
            tvTitle.setText("Register or Login");
        }

        //Buttons shown on the activity screen will be listened for clicks
        btnRegister = (Button)findViewById(R.id.btnRegister);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //To identify who called this method, we need view.getid
        switch(v.getId()){

            case R.id.btnRegister:
                //If Register is clicked, no data needed to be put into the bundle of the intent
                startActivity(new Intent(this, Register.class));
                break;

            case R.id.btnLogin:

                //If the user wants to login (this might happen after registration, so put the
                //stored details into the bundle and start Login activity

                Intent i = new Intent(this, Login.class);
                Bundle bundle = new Bundle();
                if((!reg_email.isEmpty()) &&
                        (!reg_password.isEmpty())) {
                    bundle.putString("email", reg_email);
                    bundle.putString("password", reg_password);
                    bundle.putString("firstname", reg_name);
                    bundle.putString("familyname", reg_fname);
                    i.putExtras(bundle);
                }
                startActivity(i);
                break;

            default:
                break;
        }
    }
}