package com.example.tangshisongci.module.kit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tangshisongci.R;
import com.example.tangshisongci.model.XieHouYu;

/**
 * Created by bigwen on 2016/3/24.
 */
public class XiehouYuItem extends LinearLayout implements View.OnClickListener{

    private TextView textView;
    private Context mContext;
    private String  TAG = XiehouYuItem.class.getName();
    private XieHouYu xieHouYu;
    private boolean isShowAnswer = false;

    public XiehouYuItem(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public XiehouYuItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.xiehouyu_item,this);
        textView = (TextView) findViewById(R.id.xiehouyu_text);
        this.setOnClickListener(this);
    }

    public void setData(XieHouYu xieHouYu){
        this.xieHouYu = xieHouYu;
        isShowAnswer = false;
        textView.setText(xieHouYu.getTitle());
    }

    private void show(){
        if (xieHouYu == null){
            return;
        }
        if (isShowAnswer) {
            isShowAnswer = false;
            textView.setText(xieHouYu.getTitle());
        }else {
            isShowAnswer = true;
            textView.setText(xieHouYu.getTitle()+"\n\n"+xieHouYu.getContent());
        }
    }

    @Override
    public void onClick(View v) {
        show();
    }
}
