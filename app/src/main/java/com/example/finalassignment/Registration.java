package com.example.finalassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalassignment.model.UserDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Registration extends AppCompatActivity {

    private EditText name, surname, Phone, pn;
    private ProgressBar progressBar;

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference users = database.getReference().child("Mpesa App");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = (EditText) findViewById(R.id.fname);
        surname = (EditText) findViewById(R.id.lname);
        Phone = (EditText) findViewById(R.id.phone2);
        pn=(EditText)findViewById(R.id.pin2);
        Button register = (Button) findViewById(R.id.Register);
        Button login = (Button) findViewById(R.id.Login);

        UserDetails user = new UserDetails();
        progressBar = findViewById(R.id.progressBar);

        login.setOnClickListener(v -> {
            Intent l = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(l);
        });


        register.setOnClickListener((v) -> {
            progressBar.setVisibility(View.VISIBLE);
            String phone = Phone.getText().toString();

            users.orderByChild("Phone").equalTo(String.valueOf(phone)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){

                        Toast.makeText(Registration.this, phone+" Phone number is already registered!!!", Toast.LENGTH_SHORT).show();

                    }else {
                        String fname = name.getText().toString();
                        String lname = surname.getText().toString();
                        String phone2 = Phone.getText().toString();
                        String pin2 = pn.getText().toString();
                        String amount = "100.00";




                        HashMap<String, String> user = new HashMap<>();

                        user.put("First Name", lname);
                        user.put("Last Name", fname);
                        user.put("Phone Number", phone2);
                        user.put("Amount", amount);
                        user.put("Pin Code", pin2);


                        users.push().setValue(user).addOnCompleteListener((task) -> {
                            Toast.makeText(Registration.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                            Intent s = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(s);
                            progressBar.setVisibility(View.GONE);
                        });

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });

    }
}