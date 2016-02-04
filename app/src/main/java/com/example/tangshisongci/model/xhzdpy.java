package com.example.tangshisongci.model;

import com.example.tangshisongci.model.base.MyAnnotion;
import com.example.tangshisongci.model.base.MyModel;

/**
 * Created by bigwen on 2016/1/29.
 */
@MyAnnotion.DBName("xhzdpy")
public class xhzdpy extends MyModel {
    @MyAnnotion.DBField
    private String PY;
    @MyAnnotion.DBField
    private int dictId;

    public String getPY() {
        return PY;
    }

    public void setPY(String PY) {
        this.PY = PY;
    }

    public int getDictId() {
        return dictId;
    }

    public void setDictId(int dictId) {
        this.dictId = dictId;
    }
}
