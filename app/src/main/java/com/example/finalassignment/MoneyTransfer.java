package com.example.finalassignment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalassignment.model.UserDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.HashMap;

public
class MoneyTransfer extends AppCompatActivity {
    private UserDetails use;

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference users = database.getReference().child("Mpesa App");

    //declare constant of shared preferences file
    public static final String MY_BALANCE = "My_Balance";

    //declare variables
    public String receivedBalance, receivedKey, receivedTitle; //data received from menu activity
    public double BalanceD;
    public double DepositEntered, NewBalance, WithdrawEntered;
    TextView BalanceTV, TitleTV;
    public DecimalFormat currency = new DecimalFormat( "$###,##0.00"); //decimal formatting
    SharedPreferences.Editor myEditor;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_transfer);
        Intent receiveIntent= getIntent();
        String num = receiveIntent.getStringExtra("Contacts");



        //receive balance, key and title from menu activity
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            receivedBalance = extras.getString("balance");
            receivedKey = extras.getString("key");
            receivedTitle = extras.getString("title");


        }//end if
        //set layout title depending on data that was received to checking or savings account
        TitleTV = (TextView) findViewById(R.id.titleTextView);
        TitleTV.setText(receivedTitle);

        //set current balance of checking or savings account
        BalanceTV = (TextView) findViewById(R.id.BalanceTextView);
        BalanceD = Double.parseDouble(String.valueOf(receivedBalance));
        BalanceTV.setText(String.valueOf(currency.format(BalanceD)));

        //declare deposit button
        Button DepositB = (Button) findViewById( R.id.MoneyButton);
        //declare user deposit input amount
        final EditText DepositET = (EditText) findViewById( R.id.DepositEditText);

        //register deposit button with Event Listener class, and Event handler method
        DepositB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v) {
                //if deposit field is not empty, get deposit amount
                if (!TextUtils.isEmpty( DepositET.getText())) {
                    DepositEntered = Double.parseDouble(String.valueOf(DepositET.getText()));
                    //create deposit object
//                    Deposit dp = new Deposit();
//                    dp.setBalance(BalanceD);
//                    dp.setDeposit(DepositEntered);
//
//                    //calculate new balance
//                    NewBalance = dp.getNewBalance();

                    BalanceTV.setText(String.valueOf(currency.format(NewBalance)));
                    BalanceD = NewBalance;





                    //reset user deposit amount to zero
                    DepositEntered = 0;
                }//end if

                //clear deposit field
                DepositET.setText(null);




            }
        });//end DepositB OnClick



    }//end onCreate
    //function to open and edit shared preferences file
    protected void onPause() {

        super.onPause();
        //open shared preferences xml file
        myEditor = getSharedPreferences(MY_BALANCE, MODE_PRIVATE).edit();

        //save new checking or savings balance
        myEditor.putString(receivedKey, String.valueOf(BalanceD));
        myEditor.apply();

    }//end onPause

}