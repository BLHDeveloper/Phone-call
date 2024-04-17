package Views;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.phonecall.OnItemClickListener;
import com.example.phonecall.R;

import java.util.ArrayList;
import java.util.List;

import Controller.Adapter;
import Controller.Adapter2;
import Controller.Adapter3;
import Controller.Adapter4;
import Controller.DatabaseHelper;
import Model.Data;

public class AddFavorites extends AppCompatActivity {
    private RecyclerView recyclerView;
    private static Adapter4 adapter;
    private DatabaseHelper databaseHelper;

    private SearchView searchView;
    private Button CancelButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_favorites);


        searchView = findViewById(R.id.searchView3);
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
        recyclerView = findViewById(R.id.RecyclerviewAddFav);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseHelper = new DatabaseHelper(this);
        adapter = new Adapter4(this,databaseHelper.getAllData(),databaseHelper);
        recyclerView.setAdapter(adapter);

        List<Data> mydata = databaseHelper.getAllData();
        for(Data data : mydata){
            String myInfo = " Firstname: "+data.getFirstname()+" Lastname : "+data.getLastname()
                    + " Company: "+data.getCompany() + " Phone: "+data.getPhone() + " Email: "+data.getEmail()+" Notes: "+ data.getNotes();
            Log.d("data", myInfo);
        }


        CancelButton = findViewById(R.id.Cancel);
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
            Toast.makeText(this, "No data found", LENGTH_SHORT).show();
        } else {
            adapter.setfiltereadlist(filteredData); // Update the adapter with the filtered list
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    public static void notifyAdapter() {
        adapter.notifyDataSetChanged();


    }

}