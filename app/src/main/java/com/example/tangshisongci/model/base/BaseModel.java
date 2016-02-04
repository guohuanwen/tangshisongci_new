package com.example.tangshisongci.model.base;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

/**
 * Created by bigwen on 2015/12/26.
 */
public abstract class BaseModel {

    public abstract String getCreatTableSql();
    public abstract String getTableName();
    public abstract String getPrimaryKeyName();
    public abstract String getPrimaryKey();
    public abstract List<BaseModel> fromCursor(Cursor cursor);
    public abstract ContentValues toContentValues();
}
