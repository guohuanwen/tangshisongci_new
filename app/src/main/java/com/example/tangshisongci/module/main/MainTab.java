package com.example.tangshisongci.module.main;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tangshisongci.R;

/**
 * Created by bigwen on 2016/1/29.
 */
public class MainTab extends LinearLayout implements View.OnClickListener {
    private Context mContext;
    private TextView tangshi;
    private TextView songci;
    private TextView yuanqu;
    private TextView tool;
    private OnTouch onTouch;
    private String TAG = MainTab.class.getName();

    public MainTab(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public MainTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();

    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.main_tab, this);
        tangshi = (TextView) findViewById(R.id.main_tab_tangshi);
        songci = (TextView) findViewById(R.id.main_tab_songci);
        yuanqu = (TextView) findViewById(R.id.main_tab_yuanqu);
        tool = (TextView) findViewById(R.id.main_tab_tool);
        tangshi.setOnClickListener(this);
        songci.setOnClickListener(this);
        yuanqu.setOnClickListener(this);
        tool.setOnClickListener(this);
    }

    public void select(int position) {
        Log.i(TAG, "select "+position);
        tangshi.setTextColor(Color.parseColor("#333333"));
        songci.setTextColor(Color.parseColor("#333333"));
        yuanqu.setTextColor(Color.parseColor("#333333"));
        tool.setTextColor(Color.parseColor("#333333"));
        switch (position) {
            case 0:
                tangshi.setTextColor(Color.parseColor("#45c01a"));
                break;
            case 1:
                songci.setTextColor(Color.parseColor("#45c01a"));
                break;
            case 2:
                yuanqu.setTextColor(Color.parseColor("#45c01a"));
                break;
            case 3:
                tool.setTextColor(Color.parseColor("#45c01a"));
                break;
        }
    }

    public void setOnTouch(OnTouch onTouch) {
        this.onTouch = onTouch;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_tab_tangshi:
                if (onTouch != null) {
                    onTouch.click(0);
                }
                break;
            case R.id.main_tab_songci:
                if (onTouch != null) {
                    onTouch.click(1);
                }
                break;
            case R.id.main_tab_yuanqu:
                if (onTouch != null) {
                    onTouch.click(2);
                }
                break;
            case R.id.main_tab_tool:
                if (onTouch != null) {
                    onTouch.click(3);
                }
                break;
        }
    }

    public interface OnTouch {
        void click(int position);
    }
}
