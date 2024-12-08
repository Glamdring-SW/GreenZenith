package com.example.greenzenith;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlantsPage extends Fragment{

    ListView list;
    ArrayList<Plant> plants;
    DatabaseHelper helper;
    public String user;
    Button btn1;

    public PlantsPage(String user) {
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plants_list, container, false);

        list = view.findViewById(R.id.listNoti);
        btn1 = view.findViewById(R.id.addPlant);
        MainActivity mainActivity = (MainActivity) getActivity();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainActivity != null) {
                    mainActivity.setMenuEnabled(false);
                    mainActivity.replaceFragment(new CreatePlants(user));
                } else {
                    Toast.makeText(view.getContext(), "Error: MainActivity no disponible", Toast.LENGTH_SHORT).show();
                }
            }
        });

        helper = new DatabaseHelper(view.getContext());
        plants = helper.getAllPlants(user);

        if (plants.isEmpty()) {
            Toast.makeText(view.getContext(), "No se encontraron plantas para este usuario", Toast.LENGTH_SHORT).show();
        }

        AdapterPlants adapter = new AdapterPlants(view.getContext(), plants);
        list.setAdapter(adapter);

        return view;
    }

}
