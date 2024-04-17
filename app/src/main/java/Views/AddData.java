package Views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.phonecall.ContactsFragment;
import com.example.phonecall.R;

import Controller.DatabaseHelper;
import Model.Data;

public class AddData extends AppCompatActivity {
    private EditText firstnameText , lastnameText , companyText ,phoneText,emailText, notesText;
    private Button saveButton;
    private Button CancelButton;

    private DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        databaseHelper = new DatabaseHelper(this);
        firstnameText = findViewById(R.id.firstname);
        lastnameText = findViewById(R.id.lastname);
        companyText = findViewById(R.id.company);
        phoneText = findViewById(R.id.phone);
        emailText = findViewById(R.id.email);
        saveButton = findViewById(R.id.buttonsave);
        CancelButton = findViewById(R.id.Cancel);
        notesText   = findViewById(R.id.notes);




        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = firstnameText.getText().toString();
                String lastname = lastnameText.getText().toString();
                String notes = notesText.getText().toString();
                String company = companyText.getText().toString();
                String phone = phoneText.getText().toString();
                String email = emailText.getText().toString();

                long id = databaseHelper.insertData(new Data(firstname,lastname,company,phone,email,notes));
                Intent intent = new Intent(AddData.this, ContactsFragment.class);
                startActivity(intent);
                finish();

            }
        });
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}