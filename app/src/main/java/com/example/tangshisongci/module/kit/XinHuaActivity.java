package com.example.tangshisongci.module.kit;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tangshisongci.R;
import com.example.tangshisongci.bace.BaceActivity;
import com.example.tangshisongci.model.base.BaseModel;
import com.example.tangshisongci.model.base.MyDataBase;
import com.example.tangshisongci.model.xhzd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bigwen on 2016/3/21.
 */
public class XinHuaActivity extends BaceActivity{

    private EditText seachText;
    private ImageView seachBt;
    private TextView ziText;
    private TextView bushouText;
    private TextView wubiText;
    private TextView explainText;
    private TextView aboutText;
    private List<xhzd> xhzdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinhua);
        seachText = (EditText) findViewById(R.id.activity_xinhua_edit);
        seachBt = (ImageView) findViewById(R.id.activity_xinhua_serch);
        ziText = (TextView) findViewById(R.id.activity_xinhua_zi);
        bushouText = (TextView) findViewById(R.id.activity_xinhua_bushou);
        wubiText = (TextView) findViewById(R.id.activity_xinhua_wubi);
        explainText = (TextView) findViewById(R.id.activity_xinhua_explain);
        aboutText = (TextView) findViewById(R.id.activity_xinhua_about);

        seachBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seachText.getText().toString().isEmpty()){
                    return;
                }
                String s = seachText.getText().toString();
                initSeach(s);
            }
        });
    }

    private void initSeach(String s){
        List<BaseModel> baseModels = MyDataBase.getInstence().loadFromDB("select * from xhzd where ZI = ?",
                new String[]{s},
                new xhzd());
        for (BaseModel baseModel:baseModels){
            if (baseModel instanceof xhzd){
                xhzdList.add((xhzd)baseModel);
            }
        }
        if (xhzdList.size() == 1){
            xhzd x = xhzdList.get(0);
            ziText.setText(x.getZI()+"（"+x.getPingying()+"）");
            bushouText.setText("部首："+x.getBushou());
            wubiText.setText("五笔："+x.getWB());
            explainText.setText("解释：\n"+ Html.fromHtml(x.getJijie()));
            aboutText.setText("详解：\n"+Html.fromHtml(x.getXiangJie()));
        }
    }
}
