package com.example.bankingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bankingapp.data.BankContract;
import com.example.bankingapp.data.UserDbHelper;

public class AllUsersList extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final int DATA_LOADER= 0;
    AllUserCursorAdapter mCursorAdapter;
    UserDbHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users_list);


        mDbHelper =new UserDbHelper(this);
        ListView list= (ListView)findViewById(R.id.list);
        addUserData();

        mCursorAdapter= new AllUserCursorAdapter(this, null);
        list.setAdapter(mCursorAdapter);


        LoaderManager.getInstance(this).initLoader(0,null,this);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(AllUsersList.this,UserActivity.class);

                Uri currentBankUri= ContentUris.withAppendedId(BankContract.BankEntry.CONTENT_URI,id);
                intent.setData(currentBankUri);
                startActivity(intent);

            }
        });

    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection ={
                BankContract.BankEntry._ID,
                BankContract.BankEntry.COL_ACCOUNT_NAME,
                BankContract.BankEntry.COL_ACCOUNT_PHONE,
                BankContract.BankEntry.COL_ACCOUNT_BALANCE,
                BankContract.BankEntry.COL_ACCOUNT_EMAIL,
                BankContract.BankEntry.COL_ACCOUNT_NUM,

        };
        return new CursorLoader(this,
                BankContract.BankEntry.CONTENT_URI,
                projection,
                null,
                null,
                null
                );
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
    public void addUserData(){
        mDbHelper.insertUserData("Manish Yadav",2222, "manish123@gmail.com",12344556,"9842828014");
        mDbHelper.insertUserData("Manish ",24522, "manish23@gmail.com",124556,"9842828013");
    }
}