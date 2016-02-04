package com.example.tangshisongci.model;

import com.example.tangshisongci.model.base.MyAnnotion;
import com.example.tangshisongci.model.base.MyModel;

/**
 * Created by bigwen on 2016/1/29.
 */
@MyAnnotion.DBName("ChinaTang")
public class ChinaTang extends MyModel {
    @MyAnnotion.DBField
    private String className;
    @MyAnnotion.DBField
    private String title;
    @MyAnnotion.DBField
    private String author;
    @MyAnnotion.DBField
    private String note;
    @MyAnnotion.DBField
    private String yunyi;
    @MyAnnotion.DBField
    private String description;
    @MyAnnotion.DBField
    private int parentID;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getYunyi() {
        return yunyi;
    }

    public void setYunyi(String yunyi) {
        this.yunyi = yunyi;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
