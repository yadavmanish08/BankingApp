package com.example.bankingapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="Bank.db";
    public static final int DATABASE_VERSION =1;

    public UserDbHelper(Context context){

     super(context, DATABASE_NAME, null, DATABASE_VERSION);
}
    @Override
    public void onCreate(SQLiteDatabase db) {
    String SQL_CREATE_BANK_TABLE= "CREATE TABLE "+ BankContract.BankEntry.TABLE_NAME+" ("
            +BankContract.BankEntry._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +BankContract.BankEntry.COL_ACCOUNT_NAME+ " TEXT NOT NULL, "
            +BankContract.BankEntry.COL_ACCOUNT_BALANCE+ " INTEGER NOT NULL DEFAULT 0,"
            +BankContract.BankEntry.COL_ACCOUNT_EMAIL+ " TEXT NOT NULL,"
            +BankContract.BankEntry.COL_ACCOUNT_NUM+ " INTEGER NOT NULL,"
            +BankContract.BankEntry.COL_ACCOUNT_PHONE+ " TEXT NOT NULL) ";
    db.execSQL(SQL_CREATE_BANK_TABLE);


               /** db.execSQL("insert into " + BankContract.BankEntry.TABLE_NAME + " values('Tanishq Saini',20000,'man123@gmail.com',123456, '9842828015')");
        db.execSQL("insert into " + BankContract.BankEntry.TABLE_NAME + " values('Saini',30000,'manish123@gmail.com',789101, '9842828016')");
        db.execSQL("insert into " + BankContract.BankEntry.TABLE_NAME + " values('anish',40000,'fdkfkj123@gmail.com',223445, '9842828017')");
        db.execSQL("insert into " + BankContract.BankEntry.TABLE_NAME + " values('pooja',50000,'mkjdfk123@gmail.com',234355, '9842828018')");
        db.execSQL("insert into " + BankContract.BankEntry.TABLE_NAME + " values('kamal',60000,'kfkj123@gmail.com',234353, '9842828019')");
        //db.execSQL(SQL_CREATE_BANK_TABLE); */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insertUserData(String name,  int balance,String email, int accnumber,String number){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(BankContract.BankEntry.COL_ACCOUNT_NAME,name);
        contentValues.put(BankContract.BankEntry.COL_ACCOUNT_BALANCE,balance);
        contentValues.put(BankContract.BankEntry.COL_ACCOUNT_EMAIL,email);
        contentValues.put(BankContract.BankEntry.COL_ACCOUNT_NUM, accnumber);
        contentValues.put(BankContract.BankEntry.COL_ACCOUNT_PHONE,number);
        long res = db.insert(BankContract.BankEntry.TABLE_NAME, null, contentValues);

        return res != -1;
    }

}
