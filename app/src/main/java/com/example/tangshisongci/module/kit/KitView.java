package com.example.tangshisongci.module.kit;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tangshisongci.R;

/**
 * Created by bigwen on 2016/1/29.
 */
public class KitView extends LinearLayout {

    private Context mContext;
    private String TAG = KitView.class.getName();
    private TextView recentPoetryView;
    private TextView xinhuaView;
    private TextView xiuhouView;
    private TextView jizhuanView;

    public KitView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public KitView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.kit_view, this);
        recentPoetryView = (TextView) findViewById(R.id.recent_poetry);
        xinhuaView = (TextView) findViewById(R.id.xinhua);
        xiuhouView = (TextView) findViewById(R.id.xiehouyu);
        jizhuanView = (TextView) findViewById(R.id.jizhuanwan);
        initClick();
    }

    private void initClick() {
        recentPoetryView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, PoetryActivity.class));
            }
        });
        xinhuaView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, XinHuaActivity.class));
            }
        });
        xiuhouView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, XieHouYuActivity.class);
                i.putExtra(XieHouYuActivity.Intent,1);
                mContext.startActivity(i);
            }
        });
        jizhuanView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, XieHouYuActivity.class);
                i.putExtra(XieHouYuActivity.Intent,2);
                mContext.startActivity(i);
            }
        });
    }


}
