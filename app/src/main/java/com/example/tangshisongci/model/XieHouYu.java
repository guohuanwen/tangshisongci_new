package com.example.tangshisongci.model;

import com.example.tangshisongci.model.base.MyAnnotion;
import com.example.tangshisongci.model.base.MyModel;

/**
 * Created by bigwen on 2016/1/29.
 */
@MyAnnotion.DBName("XieHouYu")
public class XieHouYu extends MyModel {
    @MyAnnotion.DBField
    private String Title;
    @MyAnnotion.DBField
    private String Content;
    @MyAnnotion.DBField
    private String Simple;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getSimple() {
        return Simple;
    }

    public void setSimple(String simple) {
        Simple = simple;
    }
}
