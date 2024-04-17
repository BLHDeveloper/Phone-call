package Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phonecall.R;

import Controller.DatabaseHelper;
import Model.Data;

public class AfficheData extends AppCompatActivity {
    private ImageView CancelButton;
    private ImageButton call;
    private TextView name, number, description;
    private DatabaseHelper databaseHelper;
    Data personInfo;
    int position;
    String str_position;
    private static final int REQUEST_CALL_PHONE_PERMISSION = 1;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_data);

        databaseHelper = new DatabaseHelper(this);
        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        description = findViewById(R.id.description);

        Bundle bundle = getIntent().getExtras();
        str_position = bundle.getString("position");
        position = Integer.parseInt(str_position);
        personInfo = databaseHelper.getData(position);

        if (personInfo != null) {
            name.setText(personInfo.getFirstname());
            number.setText(personInfo.getPhone());
            description.setText(personInfo.getNotes());
        }

        CancelButton = findViewById(R.id.back);
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        call = findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom =  name.getText().toString();
                String nmbr = number.getText().toString();
                String dial = "tel:" + nmbr;

                // Check if CALL_PHONE permission is granted
                if (ContextCompat.checkSelfPermission(AfficheData.this, android.Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED) {
                    // Permission is already granted, proceed with call
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(dial));
                    intent.putExtra("com.android.contacts.extra.DISPLAY_NAME", nom); // Ajoutez le nom de la personne
                    startActivity(intent);
                } else {
                    // Request the permission
                    ActivityCompat.requestPermissions(AfficheData.this,
                            new String[]{android.Manifest.permission.CALL_PHONE},
                            REQUEST_CALL_PHONE_PERMISSION);
                }
            }
        });
    }
}
