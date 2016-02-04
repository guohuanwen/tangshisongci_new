package com.example.tangshisongci.model;

import com.example.tangshisongci.model.base.MyAnnotion;
import com.example.tangshisongci.model.base.MyModel;

/**
 * Created by bigwen on 2016/1/30.
 */
@MyAnnotion.DBName("data_list")
public class Songci extends MyModel{
    @MyAnnotion.DBField
    private String auth;
    @MyAnnotion.DBField
    private String desc;

    private int fav;
    @MyAnnotion.DBField
    private int id;
    @MyAnnotion.DBField
    private String title;
    @MyAnnotion.DBField
    private String type;//2为宋词，3为元曲，1为唐诗

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
