package com.example.tangshisongci.model.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.tangshisongci.tool.MyApplication;

import java.util.List;

/**
 * Created by bigwen on 2015/12/26.
 */
public class MyDataBase {
    private String TAG = MyDataBase.class.getName();
    private final static String DB_NAME = "sqlite";
    private final static int DB_VERSION = 1;
    public Context context;
    private static MyDataBase myDataBase;
    private SQLiteDatabase db;
    private SqliteBaseHelper sqliteBaseHelper;

    public static MyDataBase getInstence() {
        if (null == myDataBase) {
            myDataBase = new MyDataBase(MyApplication.getContext());
        }
        return myDataBase;
    }

    private MyDataBase(Context context) {
        this.context = context;
        sqliteBaseHelper = new SqliteBaseHelper(context, DB_NAME, null, DB_VERSION);
        db = sqliteBaseHelper.getWritableDatabase();
    }

    public void saveBean(List<MyModel> myModels) {
        for (MyModel myModel : myModels) {
            ContentValues contentValues = myModel.toContentValues();
            db.replace(myModel.getTableName(), null, contentValues);
        }
    }

    public void saveBean(MyModel myModel) {
        ContentValues contentValues = myModel.toContentValues();
        db.replace(myModel.getTableName(), null, contentValues);
    }

    public List<BaseModel> loadAllFromDB(MyModel myModel) {
        try {
            Cursor cursor = db.rawQuery(" select  *  from  " + myModel.getTableName(), null);
            return myModel.fromCursor(cursor);
        } catch (Exception e) {
            Log.e(TAG, "loadAllFromDB " + e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public void loadAllFromDBAysc(final MyModel myModel, final FindFinish findFinish) {
        Log.i(TAG, "loadAllFromDBAysc: ");
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                Log.i(TAG, "doInBackground: ");
                try {
                    Cursor cursor = db.rawQuery(" select  *  from  " + myModel.getTableName(), null);
                    return myModel.fromCursor(cursor);
                } catch (Exception e) {
                    Log.e(TAG, "loadAllFromDB " + e.toString());
                    e.printStackTrace();
                    findFinish.error(e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                Log.i(TAG, "onPostExecute: ");
                findFinish.success((List<BaseModel>)o);
            }
        };
        asyncTask.execute();
    }

    public List<BaseModel> loadFromDB(String sql, String[] param, MyModel myModel) {
        /**
         * rawQuery()方法的第一个参数为select语句；
         * 第二个参数为select语句中占位符参数的值，
         * 如果select语句没有使用占位符，该参数可以设置为null。
         * 带占位符参数的select语句使用例子如下：
         * Cursor cursor = db.rawQuery("select * from person where name like ? and age=?",
         * new String[]{"%传智%", "4"});
         *
         *
         * List<BaseModel> myModelList = myDataBase.loadFromDB(
         "select * from "+new ChinaSong().getTableName()+" where parentID = ? ",
         new String[]{"3573"},
         new ChinaSong());
         */
        try {
            Cursor cursor = db.rawQuery(sql, param);
            return myModel.fromCursor(cursor);
        } catch (Exception e) {
            Log.e(TAG, "loadFromDB " + e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public void loadFromDBAsyn(final String sql,final String[] param,final MyModel myModel,final FindFinish findFinish) {
        /**
         * rawQuery()方法的第一个参数为select语句；
         * 第二个参数为select语句中占位符参数的值，
         * 如果select语句没有使用占位符，该参数可以设置为null。
         * 带占位符参数的select语句使用例子如下：
         * Cursor cursor = db.rawQuery("select * from person where name like ? and age=?",
         * new String[]{"%传智%", "4"});
         *
         *
         * List<BaseModel> myModelList = myDataBase.loadFromDB(
         "select * from "+new ChinaSong().getTableName()+" where parentID = ? ",
         new String[]{"3573"},
         new ChinaSong());
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Cursor cursor = db.rawQuery(sql, param);
                    final List<BaseModel> models = myModel.fromCursor(cursor);
                    new Handler(Looper.getMainLooper()).
                            post(new Runnable() {
                                @Override
                                public void run() {
                                    findFinish.success(models);
                                }
                            });
                } catch (final Exception e) {
                    Log.e(TAG, "loadFromDB " + e.toString());
                    new Handler(Looper.getMainLooper()).
                            post(new Runnable() {
                                @Override
                                public void run() {
                                    findFinish.error(e);
                                }
                            });

                }
            }
        }).start();

    }

    public void deleteAll(MyModel baseModel){
        db.delete(baseModel.getTableName(),null,null);
    }

    public void translateType(List<BaseModel> baseModels){

    }

    public interface FindFinish{
        void success(final List<BaseModel> baseModels);
        void error(Exception e);
    }
}
