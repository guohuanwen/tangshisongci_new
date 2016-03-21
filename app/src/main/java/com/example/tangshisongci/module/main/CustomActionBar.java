package com.example.tangshisongci.module.main;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tangshisongci.R;
import com.example.tangshisongci.module.search.SearchActivity;

/**
 * Created by bigwen on 2016/1/29.
 */
public class CustomActionBar extends LinearLayout {
    private Context mContext;
    private ImageView searchImg;
    private TextView title;

    public CustomActionBar(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CustomActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.custom_action_bar, this);
        title = (TextView) findViewById(R.id.cab_title);
        searchImg = (ImageView) findViewById(R.id.custom_action_bar_search);
        searchImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SearchActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    public void setTitle(String text){
        title.setText(text);
    }
}
