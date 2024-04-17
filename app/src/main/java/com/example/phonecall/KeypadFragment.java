package com.example.phonecall;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import Controller.DatabaseHelper;
import Model.Data;
import Views.AddData;
import Views.EditData;

public class KeypadFragment extends Fragment {

    private Button add;
    private DatabaseHelper databaseHelper;
    private static final int REQUEST_CALL_PHONE_PERMISSION = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_keypad, container, false);

        add = view.findViewById(R.id.add);

        Button num0 = view.findViewById(R.id.num0);
        Button num1 = view.findViewById(R.id.num1);
        Button num2 = view.findViewById(R.id.num2);
        Button num3 = view.findViewById(R.id.num3);
        Button num4 = view.findViewById(R.id.num4);
        Button num5 = view.findViewById(R.id.num5);
        Button num6 = view.findViewById(R.id.num6);
        Button num7 = view.findViewById(R.id.num7);
        Button num8 = view.findViewById(R.id.num8);
        Button num9 = view.findViewById(R.id.num9);
        Button star = view.findViewById(R.id.star);
        Button hash = view.findViewById(R.id.hash);
        ImageButton appell = view.findViewById(R.id.appell);
        ImageButton back = view.findViewById(R.id.back);
        TextView screen = view.findViewById(R.id.screen);



        databaseHelper = new DatabaseHelper(getActivity());






        updateAddButtonVisibility(screen.getText().toString(), add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = screen.getText().toString();
                long id = databaseHelper.insertData(new Data(phone));
              //Intent intent = new Intent(getActivity(), EditData.class);
                Intent intent = new Intent(getActivity(), AddData.class);
                startActivity(intent);

            }
        });



        num0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText(screen.getText().toString()+"0");
                updateAddButtonVisibility(screen.getText().toString(), add);
            }
        });
        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText(screen.getText().toString()+"1");
                updateAddButtonVisibility(screen.getText().toString(), add);
            }
        });
        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText(screen.getText().toString()+"2");
                updateAddButtonVisibility(screen.getText().toString(), add);
            }
        });
        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText(screen.getText().toString()+"3");
                updateAddButtonVisibility(screen.getText().toString(), add);
            }
        });
        num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText(screen.getText().toString()+"4");
                updateAddButtonVisibility(screen.getText().toString(), add);
            }
        });
        num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText(screen.getText().toString()+"5");
                updateAddButtonVisibility(screen.getText().toString(), add);
            }
        });
        num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText(screen.getText().toString()+"6");
                updateAddButtonVisibility(screen.getText().toString(), add);
            }
        });
        num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText(screen.getText().toString()+"7");
                updateAddButtonVisibility(screen.getText().toString(), add);
            }
        });
        num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText(screen.getText().toString()+"8");
                updateAddButtonVisibility(screen.getText().toString(), add);
            }
        });
        num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText(screen.getText().toString()+"9");
                updateAddButtonVisibility(screen.getText().toString(), add);
            }
        });
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText(screen.getText().toString()+"*");
                updateAddButtonVisibility(screen.getText().toString(), add);
            }
        });
        hash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText(screen.getText().toString()+"#");
                updateAddButtonVisibility(screen.getText().toString(), add);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder stringBuilder = new StringBuilder(screen.getText());
                if (screen.getText().length()>=1){
                    stringBuilder.deleteCharAt(screen.getText().length()-1);
                }
                String newstring = stringBuilder.toString();
                screen.setText(newstring);
                updateAddButtonVisibility(screen.getText().toString(), add);
            }
        });

        appell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = screen.getText().toString();
                String dial = "tel:" + number;

                // Check if CALL_PHONE permission is granted
                if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED) {
                    // Permission is already granted, proceed with call
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                } else {
                    // Request the permission
                    ActivityCompat.requestPermissions(requireActivity(),
                            new String[]{android.Manifest.permission.CALL_PHONE},
                            REQUEST_CALL_PHONE_PERMISSION);
                }
            }
        });

        return view;
    }
    private void updateAddButtonVisibility(String text, Button addButton) {
        if (text.length() >= 1) {
            addButton.setVisibility(View.VISIBLE);
        } else {
            addButton.setVisibility(View.GONE);
        }
    }


}