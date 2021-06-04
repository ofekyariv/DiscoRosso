package com.example.discoproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import static com.example.discoproject.IdentificationActivity.user;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView  pricebag1, pricebag2, pricebag3, pricebag4;
    private Button bag1, bag2, bag3, bag4, back, select;
    private RatingBar bag1_star, bag2_star, bag3_star, bag4_star;
    private Bag bag;
    private BagDataBase bagDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        bag1=findViewById(R.id.btnbag1);
        bag2=findViewById(R.id.btnbag2);
        bag3=findViewById(R.id.btnbag3);
        bag4=findViewById(R.id.btnbag4);
        bag1_star=findViewById(R.id.starBag1);
        bag2_star=findViewById(R.id.starBag2);
        bag3_star=findViewById(R.id.starBag3);
        bag4_star=findViewById(R.id.starBag4);
        pricebag1=findViewById(R.id.bag1Price);
        pricebag2=findViewById(R.id.bag2Price);
        pricebag3=findViewById(R.id.bag3Price);
        pricebag4=findViewById(R.id.bag4Price);
        back=findViewById(R.id.btnback);
        select=findViewById(R.id.btnselect);

        bag1.setOnClickListener(this);
        bag2.setOnClickListener(this);
        bag3.setOnClickListener(this);
        bag4.setOnClickListener(this);
        back.setOnClickListener(this);
        select.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnbag1) {
            bag2.setVisibility(View.INVISIBLE);
            bag3.setVisibility(View.INVISIBLE);
            bag4.setVisibility(View.INVISIBLE);
            bag1_star.setVisibility(View.VISIBLE);
            pricebag1.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
            select.setVisibility(View.VISIBLE);
            bag = new Bag(1, "LOUIS VUITTON",bag1_star.getRating(),50,user);
        }

        if(v.getId()==R.id.btnbag2)
        {
            bag1.setVisibility(View.INVISIBLE);
            bag3.setVisibility(View.INVISIBLE);
            bag4.setVisibility(View.INVISIBLE);
            bag2_star.setVisibility(View.VISIBLE);
            pricebag2.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
            select.setVisibility(View.VISIBLE);
            bag = new Bag(2, "GUCCI",bag2_star.getRating(),150,user);
        }
        if(v.getId()==R.id.btnbag3)
        {
            bag2.setVisibility(View.INVISIBLE);
            bag1.setVisibility(View.INVISIBLE);
            bag4.setVisibility(View.INVISIBLE);
            bag3_star.setVisibility(View.VISIBLE);
            pricebag3.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
            select.setVisibility(View.VISIBLE);
            bag = new Bag(3, "CHANEL",bag3_star.getRating(),300,user);
        }
        if(v.getId()==R.id.btnbag4)
        {
            bag2.setVisibility(View.INVISIBLE);
            bag3.setVisibility(View.INVISIBLE);
            bag1.setVisibility(View.INVISIBLE);
            bag4_star.setVisibility(View.VISIBLE);
            pricebag4.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
            select.setVisibility(View.VISIBLE);
            bag = new Bag(4, "FENDI",bag4_star.getRating(),230,user);
        }



        if(v.getId()==R.id.btnback)
        {
            bag1.setVisibility(View.VISIBLE);
            bag2.setVisibility(View.VISIBLE);
            bag3.setVisibility(View.VISIBLE);
            bag4.setVisibility(View.VISIBLE);
            bag1_star.setVisibility(View.INVISIBLE);
            bag2_star.setVisibility(View.INVISIBLE);
            bag3_star.setVisibility(View.INVISIBLE);
            bag4_star.setVisibility(View.INVISIBLE);
            back.setVisibility(View.INVISIBLE);
            select.setVisibility(View.INVISIBLE);
        }
        if(v.getId()==R.id.btnselect)
        {
            bag.setRating(bag1_star.getRating()+bag2_star.getRating()+bag3_star.getRating()+bag4_star.getRating());
            bagDataBase = new BagDataBase(this);
            bagDataBase.setRecord(bag);
            Intent intent = new Intent(this, FinalListActivity.class);
            startActivity(intent);
            //add the item to the list
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_bag_list, menu);
        getMenuInflater().inflate(R.menu.main_identification, menu);
        getMenuInflater().inflate(R.menu.main_budget, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.list_item)
        {
            Intent intent=new Intent(this, FinalListActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.identification_item)
        {
            Intent intent=new Intent(this, IdentificationActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.budget_item)
        {
            Intent intent=new Intent(this, BudgetActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
