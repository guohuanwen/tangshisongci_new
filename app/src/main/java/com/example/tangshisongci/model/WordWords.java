package com.example.tangshisongci.model;

import com.example.tangshisongci.model.base.MyAnnotion;
import com.example.tangshisongci.model.base.MyModel;

/**
 * Created by bigwen on 2016/1/29.
 */
@MyAnnotion.DBName("WordWords")
public class WordWords extends MyModel {
    @MyAnnotion.DBField
    private String Name;
    @MyAnnotion.DBField
    private String yinbiao;
    @MyAnnotion.DBField
    private int detail_id;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getYinbiao() {
        return yinbiao;
    }

    public void setYinbiao(String yinbiao) {
        this.yinbiao = yinbiao;
    }

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }
}
