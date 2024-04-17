package com.example.phonecall;

import static android.widget.Toast.LENGTH_SHORT;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Controller.CallLogAdapter;
import Controller.CallLogItem;
import Model.Data;

public class RecentsFragment extends Fragment {

    private RecyclerView recyclerView;
    private CallLogAdapter adapter;
    private ImageView logging;
    private SearchView searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recents, container, false);

        // Vérifiez et demandez les autorisations nécessaires pour lire le journal des appels
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.READ_CALL_LOG}, PackageManager.PERMISSION_GRANTED);
        }
        searchView = view.findViewById(R.id.searchView2);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterlist(newText);
                return true;

            }
        });
        logging = view.findViewById(R.id.logging);
        logging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CallLogItem> callLogs = getCallLogs();
                adapter.setCallLogs(callLogs);
            }
        });
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CallLogAdapter();
        recyclerView.setAdapter(adapter);


        return view;
    }



    private List<CallLogItem> getCallLogs() {
        List<CallLogItem> callLogs = new ArrayList<>();
        Uri uriCallLogs = CallLog.Calls.CONTENT_URI;
        String[] projection = {CallLog.Calls.NUMBER, CallLog.Calls.CACHED_NAME, CallLog.Calls.DURATION, CallLog.Calls.DATE};
        Cursor cursorCallLogs = getActivity().getContentResolver().query(uriCallLogs, projection, null, null, CallLog.Calls.DATE + " DESC");
        if (cursorCallLogs != null) {
            while (cursorCallLogs.moveToNext()) {
                @SuppressLint("Range") String stringNumber = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.NUMBER));
                @SuppressLint("Range") String stringName = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.CACHED_NAME));
                @SuppressLint("Range") String stringDuration = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.DURATION));
                @SuppressLint("Range") long callDate = cursorCallLogs.getLong(cursorCallLogs.getColumnIndex(CallLog.Calls.DATE)); // Obtention de la date de l'appel
                CallLogItem callLogItem = new CallLogItem(stringNumber, stringName, stringDuration, callDate);
                callLogs.add(callLogItem);
            }
            cursorCallLogs.close();
        }
        return callLogs;
    }
    private void filterlist(String text) {
        try {
            List<CallLogItem> callLogs = getCallLogs(); // Obtenez la liste des journaux d'appels
            List<CallLogItem> filteredCallLogs = new ArrayList<>(); // Créez une nouvelle liste pour les éléments filtrés
            for (CallLogItem callLog : callLogs) {
                if (callLog.getName() != null && callLog.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredCallLogs.add(callLog); // Ajoutez les éléments correspondants à la liste filtrée
                }
            }
            if (filteredCallLogs.isEmpty()) {
                Toast.makeText(getActivity(), "No matching call logs found", Toast.LENGTH_SHORT).show();
            } else {
                adapter.setCallLogs(filteredCallLogs); // Mettez à jour l'adaptateur avec la liste filtrée
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error filtering call logs", Toast.LENGTH_SHORT).show();
        }
    }


}
