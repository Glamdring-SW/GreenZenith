package com.example.greenzenith;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Marketplace extends Fragment {

    ListView list;
    ArrayList<Plant> plants;
    DatabaseHelper helper;
    public String user;

    public Marketplace(String user){
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_marketplace, container, false);

        list = view.findViewById(R.id.listPlants);
        MainActivity mainActivity = (MainActivity) getActivity();

        helper = new DatabaseHelper(view.getContext());
        plants = helper.getAllPlants();

        if (plants.isEmpty()) {
            Toast.makeText(view.getContext(), "NO HAY PLANTAS!!!", Toast.LENGTH_SHORT).show();
        }

        AdapterMarketplace adapter = new AdapterMarketplace(view.getContext(), plants);
        list.setAdapter(adapter);

        return view;
    }
}