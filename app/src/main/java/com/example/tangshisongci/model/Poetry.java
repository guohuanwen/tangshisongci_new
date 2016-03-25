package com.example.tangshisongci.model;

import com.example.tangshisongci.model.base.MyAnnotion;
import com.example.tangshisongci.model.base.MyModel;

/**
 * Created by bigwen on 2016/1/29.
 */
@MyAnnotion.DBName("Poetry")
public class Poetry extends MyModel {

    @MyAnnotion.DBField
    private int _id;
    @MyAnnotion.DBField
    private String Type1;
    @MyAnnotion.DBField
    private String Title;
    @MyAnnotion.DBField
    private String Author;
    @MyAnnotion.DBField
    private String Type2;
    @MyAnnotion.DBField
    private String Age;
    @MyAnnotion.DBField
    private String Content;
    @MyAnnotion.DBField
    private String Description;
    @MyAnnotion.DBField
    private int ClassID;
    @MyAnnotion.DBField
    private int mark;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getType1() {
        return Type1;
    }

    public void setType1(String type1) {
        Type1 = type1;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getType2() {
        return Type2;
    }

    public void setType2(String type2) {
        Type2 = type2;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int classID) {
        ClassID = classID;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
