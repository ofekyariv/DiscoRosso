package com.example.discoproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.discoproject.IdentificationActivity.user;

public class BudgetActivity extends AppCompatActivity implements View.OnClickListener {

    private Button  go_btn;
    private EditText etbudget;
    private TextView txtlastbudget;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        txtlastbudget=findViewById(R.id.txtlastbudget);
        go_btn = findViewById(R.id.btnGo);
        etbudget = findViewById(R.id.etbudget);
        sharedpreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        if (sharedpreferences.contains(user+" budget")) {
            txtlastbudget.setText(String.valueOf(sharedpreferences.getInt(user+" budget", 0)));}
        else
            txtlastbudget.setText("No Budget Saved");
        go_btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
         if(v.getId()==R.id.btnGo) {
             SharedPreferences.Editor editor = sharedpreferences.edit();
             if (etbudget.getText().toString().equals(""))
             {
                 Toast.makeText(BudgetActivity.this,"Please enter budget!",Toast.LENGTH_SHORT).show();
             }
             else{
                 try{
                     editor.putInt(user+" budget", Integer.parseInt(etbudget.getText().toString()));
                     editor.apply();
                     Toast.makeText(BudgetActivity.this,"Budget Saved",Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(this,HomePageActivity.class);
                     startActivity(intent);
                     finish();
                 }
                 catch (Exception e){
                     Toast.makeText(BudgetActivity.this,"Invalid budget",Toast.LENGTH_SHORT).show();
                 }
             }
         }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_bag_list, menu);
        getMenuInflater().inflate(R.menu.main_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.list_item)
        {
            Intent intent=new Intent(this, FinalListActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.homepage_item)
        {
            Intent intent=new Intent(this, HomePageActivity.class);
            startActivity(intent);
        }
        return true;
    }
}



