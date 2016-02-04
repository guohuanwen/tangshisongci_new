package com.example.tangshisongci.model;

import com.example.tangshisongci.model.base.MyAnnotion;
import com.example.tangshisongci.model.base.MyModel;

/**
 * Created by bigwen on 2016/1/29.
 */
@MyAnnotion.DBName("WordDetails")
public class WordDetails extends MyModel {
    @MyAnnotion.DBField
    private int ID;
    @MyAnnotion.DBField
    private String Detail;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }
}
