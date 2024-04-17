package com.example.phonecall;

import static android.widget.Toast.LENGTH_SHORT;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import Controller.Adapter;
import Controller.DatabaseHelper;
import Model.Data;
import Views.AddData;

public class ContactsFragment extends Fragment {
    private RecyclerView recyclerView;
    private static Adapter adapter;
    private DatabaseHelper databaseHelper;

    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);



        searchView = view.findViewById(R.id.searchView);
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
        recyclerView = view.findViewById(R.id.RecyclerviewID);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        databaseHelper = new DatabaseHelper(getActivity());

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddData.class);
                startActivity(intent);

            }
        });
        adapter = new Adapter(getActivity(),databaseHelper.getAllData(),databaseHelper);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Intent intent = new Intent(getActivity(), AfficheData.class);
//                startActivity(intent);


            }
        });

        List<Data> mydata = databaseHelper.getAllData();
        for(Data data : mydata){
            String myInfo = " Firstname: "+data.getFirstname()+" Lastname : "+data.getLastname()
                    + " Company: "+data.getCompany() + " Phone: "+data.getPhone() + " Email: "+data.getEmail()+" Notes: "+ data.getNotes();
            Log.d("data", myInfo);
        }
        return view;

    }

    private void filterlist(String text) {
        List<Data> mydata = databaseHelper.getAllData();
        List<Data> filteredData = new ArrayList<>(); // Create a new list for filtered items
        for (Data data : mydata) {
            if (data.getFirstname().toLowerCase().contains(text.toLowerCase())) {
                filteredData.add(data); // Add matching items to the filtered list
            }
        }
        if (filteredData.isEmpty()) {
            Toast.makeText(getActivity(), "No data found", LENGTH_SHORT).show();
        } else {
            adapter.setfiltereadlist(filteredData); // Update the adapter with the filtered list
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    public static void notifyAdapter() {
        adapter.notifyDataSetChanged();


    }


}
