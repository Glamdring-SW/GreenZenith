package com.example.greenzenith;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

public class Login extends Fragment {

    EditText edit1, edit2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_login, container, false);
        edit1 = (EditText) view.findViewById(R.id.edit1);
        edit2 = (EditText) view.findViewById(R.id.edit2);

        return view;
    }
}