package com.example.tangshisongci.model;

import com.example.tangshisongci.model.base.MyAnnotion;
import com.example.tangshisongci.model.base.MyModel;

/**
 * Created by bigwen on 2016/1/29.
 */
@MyAnnotion.DBName("xhzd")
public class xhzd extends MyModel {
    @MyAnnotion.DBField
    private String ZI;
    @MyAnnotion.DBField
    private String PY;
    @MyAnnotion.DBField
    private String WB;
    @MyAnnotion.DBField
    private String bushou;
    @MyAnnotion.DBField
    private String Bihua;
    @MyAnnotion.DBField
    private String Pingying;
    @MyAnnotion.DBField
    private String Jijie;
    @MyAnnotion.DBField
    private String XiangJie;

    public String getZI() {
        return ZI;
    }

    public void setZI(String ZI) {
        this.ZI = ZI;
    }

    public String getPY() {
        return PY;
    }

    public void setPY(String PY) {
        this.PY = PY;
    }

    public String getWB() {
        return WB;
    }

    public void setWB(String WB) {
        this.WB = WB;
    }

    public String getBushou() {
        return bushou;
    }

    public void setBushou(String bushou) {
        this.bushou = bushou;
    }

    public String getBihua() {
        return Bihua;
    }

    public void setBihua(String bihua) {
        Bihua = bihua;
    }

    public String getPingying() {
        return Pingying;
    }

    public void setPingying(String pingying) {
        Pingying = pingying;
    }

    public String getJijie() {
        return Jijie;
    }

    public void setJijie(String jijie) {
        Jijie = jijie;
    }

    public String getXiangJie() {
        return XiangJie;
    }

    public void setXiangJie(String xiangJie) {
        XiangJie = xiangJie;
    }
}
