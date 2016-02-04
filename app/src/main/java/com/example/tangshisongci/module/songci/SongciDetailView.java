package com.example.tangshisongci.module.songci;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tangshisongci.R;
import com.example.tangshisongci.model.Songci;
import com.example.tangshisongci.model.base.BaseModel;
import com.example.tangshisongci.model.base.MyDataBaseSong;

import java.util.List;

/**
 * Created by bigwen on 2016/1/29.
 */
public class SongciDetailView extends LinearLayout {
    private String TAG = SongciDetailView.class.getName();
    private Context mContext;
    private TextView name;
    private TextView auther;
    private TextView text;


    public SongciDetailView(Context context,int id) {
        super(context);
        mContext = context;
        init(id);
    }


    public SongciDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
//        init();
    }

    private void init(int id) {
        LayoutInflater.from(mContext).inflate(R.layout.songci_detail_view, this);
        name = (TextView) findViewById(R.id.tangshi_detail_view_name);
        auther = (TextView) findViewById(R.id.tangshi_detail_view_poet);
        text = (TextView) findViewById(R.id.tangshi_detail_view_text);
        loadFromDb(id);
    }

    public void loadFromDb(int id) {
        MyDataBaseSong.getInstence().loadFromDBAsyn(
                "select * from " + new Songci().getTableName() + " where id = ? ",
                new String[]{id + ""},
                new Songci(),
                new MyDataBaseSong.FindFinish() {
                    @Override
                    public void success(final List<BaseModel> baseModels) {
                        Log.i(TAG, "success ");
                        setViewByHandler(baseModels);
                    }

                    @Override
                    public void error(Exception e) {
                        Log.e(TAG, "error "+e.toString());
                    }
                }
        );
    }

    private void setViewByHandler(final List<BaseModel> baseModels) {
        Log.i(TAG, "setViewByHandler ");
        if (baseModels == null || baseModels.size() == 0) {
            return;
        }
        final Songci songci = (Songci) baseModels.get(0);
        final String text = Html.fromHtml(songci.getDesc()).toString();
        new Handler(Looper.getMainLooper())
                .post(new Runnable() {
                    @Override
                    public void run() {
                        setView(songci.getTitle(), songci.getAuth(), text);
                    }
                });

    }

    public void setView(String name, String auther, String text) {
        Log.i(TAG, "setView ");
        this.name.setText(name);
        this.auther.setText(auther);
        this.text.setText(text);
    }


}
