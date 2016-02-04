package com.example.tangshisongci.model;

import com.example.tangshisongci.model.base.MyAnnotion;
import com.example.tangshisongci.model.base.MyModel;

//wordbase
@MyAnnotion.DBName("WordBase")
public class WordBase extends MyModel
{
  @MyAnnotion.DBField
  public String Title;
  @MyAnnotion.DBField
  public String PingYing;
  @MyAnnotion.DBField
  public String Description;
  @MyAnnotion.DBField
  public String ComeFrom;
  @MyAnnotion.DBField
  public String Content;

  public String getTitle() {
    return Title;
  }

  public void setTitle(String title) {
    Title = title;
  }

  public String getPingYing() {
    return PingYing;
  }

  public void setPingYing(String pingYing) {
    PingYing = pingYing;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String description) {
    Description = description;
  }

  public String getComeFrom() {
    return ComeFrom;
  }

  public void setComeFrom(String comeFrom) {
    ComeFrom = comeFrom;
  }

  public String getContent() {
    return Content;
  }

  public void setContent(String content) {
    Content = content;
  }
}