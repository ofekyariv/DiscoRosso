package com.example.discoproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BagAdapter extends ArrayAdapter<Bag> {

    private Context context;
    private ArrayList<Bag>list;

    public BagAdapter(@NonNull Context context, ArrayList<Bag>list) {
        super(context, R.layout.baglist ,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView= inflater.inflate(R.layout.baglist,parent,false);

        TextView tvName = rowView.findViewById(R.id.tvName);
        tvName.setText(list.get(position).getName());

        TextView tvRating = rowView.findViewById(R.id.tvRating);
        tvRating.setText(String.valueOf(list.get(position).getRating()));

        TextView tvPrice = rowView.findViewById(R.id.tvPrice);
        tvPrice.setText(String.valueOf(list.get(position).getPrice()));

        //pic
        ImageView imageView=rowView.findViewById(R.id.imgBag);
        if (list.get(position).getName().equalsIgnoreCase("LOUIS VUITTON"))
        {imageView.setImageResource(R.drawable.bag1);}
        if (list.get(position).getName().equalsIgnoreCase("GUCCI"))
        {imageView.setImageResource(R.drawable.bag2);}
        if (list.get(position).getName().equalsIgnoreCase("CHANEL"))
        {imageView.setImageResource(R.drawable.bag3);}
        if (list.get(position).getName().equalsIgnoreCase("FENDI"))
        {imageView.setImageResource(R.drawable.bag4);}


        return rowView;
    }
}