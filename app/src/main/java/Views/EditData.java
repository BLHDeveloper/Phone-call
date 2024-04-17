package Views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.phonecall.ContactsFragment;
import com.example.phonecall.R;

import Controller.DatabaseHelper;
import Model.Data;

public class EditData extends AppCompatActivity {
    private EditText firstnameText , lastnameText , companyText ,phoneText,emailText, notesText;
    private Button editButton;
    private Button CancelButton;

    private DatabaseHelper databaseHelper;
    Data personInfo;
    int position;
    String str_position;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        databaseHelper = new DatabaseHelper(this);
        firstnameText = findViewById(R.id.firstname1);
        lastnameText = findViewById(R.id.lastname1);
        companyText = findViewById(R.id.company1);
        phoneText = findViewById(R.id.phone1);
        emailText = findViewById(R.id.email1);
        notesText   = findViewById(R.id.notes1);
        editButton = findViewById(R.id.buttonedit);
        CancelButton = findViewById(R.id.Cancel);


        Bundle bundle = getIntent().getExtras();
        str_position = bundle.getString("position");
        position = Integer.parseInt(str_position);
        personInfo = databaseHelper.getData(position);

        if(personInfo != null){
            firstnameText.setText(personInfo.getFirstname());
            lastnameText.setText(personInfo.getLastname());
            companyText.setText(personInfo.getCompany());
            phoneText.setText(personInfo.getPhone());
            emailText.setText(personInfo.getEmail());
            notesText.setText(personInfo.getNotes());
        }



        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personInfo.setFirstname(  firstnameText.getText().toString() );
                personInfo.setLastname(  lastnameText.getText().toString() );
                personInfo.setCompany(  companyText.getText().toString() );
                personInfo.setPhone(  phoneText.getText().toString() );
                personInfo.setEmail(  emailText.getText().toString() );
                personInfo.setNotes(  notesText.getText().toString() );


            databaseHelper.updateData(personInfo);
                ContactsFragment.notifyAdapter();
                Intent intent = new Intent(EditData.this, ContactsFragment.class);
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
