package com.example.tangshisongci.module.kit;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by bigwen on 2016/3/21.
 */
public class ClickItemView extends LinearLayout {

    private Context mContext;

    public ClickItemView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public ClickItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {

    }
}
