package com.example.greenzenith;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class CreatePlants extends Fragment {

    private ListView listViewDays;
    private DayAdapter dayAdapter;
    private ArrayList<DayItem> dayList;
    private EditText nameEdit, descriptionEdit;
    private Spinner hourSpinner, minuteSpinner;
    private Button btnConfirm;
    DatabaseHelper helper;
    public String user;

    public CreatePlants(String user){
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_plants, container, false);

        listViewDays = view.findViewById(R.id.listViewDays);
        nameEdit = view.findViewById(R.id.name_edit);
        descriptionEdit = view.findViewById(R.id.description_edit);
        hourSpinner = view.findViewById(R.id.hour);
        minuteSpinner = view.findViewById(R.id.minute);
        btnConfirm = view.findViewById(R.id.btnConfirm);

        setupDays();

        dayAdapter = new DayAdapter(getContext(), dayList);
        listViewDays.setAdapter(dayAdapter);

        minuteSpinner.setEnabled(false);

        ArrayList<String> hoursList = new ArrayList<>();
        hoursList.add("HORAS");
        for (int i = 0; i <= 24; i++) {
            hoursList.add(String.valueOf(i));
        }

        ArrayAdapter<String> hoursAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, hoursList);

        hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hourSpinner.setAdapter(hoursAdapter);

        hourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                minuteSpinner.setEnabled(true);

                ArrayList<String> minutesList = new ArrayList<>();
                minutesList.add("MINUTOS");

                for (int i = 0; i < 60; i += 15) {
                    minutesList.add(String.valueOf(i));
                }

                ArrayAdapter<String> minutesAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, minutesList);

                minutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                minuteSpinner.setAdapter(minutesAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                minuteSpinner.setEnabled(false);
            }
        });

        minuteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMinute = minuteSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        btnConfirm.setOnClickListener(v -> {
            try {
                String name = nameEdit.getText().toString();
                String description = descriptionEdit.getText().toString();
                String hour = hourSpinner.getSelectedItem().toString();
                String minute = minuteSpinner.getSelectedItem().toString();

                ArrayList<String>days = new ArrayList<>();
                for (DayItem day : dayList) {
                    if (day.isChecked()) {
                        days.add(day.getDayName());
                    } else {
                        days.add("");
                    }
                }

                if (name.isEmpty() || hour.isEmpty() || minute.isEmpty()) {
                    Toast.makeText(getContext(), "Llena todos los campos requeridos", Toast.LENGTH_SHORT).show();
                    return;
                }

                helper = new DatabaseHelper(v.getContext());

                try {
                    Boolean insertPlant = helper.insertplant(
                            name,
                            description,
                            Integer.parseInt(hour),
                            Integer.parseInt(minute),
                            "se planto un dia",
                            "si",
                            user
                    );

                    if (insertPlant) {
                        Toast.makeText(getContext(), "CREACIÓN EXITOSA", Toast.LENGTH_SHORT).show();
                        nameEdit.setText("");
                        descriptionEdit.setText("");
                    } else {
                        Toast.makeText(getContext(), "Creación Fallida", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Error al insertar notificación: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

                try {
                    Boolean insertDay = helper.insertDay(days, name);
                    if (!insertDay) {
                        Toast.makeText(getContext(), "Error al insertar día:", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Error al insertar días: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            } catch (Exception e) {
                Toast.makeText(getContext(), "Ocurrió un error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });

        return view;
    }

    private void setupDays() {
        dayList = new ArrayList<>();
        dayList.add(new DayItem("Lunes", false));
        dayList.add(new DayItem("Martes", false));
        dayList.add(new DayItem("Miércoles", false));
        dayList.add(new DayItem("Jueves", false));
        dayList.add(new DayItem("Viernes", false));
        dayList.add(new DayItem("Sábado", false));
        dayList.add(new DayItem("Domingo", false));
    }
}
