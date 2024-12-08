package com.example.greenzenith;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterPlants extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<Plant> plants;
    DatabaseHelper helper;

    public AdapterPlants(Context context, ArrayList<Plant> plants) {
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
            view = inflater.inflate(R.layout.notification, viewGroup, false);

            holder = new ViewHolder();

            holder.text1 = view.findViewById(R.id.notiNumber);
            holder.text2 = view.findViewById(R.id.notiName);
            holder.text3 = view.findViewById(R.id.notiDescription);
            holder.text4 = view.findViewById(R.id.notiTime);
            holder.text5 = view.findViewById(R.id.notiDays);
            holder.text6 = view.findViewById(R.id.notiPlanting);
            holder.btn2 = view.findViewById(R.id.btn2);
            holder.btn3 = view.findViewById(R.id.btn3);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Plant plant = plants.get(i);

        holder.text1.setText(String.valueOf(i + 1));
        holder.text2.setText(plant.getName());
        holder.text3.setText(plant.getDescription());
        holder.text4.setText(String.valueOf(plant.getHour())+ " horas | " + (String.valueOf(plant.getMinutes())) +"minutos");
        holder.text6.setText(plant.getPlanting());

        helper = new DatabaseHelper(view.getContext());
        String name = plant.getName();

        ArrayList<String> days = helper.getDays(plant.getName());

        StringBuilder builder = new StringBuilder();

        for (String day : days) {
            if (days.isEmpty()) {
                builder.append(" ");
            } else {
                builder.append(day);
                builder.append(" ");
            }
        }

        if (builder.length() > 0) {
            builder.setLength(builder.length() - 2);
        }

        String result = builder.toString();

        holder.text5.setText("Días: " + result);

        holder.btn2.setOnClickListener(v -> {
            if (helper.deleteplant(name)){
                Toast.makeText(context, "notificacion eliminada " , Toast.LENGTH_SHORT).show();
                plants.remove(i);
                notifyDataSetChanged();
            } else {
                Toast.makeText(context, "algo a salido mal",  Toast.LENGTH_SHORT).show();
            }

        });

        holder.btn3.setOnClickListener(v -> {

            MainActivity mainActivity = (MainActivity) context;
            Plant selectedPlant = plants.get(i);
            mainActivity.setMenuEnabled(false);
            mainActivity.replaceFragment(new AlterPlants(mainActivity.getUser(), selectedPlant));

        });

        return view;
    }

    static class ViewHolder {
        TextView text1;
        TextView text2;
        TextView text3;
        TextView text4;
        TextView text5;
        TextView text6;
        Button btn2;
        Button btn3;
    }
}
