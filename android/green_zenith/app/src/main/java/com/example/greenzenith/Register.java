package com.example.greenzenith;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Register extends Fragment {

    EditText edit1, edit2, edit3, edit4, edit5;
    Button btn1, btn2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_register, container, false);
        edit1 = (EditText) view.findViewById(R.id.edit1);
        edit2 = (EditText) view.findViewById(R.id.edit2);
        edit3 = (EditText) view.findViewById(R.id.edit3);
        edit4 = (EditText) view.findViewById(R.id.edit4);
        edit5 = (EditText) view.findViewById(R.id.edit5);


        btn1 = (Button) view.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                Principal principal = new Principal();
                transaction.replace(R.id.fragmentContainer, principal);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btn2 = (Button) view.findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                Information information = new Information();
                transaction.replace(R.id.fragmentContainer, information);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity) getActivity()).setMenuEnabled(true);
    }

}