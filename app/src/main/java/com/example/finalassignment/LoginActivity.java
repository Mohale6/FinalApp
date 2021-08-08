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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference users = database.getReference().child("Mpesa App");
    String PhoneNumber, Mypin;
    ProgressBar progressBar1;
    int loginCntr = 3;
    @Override
    protected
    void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );

        Button loginButton = (Button) findViewById(R.id.LoginBTN);
        Button RegisterButton = (Button) findViewById(R.id.RegisterBTN);
        final EditText Mynumber = (EditText) findViewById(R.id.phone2);
        final EditText myPin = (EditText) findViewById(R.id.pin2);
        progressBar1 = findViewById(R.id.progressBar1);

        RegisterButton.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, Registration.class)));

        //register button with Event Listener class, and Event handler method
        loginButton.setOnClickListener(v -> {

            progressBar1.setVisibility(View.VISIBLE);

            //getting user input
            PhoneNumber = Mynumber.getText ( ).toString ( );
            Mypin = myPin.getText ( ).toString ( );
            users.orderByChild("Phone").equalTo(PhoneNumber).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull  DataSnapshot snapshot) {
                    if ((PhoneNumber.equals(""))||Mypin.equals("")){
                        Toast.makeText ( LoginActivity.this, "All field should be field with details", Toast.LENGTH_LONG ).show ( );


                    }
                    else if (snapshot.exists()){

                        for (DataSnapshot check: snapshot.getChildren()){
                            String checkPin = check.child("Pin").getValue().toString();
                            if (checkPin.equalsIgnoreCase( Mypin )){
                                Toast.makeText(LoginActivity.this, "Successfully sign in", Toast.LENGTH_SHORT).show();
                                Intent s = new Intent(getApplicationContext(), ServiceActivity.class);
                                s.putExtra("Contact",PhoneNumber);
                                startActivity(s);
                                progressBar1.setVisibility(View.GONE);

                                //display sevicesActivity
                            }

                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull  DatabaseError error) {

                }
            });
        });
    }
}