package com.example.finalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private Button Register,Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Register=(Button) findViewById(R.id.button1);
        Register.setOnClickListener(this);

        Login=(Button) findViewById(R.id.button2);
        Login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button1:
                startActivity(new Intent(this,Registration.class));
                break;

            case R.id.button2:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}