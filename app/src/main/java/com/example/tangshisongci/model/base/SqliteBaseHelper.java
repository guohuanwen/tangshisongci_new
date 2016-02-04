package com.example.tangshisongci.model.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by bigwen on 2015/12/26.
 */
public class SqliteBaseHelper extends SQLiteOpenHelper {
    private String TAG = SqliteBaseHelper.class.getName();


    public SqliteBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade " + oldVersion + "  " + newVersion);
    }

}
