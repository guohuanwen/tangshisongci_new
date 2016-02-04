package com.example.tangshisongci.module.tangshi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tangshisongci.R;
import com.example.tangshisongci.model.ChinaTang;
import com.example.tangshisongci.model.base.BaseModel;
import com.example.tangshisongci.model.base.MyDataBase;

import java.util.List;

/**
 * Created by bigwen on 2016/1/29.
 */
public class TangshiDetailView extends LinearLayout {
    private Context mContext;
    private TextView name;
    private TextView poet;
    private TextView text;
    private TextView note;
    private TextView explain;
    private int id;

    public TangshiDetailView(Context context) {
        super(context);
        mContext = context;
    }

    public TangshiDetailView(Context context, int id) {
        super(context);
        mContext = context;
        this.id = id;
        init();
    }

    public TangshiDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.tangshi_detail_view, this);
        name = (TextView) findViewById(R.id.tangshi_detail_view_name);
        poet = (TextView) findViewById(R.id.tangshi_detail_view_poet);
        text = (TextView) findViewById(R.id.tangshi_detail_view_text);
        note = (TextView) findViewById(R.id.tangshi_detail_view_note);
        explain = (TextView) findViewById(R.id.tangshi_detail_view_explain);
        loadDB(id);
    }

    private void loadDB(int parentID) {
        MyDataBase.getInstence().loadFromDBAsyn(
                " select  *  from  " + new ChinaTang().getTableName() + " where parentID = ? ",
                new String[]{parentID + ""},
                new ChinaTang(), new MyDataBase.FindFinish() {
                    @Override
                    public void success(final List<BaseModel> baseModels) {
                        setView(baseModels);
                    }

                    @Override
                    public void error(Exception e) {

                    }
                });
    }

    public void setView(List<BaseModel> baseModels) {
        if (baseModels.size() == 0) {
            return;
        }
        BaseModel baseModel = baseModels.get(0);
        if (!(baseModel instanceof ChinaTang)) {
            return;
        }
        ChinaTang chinaTang = (ChinaTang) baseModel;
        name.setText(chinaTang.getTitle());
        poet.setText(chinaTang.getAuthor());
        String t = chinaTang.getYunyi();
        t = t.replace("，", ",\n");
        t = t.replace("。", "。\n");
        t = t.replace("？", "？\n");
        t = t.replace("！", "！\n");
        t = t.replace("；", "；\n");
        t = t.replaceAll(" ", "");
        String n = chinaTang.getNote();
        n = n.replaceAll("。", "。\n");
        n = n.replaceAll(" ", "");
        text.setText(t);
        note.setText(n);
        explain.setText(chinaTang.getDescription());
    }
}
