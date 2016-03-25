package com.example.tangshisongci.module.kit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.tangshisongci.R;
import com.example.tangshisongci.bace.BaceActivity;
import com.example.tangshisongci.model.QuickResult;
import com.example.tangshisongci.model.XieHouYu;
import com.example.tangshisongci.model.base.BaseModel;
import com.example.tangshisongci.model.base.MyDataBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bigwen on 2016/3/24.
 */
public class XieHouYuActivity extends BaceActivity {

    private ListView listView;
    private XieHouYuAdapter xieHouYuAdapter;
    private List<XieHouYu> xieHouYuList = new ArrayList<>();
    private List<QuickResult> quickResults = new ArrayList<>();
    private int type = -1;
    public static final String Intent = "XiuhouyuType";
    private int itemSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiuhouyu);
        listView =  (ListView) findViewById(R.id.activity_xiehou_list);
        xieHouYuAdapter = new XieHouYuAdapter();
        Intent intent = getIntent();
        type = intent.getIntExtra(Intent,-1);
        listView.setAdapter(xieHouYuAdapter);
        if (type == -1){
            return;
        }
        if(type == 1) {
            initXiehou();
        }
        if (type == 2){
            initQuick();
        }
    }

    private void initSize(){
        if (type == -1){
            itemSize = 0;
        }
        if (type == 1) {
            itemSize = xieHouYuList.size();
        }
        if (type == 2){
            itemSize = quickResults.size();
        }
    }

    private void initQuick(){
        List<BaseModel> baseModels = MyDataBase.getInstence().loadAllFromDB(new QuickResult());
        if (baseModels != null && baseModels.size() >0){
            for (BaseModel baseModel:baseModels){
                if (baseModel instanceof QuickResult){
                    quickResults.add((QuickResult)baseModel);
                }
            }
        }
        initSize();
        xieHouYuAdapter.notifyDataSetChanged();
    }

    private void initXiehou(){
        List<BaseModel> baseModels = MyDataBase.getInstence().loadAllFromDB(new XieHouYu());
        if (baseModels != null && baseModels.size() >0){
            for (BaseModel baseModel:baseModels){
                if (baseModel instanceof XieHouYu){
                    xieHouYuList.add((XieHouYu)baseModel);
                }
            }
        }
        initSize();
        xieHouYuAdapter.notifyDataSetChanged();
    }

    public class XieHouYuAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return itemSize;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = new XiehouYuItem(XieHouYuActivity.this);
            }
            ((XiehouYuItem)convertView).setData(xieHouYuList.get(position));
            return convertView;
        }
    }
}
