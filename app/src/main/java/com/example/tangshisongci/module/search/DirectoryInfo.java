package com.example.tangshisongci.module.search;

/**
 * Created by bigwen on 2016/2/1.
 */
public class DirectoryInfo {
    private String auther;
    private String name;
    private int type;//1:唐诗 2宋词元曲
    private int id;

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
