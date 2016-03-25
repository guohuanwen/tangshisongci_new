package com.example.tangshisongci.module.kit;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.example.tangshisongci.R;
import com.example.tangshisongci.bace.BaceActivity;
import com.example.tangshisongci.model.Poetry;
import com.example.tangshisongci.model.base.BaseModel;
import com.example.tangshisongci.model.base.MyDataBase;

import java.util.List;

/**
 * Created by bigwen on 2016/3/21.
 */
public class PoetryDetailActivity extends BaceActivity {

    private Context mContext;
    private TextView name;
    private TextView poet;
    private TextView text;
    private TextView note;
    private TextView explain;
    private int title;
    public static String INTENTTITLE = "_id";
    private String TAG = PoetryDetailActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getIntent().getIntExtra(INTENTTITLE, -1);
        if (title == -1) {
            return;
        }
        Log.i(TAG, "onCreate: " + title);
        setContentView(R.layout.tangshi_detail_view);
        init();
    }

    private void init() {
        name = (TextView) findViewById(R.id.tangshi_detail_view_name);
        poet = (TextView) findViewById(R.id.tangshi_detail_view_poet);
        text = (TextView) findViewById(R.id.tangshi_detail_view_text);
        note = (TextView) findViewById(R.id.tangshi_detail_view_note);
        explain = (TextView) findViewById(R.id.tangshi_detail_view_explain);
        loadDB(title);
    }

    private void loadDB(int parentID) {
        MyDataBase.getInstence().loadFromDBAsyn(
                " select  *  from " + new Poetry().getTableName() + " where _id = ? ",
                new String[]{""+parentID},
                new Poetry(), new MyDataBase.FindFinish() {
                    @Override
                    public void success(final List<BaseModel> baseModels) {
                        Log.i(TAG, "success: " + baseModels.size());
                        setView(baseModels);
                    }

                    @Override
                    public void error(Exception e) {
                        Log.e(TAG, "error: " + e.toString());

                    }
                });
    }

    public void setView(List<BaseModel> baseModels) {
        if (baseModels.size() == 0) {
            return;
        }
        BaseModel baseModel = baseModels.get(0);
        if (!(baseModel instanceof Poetry)) {
            return;
        }
        Poetry chinaTang = (Poetry) baseModel;
        name.setText(chinaTang.getTitle());
        poet.setText(chinaTang.getAuthor());
        String t = chinaTang.getContent();
        if (t != null && !t.isEmpty()) {
            t = t.replace("，", ",\n");
            t = t.replace("。", "。\n");
            t = t.replace("？", "？\n");
            t = t.replace("！", "！\n");
            t = t.replace("；", "；\n");
            t = t.replaceAll(" ", "");
            text.setText(t);
        }
        String n = chinaTang.getDescription();
        if (n != null && !n.isEmpty()) {
            n = n.replaceAll("。", "。\n");
            n = n.replaceAll(" ", "");
            note.setText(n);
        }
//        explain.setText(chinaTang.getDescription());
    }


}
