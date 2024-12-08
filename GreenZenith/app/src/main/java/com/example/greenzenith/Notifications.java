package com.example.greenzenith;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Notifications extends Fragment{

    ListView list;
    ArrayList<Plant> plants;
    DatabaseHelper helper;
    public String user;

    public Notifications(String user){
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        list = view.findViewById(R.id.listNoti);

        helper = new DatabaseHelper(view.getContext());
        plants = helper.getAllPlants(user);

        int idk = plants.size();

        Toast.makeText(view.getContext(), "hay plantas" + idk, Toast.LENGTH_LONG).show();

        AdapterPlants adapter = new AdapterPlants(view.getContext(), plants);

        list.setAdapter(adapter);

        return view;
    }

}