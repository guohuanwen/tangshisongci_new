package com.example.tangshisongci.model;

import com.example.tangshisongci.model.base.MyAnnotion;
import com.example.tangshisongci.model.base.MyModel;

/**
 * Created by bigwen on 2016/1/29.
 */
@MyAnnotion.DBName("xhzdSound")
public class xhzdSound extends MyModel {
    @MyAnnotion.DBField
    private String py;
    @MyAnnotion.DBField
    private String pingying;
    @MyAnnotion.DBField
    private String diao;

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public String getPingying() {
        return pingying;
    }

    public void setPingying(String pingying) {
        this.pingying = pingying;
    }

    public String getDiao() {
        return diao;
    }

    public void setDiao(String diao) {
        this.diao = diao;
    }
}
