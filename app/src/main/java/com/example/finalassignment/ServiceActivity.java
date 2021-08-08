package com.example.finalassignment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ServiceActivity extends AppCompatActivity {

    //declare constants used with shared preferences
    public static final String MY_BALANCE = "My_Balance";
    public static final String CHECKING_KEY = "checking_key";
    public static final String SAVINGS_KEY = "savings_key";
    //declare variables for message, checking and savings balance
    String receivedString;
    public String chkBalance, savBalance;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        Intent receiveIntent= getIntent();
        String num = receiveIntent.getStringExtra("Contact");




        Bundle extras = getIntent().getExtras();

        //receive welcome msg from MainActivity
        if (extras != null) {
            receivedString = extras.getString("stringReference");
            Toast.makeText( ServiceActivity.this, receivedString, Toast.LENGTH_LONG).show();
        }else {Toast.makeText( ServiceActivity.this, receivedString, Toast.LENGTH_LONG).show();
            //end if
        }

        //read checking and savings balances from shared preferences file
        getPrefs();

        //declare menu buttons
        Button checking_BT = (Button) findViewById( R.id.checkingButton);
        Button transfer_BT = (Button) findViewById(R.id.transferButton);

        //register checking button with Event Listener class, and Event handler method
        checking_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v) {
                //user wants to access checking account
                Intent checkingIntent = new Intent( ServiceActivity.this, MoneyTransfer.class);
                //send only data related to checking account
                checkingIntent.putExtra("balance", chkBalance); //checking balance
                checkingIntent.putExtra("key", CHECKING_KEY); //key used to store checking balance
                checkingIntent.putExtra("Contacts",num);
                checkingIntent.putExtra("title", "Checking Account"); //title for transaction activity
                //display transaction activity screen
                startActivity(checkingIntent);

            }
        });//end OnClickListener checking

        transfer_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //user wants to transfer funds
                Intent transferIntent = new Intent(ServiceActivity.this, MoneyTransfer.class);
                //send both balances
                transferIntent.putExtra("balanceC", chkBalance); //checking balance
                transferIntent.putExtra("balanceS", savBalance); //savings balance
                //display transfer activity screen
                startActivity(transferIntent);

            }
        });//end OnClickListener transfer
    }//end onCreate

    //function to retrieve current balances when program resumes
    protected void onResume() {
        super.onResume();
        getPrefs();

    }//end onResume
    //function to open shared preferences and retrieve current balances
    public void getPrefs() {
        //open shared preferences xml file
        SharedPreferences BalancePref = getSharedPreferences( ServiceActivity.MY_BALANCE, MODE_PRIVATE);
        //retrieve checking and savings balances if they are not null
        //or set balances to default value if they are null
        chkBalance = BalancePref.getString(CHECKING_KEY, "5000.00");
        savBalance = BalancePref.getString(SAVINGS_KEY, "7000.00");

    }//end getPrefs


}