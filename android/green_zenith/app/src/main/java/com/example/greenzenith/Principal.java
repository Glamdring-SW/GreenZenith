package com.example.greenzenith;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Principal extends Fragment {

    ImageView img1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_principal, container, false);
        img1 = (ImageView) view.findViewById(R.id.img1);
        img1.setImageResource(R.drawable.fungi);

        return view;
    }

}