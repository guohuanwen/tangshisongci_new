package com.example.tangshisongci.module.kit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.tangshisongci.R;
import com.example.tangshisongci.bace.BaceActivity;
import com.example.tangshisongci.model.Motto;
import com.example.tangshisongci.model.QuickResult;
import com.example.tangshisongci.model.Tonggue;
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
    private List<Motto> mottos = new ArrayList<>();
    private List<Tonggue> tonggues = new ArrayList<>();
    private int type = -1;
    public static final String INTENT = "XiuhouyuType";
    private int itemSize = 0;
    private String TAG = XieHouYuActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiuhouyu);
        listView =  (ListView) findViewById(R.id.activity_xiehou_list);
        xieHouYuAdapter = new XieHouYuAdapter();
        Intent intent = getIntent();
        type = intent.getIntExtra(INTENT,-1);
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
        if (type == 3){
            initZuoyou();
        }
        if (type == 4){
            initTongu();
        }
    }

    private void initTongu() {
        MyDataBase.getInstence().loadAllFromDBAysc(new Tonggue(), new MyDataBase.FindFinish() {
            @Override
            public void success(List<BaseModel> baseModels) {
                Log.i(TAG, "success: "+baseModels.size());
                if (baseModels != null && baseModels.size() >0){
                    for (BaseModel baseModel:baseModels){
                        if (baseModel instanceof Tonggue){
                            tonggues.add((Tonggue)baseModel);
                        }
                    }
                }
                initSize();
                xieHouYuAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(Exception e) {

            }
        });
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
        if (type == 3){
            itemSize = mottos.size();
        }
        if (type == 4){
            itemSize = tonggues.size();
        }
    }

    private void initZuoyou(){

        MyDataBase.getInstence().loadAllFromDBAysc(new Motto(), new MyDataBase.FindFinish() {
            @Override
            public void success(List<BaseModel> baseModels) {
                Log.i(TAG, "success: "+baseModels.size());
                if (baseModels != null && baseModels.size() >0){
                    for (BaseModel baseModel:baseModels){
                        if (baseModel instanceof Motto){
                            mottos.add((Motto)baseModel);
                        }
                    }
                }
                initSize();
                xieHouYuAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(Exception e) {

            }
        });
//        List<BaseModel> baseModels = MyDataBase.getInstence().loadAllFromDB(new Motto());
//        if (baseModels != null && baseModels.size() >0){
//            for (BaseModel baseModel:baseModels){
//                if (baseModel instanceof Motto){
//                    mottos.add((Motto)baseModel);
//                }
//            }
//        }
//        initSize();
//        xieHouYuAdapter.notifyDataSetChanged();
    }

    private void initQuick(){
        MyDataBase.getInstence().loadAllFromDBAysc(new QuickResult(), new MyDataBase.FindFinish() {
            @Override
            public void success(List<BaseModel> baseModels) {
                Log.i(TAG, "success: "+baseModels.size());
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

            @Override
            public void error(Exception e) {

            }
        });
//        List<BaseModel> baseModels = MyDataBase.getInstence().loadAllFromDB(new QuickResult());
//        if (baseModels != null && baseModels.size() >0){
//            for (BaseModel baseModel:baseModels){
//                if (baseModel instanceof QuickResult){
//                    quickResults.add((QuickResult)baseModel);
//                }
//            }
//        }
//        initSize();
//        xieHouYuAdapter.notifyDataSetChanged();
    }

    private void initXiehou(){
        MyDataBase.getInstence().loadAllFromDBAysc(new XieHouYu(), new MyDataBase.FindFinish() {
            @Override
            public void success(List<BaseModel> baseModels) {
                Log.i(TAG, "success: "+baseModels.size());
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

            @Override
            public void error(Exception e) {

            }
        });
//        List<BaseModel> baseModels = MyDataBase.getInstence().loadAllFromDB(new XieHouYu());
//        if (baseModels != null && baseModels.size() >0){
//            for (BaseModel baseModel:baseModels){
//                if (baseModel instanceof XieHouYu){
//                    xieHouYuList.add((XieHouYu)baseModel);
//                }
//            }
//        }
//        initSize();
//        xieHouYuAdapter.notifyDataSetChanged();
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
            if (type == 1) {
                ((XiehouYuItem) convertView).setData(xieHouYuList.get(position));
            }else if (type ==2){
                ((XiehouYuItem) convertView).setData(quickResults.get(position));
            }else if (type == 3){
                ((XiehouYuItem) convertView).setData(mottos.get(position));
            }else if (type == 4){
                ((XiehouYuItem) convertView).setData(tonggues.get(position));
            }
            return convertView;
        }
    }
}
