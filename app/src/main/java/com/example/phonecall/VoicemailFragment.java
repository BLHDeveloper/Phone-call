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


public class VoicemailFragment extends Fragment {

   private Button Callemail;
    private static final int REQUEST_CALL_PHONE_PERMISSION = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_voicemail, container, false);

        Callemail = view.findViewById(R.id.Callemail);

        Callemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check if CALL_PHONE permission is granted
                if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED) {
                    // Permission is already granted, proceed with call
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:0664189325")));
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
}