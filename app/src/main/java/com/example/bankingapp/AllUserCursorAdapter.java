package com.example.bankingapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.bankingapp.data.BankContract;

public class AllUserCursorAdapter extends CursorAdapter {

    public AllUserCursorAdapter(Context context, Cursor c){

        super (context, c,0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView personName= (TextView)view.findViewById(R.id.personName);
        TextView currentBalance=(TextView)view.findViewById(R.id.currentBalance);

        int nameColumnIndex = cursor.getColumnIndex(BankContract.BankEntry.COL_ACCOUNT_NAME);
        int balanceIndex= cursor.getColumnIndex(BankContract.BankEntry.COL_ACCOUNT_BALANCE);

        String name= cursor.getString(nameColumnIndex);
        int CBalance=cursor.getInt(balanceIndex);

        personName.setText(name);
        currentBalance.setText(String.valueOf(CBalance));



    }
}
