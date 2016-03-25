package com.example.tangshisongci.module.kit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tangshisongci.R;
import com.example.tangshisongci.model.Motto;
import com.example.tangshisongci.model.QuickResult;
import com.example.tangshisongci.model.Tonggue;
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
    private QuickResult quickResult;
    private TanslateInfo tanslateInfo;

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
        LayoutInflater.from(mContext).inflate(R.layout.xiehouyu_item, this);
        textView = (TextView) findViewById(R.id.xiehouyu_text);
        this.setOnClickListener(this);
    }

    private void setData(TanslateInfo xieHouYu){
        this.tanslateInfo = xieHouYu;
        isShowAnswer = false;
        textView.setText(xieHouYu.getTitle());
    }

    public void setData(XieHouYu xieHouYu){
        TanslateInfo tanslateInfo = new TanslateInfo();
        tanslateInfo.content = xieHouYu.getContent();
        tanslateInfo.title = xieHouYu.getTitle();
        setData(tanslateInfo);
    }

    public void setData(QuickResult xieHouYu){
        TanslateInfo tanslateInfo = new TanslateInfo();
        tanslateInfo.content = xieHouYu.getKey();
        tanslateInfo.title = xieHouYu.getQuestion();
        setData(tanslateInfo);
    }

    public void setData(Motto xieHouYu){
        TanslateInfo tanslateInfo = new TanslateInfo();
        tanslateInfo.content = null;
        tanslateInfo.title = xieHouYu.getContent()+"--"+xieHouYu.getAuthor();
        setData(tanslateInfo);
    }

    public void setData(Tonggue xieHouYu){
        TanslateInfo tanslateInfo = new TanslateInfo();
        tanslateInfo.content = xieHouYu.getContent();
        tanslateInfo.title = xieHouYu.getTitle();
        setData(tanslateInfo);
    }

    private void show(){
        if (tanslateInfo == null){
            return;
        }
        if (isShowAnswer) {
            isShowAnswer = false;
            textView.setText(tanslateInfo.getTitle());
        }else {
            if (tanslateInfo.getContent() == null || tanslateInfo.getContent().isEmpty()){
                return;
            }
            isShowAnswer = true;
            textView.setText(tanslateInfo.getTitle()+"\n\n"+tanslateInfo.getContent());
        }
    }

    @Override
    public void onClick(View v) {
        show();
    }

    class TanslateInfo{
        String title;
        String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
