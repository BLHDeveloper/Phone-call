package com.example.phonecall;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;

import java.util.List;

import Controller.Adapter;
import Controller.Adapter3;
import Controller.DatabaseHelper;
import Model.Data;
import Views.AddData;
import Views.AddFavorites;


public class FavoritesFragment extends Fragment {
    private RecyclerView recyclerView;
    private static Adapter3 adapter;
    private DatabaseHelper databaseHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerView = view.findViewById(R.id.Recyclerviewfavorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        databaseHelper = new DatabaseHelper(getActivity());

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton addfavorite = view.findViewById(R.id.addfavorite);
        addfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddFavorites.class);
                startActivity(intent);

            }
        });
        adapter = new Adapter3(getActivity(),databaseHelper.getAllData(),databaseHelper);
        recyclerView.setAdapter(adapter);

        List<Data> mydata = databaseHelper.getAllData();
        for(Data data : mydata){
            String myInfo = " Firstname: "+data.getFirstname()+" Lastname : "+data.getLastname()
                    + " Company: "+data.getCompany() + " Phone: "+data.getPhone() + " Email: "+data.getEmail()+" Notes: "+ data.getNotes();
            Log.d("data", myInfo);}
        return view;
    }
    @SuppressLint("NotifyDataSetChanged")
    public static void notifyAdapter() {
        adapter.notifyDataSetChanged();


    }
}