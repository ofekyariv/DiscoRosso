package com.example.discoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IdentificationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_login;
    private Button btn_signup;
    public static String user="";
    EditText userName, password;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);

        btn_login=findViewById(R.id.btnlogin);
        btn_signup=findViewById(R.id.btnsignup);

        userName=findViewById(R.id.etname);
        password=findViewById(R.id.etpassword);

        btn_login.setOnClickListener(this);
        btn_signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnlogin) {
            sharedpreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
            if (sharedpreferences.contains(userName.getText().toString())) {
                if(sharedpreferences.getString(userName.getText().toString(),"")
                        .equals(password.getText().toString())){
                    Toast.makeText(IdentificationActivity.this,"Welcome Back!",Toast.LENGTH_SHORT).show();
                    user = userName.getText().toString();
                    Intent intent = new Intent(this, BudgetActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(IdentificationActivity.this,"Wrong password!",Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(IdentificationActivity.this,"Not registered!",Toast.LENGTH_SHORT).show();
            }
        }
        if(v.getId()==R.id.btnsignup){
            sharedpreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            if (!sharedpreferences.contains(userName.getText().toString())) {
                if(!password.getText().toString().equals("")){
                    Toast.makeText(IdentificationActivity.this,"Welcome!",Toast.LENGTH_SHORT).show();
                    editor.putString(userName.getText().toString(),password.getText().toString());
                    editor.apply();
                    user = userName.getText().toString();
                    Intent intent = new Intent(this, BudgetActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(IdentificationActivity.this,"Enter password!",Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(IdentificationActivity.this,"Already registered!",Toast.LENGTH_SHORT).show();
            }
        }
    }
}