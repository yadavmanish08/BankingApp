package com.example.bankingapp.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class BankContract {
    private BankContract(){}

    public static final String CONTENT_AUTHORITY = "com.example.bankingapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_BANK= "AccountDetails";



    public static final class BankEntry implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BANK);

        public static final String  _ID= BaseColumns._ID;
        public static final String TABLE_NAME="AccountDetails";
        public static final String COL_ACCOUNT_NAME= "name";
        public static final String COL_ACCOUNT_NUM= "accountNum";
        public static final String COL_ACCOUNT_EMAIL="email";
        public static final String COL_ACCOUNT_PHONE="phone";
        public static final String COL_ACCOUNT_BALANCE="balance";


    }
}
