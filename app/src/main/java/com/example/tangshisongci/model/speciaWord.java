package com.example.tangshisongci.model;

import com.example.tangshisongci.model.base.MyAnnotion;
import com.example.tangshisongci.model.base.MyModel;

/**
 * Created by bigwen on 2016/1/29.
 */
@MyAnnotion.DBName("speciaWord")
public class speciaWord extends MyModel {
    @MyAnnotion.DBField
    private String wordindex;
    @MyAnnotion.DBField
    private String word;
    @MyAnnotion.DBField
    private String samWord;
    @MyAnnotion.DBField
    private String difWord;

    public String getWordindex() {
        return wordindex;
    }

    public void setWordindex(String wordindex) {
        this.wordindex = wordindex;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSamWord() {
        return samWord;
    }

    public void setSamWord(String samWord) {
        this.samWord = samWord;
    }

    public String getDifWord() {
        return difWord;
    }

    public void setDifWord(String difWord) {
        this.difWord = difWord;
    }
}
