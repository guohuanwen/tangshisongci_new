package com.example.tangshisongci.model.base;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

/**
 * Created by bigwen on 2015/12/26.
 */
public class MyModel extends BaseModel {
    @Override
    public String getCreatTableSql() {
        return ModelParse.getCreatTableSql(this);
    }

    @Override
    public String getTableName() {
        return ModelParse.getTableName(this);
    }

    @Override
    public String getPrimaryKeyName() {
        return null;
    }

    @Override
    public String getPrimaryKey() {
        return null;
    }

    @Override
    public List<BaseModel> fromCursor(Cursor cursor) {
        return ModelParse.fromCursor(cursor, this);
    }

    @Override
    public ContentValues toContentValues() {
        return ModelParse.toContentValues(this);
    }


}
