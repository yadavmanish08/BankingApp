package com.example.bankingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bankingapp.data.BankContract;

public class UserActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final int BANK_LOADER = 0;
    private Button transfer;
    private Uri mCurrentBankUri;
    TextView name;
    TextView accountNumber;
    TextView email;
    TextView phonenum;
    TextView balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        transfer= findViewById(R.id.transfer_money);

        name= (TextView) findViewById(R.id.name);
        accountNumber= (TextView) findViewById(R.id.account_no);
           email=(TextView) findViewById(R.id.email_id);
               phonenum= (TextView) findViewById(R.id.phone_no);
               balance= (TextView) findViewById(R.id.avail_balance);

        Intent intent= getIntent();
        mCurrentBankUri= intent.getData();
        LoaderManager.getInstance(this).initLoader(BANK_LOADER, null, this);


        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,TransactionActivity.class);
                startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection={
                BankContract.BankEntry._ID,
                BankContract.BankEntry.COL_ACCOUNT_NAME,
                BankContract.BankEntry.COL_ACCOUNT_BALANCE,
                BankContract.BankEntry.COL_ACCOUNT_EMAIL,
                BankContract.BankEntry.COL_ACCOUNT_NUM,
                BankContract.BankEntry.COL_ACCOUNT_PHONE,};

        return new CursorLoader(this,
                mCurrentBankUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) {
            return;
        }


        if (cursor.moveToFirst()) {

            int nameColumnIndex = cursor.getColumnIndex(BankContract.BankEntry.COL_ACCOUNT_NAME);
            int BalanceColumnIndex = cursor.getColumnIndex(BankContract.BankEntry.COL_ACCOUNT_BALANCE);
            int emailColumnIndex = cursor.getColumnIndex(BankContract.BankEntry.COL_ACCOUNT_EMAIL);
            int accountNumColumnIndex = cursor.getColumnIndex(BankContract.BankEntry.COL_ACCOUNT_NUM);
            int phoneNumColumnIndex = cursor.getColumnIndex(BankContract.BankEntry.COL_ACCOUNT_PHONE);

            // final int inventoryId= cursor.getInt(idColumnIndex);
            String mBankAccName = cursor.getString(nameColumnIndex);
            int mBankBalance = cursor.getInt(BalanceColumnIndex);
            String mEmail = cursor.getString(emailColumnIndex);
            int mAccNum = cursor.getInt(accountNumColumnIndex);
            String mPhoneN=cursor.getString(phoneNumColumnIndex);


         name.setText(mBankAccName);
            balance.setText(String.valueOf(mBankBalance));
           email.setText(mEmail);
           accountNumber.setText(String.valueOf(mAccNum));
           phonenum.setText(mPhoneN);





        }



    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        name.setText("");
        balance.setText("");
        email.setText("");
        accountNumber.setText("");
        phonenum.setText("");
    }
}