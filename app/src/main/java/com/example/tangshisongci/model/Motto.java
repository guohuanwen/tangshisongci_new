package com.example.tangshisongci.model;

import com.example.tangshisongci.model.base.MyAnnotion;
import com.example.tangshisongci.model.base.MyModel;

//Motto
@MyAnnotion.DBName("Motto")
public class Motto  extends MyModel{
  @MyAnnotion.DBField
  public int ClassId;
  @MyAnnotion.DBField
  public String Content;
  @MyAnnotion.DBField
  private String Author;
  @MyAnnotion.DBField
  private String Rank;

  public int getClassId() {
    return ClassId;
  }

  public void setClassId(int classId) {
    ClassId = classId;
  }

  public String getContent() {
    return Content;
  }

  public void setContent(String content) {
    Content = content;
  }

  public String getAuthor() {
    return Author;
  }

  public void setAuthor(String author) {
    Author = author;
  }

  public String getRank() {
    return Rank;
  }

  public void setRank(String rank) {
    Rank = rank;
  }
}