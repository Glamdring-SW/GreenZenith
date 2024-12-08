package com.example.greenzenith;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AlterPlants extends Fragment {

    Button btnExit;
    private String user;
    private Plant plant;

    public AlterPlants(String user, Plant plant){
        this.user = user;
        this.plant = plant;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alter_plants, container, false);

        btnExit = view.findViewById(R.id.btnExit);
        btnExit.setText(plant.getName().toString());
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.setMenuEnabled(true);
                mainActivity.replaceFragment(new PlantsPage(user));

            }
        });

        return view;
    }
}