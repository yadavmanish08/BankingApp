package com.example.bankingapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DetailProvider extends ContentProvider {

    private static final String LOG_TAG = DetailProvider.class.getSimpleName();
    private UserDbHelper mDbHelper;
    private  static  final int BANK=100;
    private static final int BANK_ID = 101;

    private static final UriMatcher sUriMatcher= new UriMatcher(UriMatcher.NO_MATCH);
    static {

        sUriMatcher.addURI(BankContract.CONTENT_AUTHORITY , BankContract.PATH_BANK, BANK);
        sUriMatcher.addURI(BankContract.CONTENT_AUTHORITY , BankContract.PATH_BANK+"/#", BANK_ID);

    }
    @Override
    public boolean onCreate() {
        mDbHelper= new UserDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database= mDbHelper.getReadableDatabase();

        Cursor cursor;

        int match= sUriMatcher.match(uri);
        switch (match) {
            case BANK:
                cursor = database.query(BankContract.BankEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case BANK_ID:
                selection = BankContract.BankEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = database.query(BankContract.BankEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("cannot query unknown URI" + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
