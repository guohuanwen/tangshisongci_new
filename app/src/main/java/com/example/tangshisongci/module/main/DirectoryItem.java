package com.example.tangshisongci.module.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tangshisongci.R;

/**
 * Created by bigwen on 2016/1/29.
 */
public class DirectoryItem extends LinearLayout {
    private Context mContext;
    private ListView tangshiList;
    private TextView type;
    private TextView name;
    private TextView poet;

    public DirectoryItem(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public DirectoryItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext =context;
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.tangshi_item,this);
        name = (TextView)findViewById(R.id.tangshi_item_name);
        poet = (TextView)findViewById(R.id.tangshi_item_poet);
        type = (TextView)findViewById(R.id.tangshi_item_type);
    }

    public void setView(String name,String poet,String type){
        this.name.setText(name);
        this.type.setText(type);
        this.poet.setText(poet);
    }
}
