package com.example.discoproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.discoproject.IdentificationActivity.user;

public class FinalListActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    private ListView listViewDR;
    private TextView txtbudgettrack;
    private ArrayList<Bag> bags;
    private BagAdapter adapter;
    private BagDataBase bagDataBase;
    SharedPreferences sharedpreferences;
    int budget=0;
    int sum =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_list);
        txtbudgettrack=findViewById(R.id.txtbudgettrack);
        listViewDR = findViewById(R.id.listViewDR);

        bagDataBase = new BagDataBase(this);

        bags = bagDataBase.getAllRecords();

        adapter = new BagAdapter(this, bags);

        listViewDR.setAdapter(adapter);

        listViewDR.setOnItemLongClickListener(this);

        sharedpreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);

        budget = sharedpreferences.getInt(user + " budget", 0);


        for (Bag bag:bags) {
            sum+=bag.getPrice();
        }
        if(sum<budget){
            txtbudgettrack.setText("you are in your budget!");
            txtbudgettrack.setTextColor(Color.GREEN);
        }
        else{
            txtbudgettrack.setText("you are NOT in your budget!");
            txtbudgettrack.setTextColor(Color.RED);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_budget,menu);
        getMenuInflater().inflate(R.menu.main_identification,menu);
        getMenuInflater().inflate(R.menu.main_home_page,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.budget_item)
        {
            Intent intent=new Intent(this, BudgetActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.identification_item)
        {
            Intent intent=new Intent(this, IdentificationActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.homepage_item)
        {
            Intent intent=new Intent(this, HomePageActivity.class);
            startActivity(intent);
        }
        return true;
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("delete bag");
        alert.setMessage("are you sure you want to delete the bag?");
        alert.setCancelable(false);

        alert.setPositiveButton("delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                //do something
                bagDataBase.deleteItemByRow(bags.get(position).getId());
                BagDataBase.getBags().remove(position);
                adapter.notifyDataSetChanged();
                dialogInterface.dismiss();
                sum=0;
                for (Bag bag:bags) {
                    sum+=bag.getPrice();
                }
                if(sum<budget){
                    txtbudgettrack.setText("you are in your budget!");
                    txtbudgettrack.setTextColor(Color.GREEN);
                }
                else{
                    txtbudgettrack.setText("you are NOT in your budget!");
                    txtbudgettrack.setTextColor(Color.RED);
                }
            }
        });

        alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.create();
        alert.show();
        adapter.notifyDataSetChanged();

        return true;
    }
}