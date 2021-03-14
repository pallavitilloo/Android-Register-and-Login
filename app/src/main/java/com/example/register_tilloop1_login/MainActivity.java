package com.example.register_tilloop1_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnRegister, btnLogin;
    String reg_name, reg_fname, reg_email, reg_password, reg_dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            reg_name = bundle.getString("firstname");
            reg_fname = bundle.getString("familyname");
            reg_email = bundle.getString("email");
            reg_password = bundle.getString("password");
            TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
            tvTitle.setText("Welcome " + reg_name + " " + reg_fname);
        }
        else{
            reg_name = reg_fname = reg_email = reg_password = "";
        }

        btnRegister = (Button)findViewById(R.id.btnRegister);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnRegister:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.btnLogin:
                Intent i = new Intent(this, Login.class);
                Bundle bundle = new Bundle();
                if((!reg_email.isEmpty()) &&
                        (!reg_password.isEmpty())) {
                    bundle.putString("email", reg_email);
                    bundle.putString("password", reg_password);
                    bundle.putString("name", reg_name);
                    bundle.putString("fname", reg_fname);
                    i.putExtras(bundle);
                }
                startActivity(i);
                break;
            default:
                break;
        }
    }
}