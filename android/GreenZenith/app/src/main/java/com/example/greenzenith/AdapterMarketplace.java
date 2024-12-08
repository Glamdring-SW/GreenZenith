package com.example.greenzenith;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterMarketplace extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<Plant> plants;

    public AdapterMarketplace(Context context, ArrayList<Plant> plants) {
        this.context = context;
        this.plants = plants;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return plants.size();
    }

    @Override
    public Object getItem(int i) {
        return plants.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.plantstobuy, viewGroup, false);

            holder = new ViewHolder();

            holder.text1 = view.findViewById(R.id.plantName);
            holder.text2 = view.findViewById(R.id.plantDescription);
            holder.btn1 = view.findViewById(R.id.btnMesagge);
            holder.btn2 = view.findViewById(R.id.btnBuy);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Plant plant = plants.get(i);

        holder.text1.setText(plant.getName());
        holder.text2.setText(plant.getDescription());


        holder.btn1.setOnClickListener(v -> {
            Toast.makeText(context, "Esto lleva a los mensajes" , Toast.LENGTH_SHORT).show();
        });

        holder.btn2.setOnClickListener(v -> {
            Toast.makeText(context, "Esto lleva a la compra" , Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    static class ViewHolder {
        TextView text1;
        TextView text2;
        ImageButton btn1;
        ImageButton btn2;
    }
}
