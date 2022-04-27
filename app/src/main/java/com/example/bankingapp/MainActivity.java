package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button userBtn;
    private Button transactionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      userBtn=findViewById(R.id.usersButton);
      transactionBtn= findViewById(R.id.transactionsButton);

      userBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent= new Intent(MainActivity.this, AllUsersList.class);
              startActivity(intent);
          }
      });


      transactionBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(MainActivity.this,TransactionActivity.class);
              startActivity(intent);
          }
      });

    }

}