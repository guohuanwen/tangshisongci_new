package com.example.tangshisongci.module.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.tangshisongci.R;
import com.example.tangshisongci.model.ChinaTang;
import com.example.tangshisongci.model.Songci;
import com.example.tangshisongci.model.base.BaseModel;
import com.example.tangshisongci.model.base.MyDataBase;
import com.example.tangshisongci.model.base.MyDataBaseSong;
import com.example.tangshisongci.module.songci.SongciActivity;
import com.example.tangshisongci.module.tangshi.TangshiDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bigwen on 2016/1/29.
 */
public class DirectoryView extends LinearLayout {
    private String TAG = DirectoryView.class.getName();
    private Context mContext;
    @Nullable
    private ListView tangshiList;
    private TangShiAdapter tangShiAdapter;
    private List<ChinaTang> tangshis = new ArrayList<>();
    private List<Songci> songcis = new ArrayList<>();
    private int type = -1;//type 1 唐诗  2宋词  3元曲

    public DirectoryView(Context context, int type) {
        super(context);
        mContext = context;
        this.type = type;
        init();
    }

    public DirectoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.tangshi_view, this);
        tangshiList = (ListView) findViewById(R.id.tangshi_view_listview);
        tangShiAdapter = new TangShiAdapter();
        tangshiList.setAdapter(tangShiAdapter);

        switch (type) {
            case 1:
                initTangshi();
                break;
            case 2:
                initSongci();
                break;
            case 3:
                initYuanyu();
                break;
        }
    }


    private void initTangshi() {
        List<BaseModel> baseModels = MyDataBase.getInstence().loadAllFromDB(new ChinaTang());
        List<ChinaTang> chinaTangs = new ArrayList<>();
        if (baseModels == null || baseModels.size() == 0) {
            return;
        }
        for (BaseModel baseModel : baseModels) {
            if (baseModel instanceof ChinaTang) {
                chinaTangs.add((ChinaTang) baseModel);
            }
        }
        tangshis.addAll(chinaTangs);
        tangShiAdapter.notifyDataSetChanged();
    }

    private void initSongci() {
        List<BaseModel> baseModels = MyDataBaseSong.getInstence().loadFromDB(
                " select * from " + new Songci().getTableName() + " where type = ? ",
                new String[]{"2"}, new Songci());
        Log.i(TAG, "initSongci 0  ");
        if (baseModels == null || baseModels.size() == 0) {
            return;
        }
        Log.i(TAG, "initSongci 1  " + baseModels.size());
        List<Songci> songci = new ArrayList<>();
        for (BaseModel baseModel : baseModels) {
            if (baseModel instanceof Songci) {
                songci.add((Songci) baseModel);
            }
        }
        songcis.addAll(songci);
        tangShiAdapter.notifyDataSetChanged();
    }

    private void initYuanyu() {
        List<BaseModel> baseModels = MyDataBaseSong.getInstence().loadFromDB(
                " select * from " + new Songci().getTableName() + " where type = ? ",
                new String[]{"3"}, new Songci());
        Log.i(TAG, "initSongci 0  ");
        if (baseModels == null || baseModels.size() == 0) {
            return;
        }
        Log.i(TAG, "initSongci 1  " + baseModels.size());
        List<Songci> songci = new ArrayList<>();
        for (BaseModel baseModel : baseModels) {
            if (baseModel instanceof Songci) {
                songci.add((Songci) baseModel);
            }
        }
        songcis.addAll(songci);
        tangShiAdapter.notifyDataSetChanged();
    }

    private class TangShiAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (type == 1) {
                return tangshis.size();
            } else if (type == 2) {
                return songcis.size();
            }else if(type==3){
                return songcis.size();
            }
            return 0;
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            DirectoryItem directoryItem = null;
            if (convertView == null) {
                directoryItem = new DirectoryItem(mContext);
            } else {
                directoryItem = (DirectoryItem) convertView;
            }
            if (type == 1) {
                Log.i(TAG, "getView "+type);
                directoryItem.setView(tangshis.get(position).getTitle(),
                        tangshis.get(position).getAuthor(),
                        tangshis.get(position).getClassName());
                directoryItem.setOnClickListener(null);
                directoryItem.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, TangshiDetailActivity.class);
                        intent.putExtra("parentID", tangshis.get(position).getParentID());
                        mContext.startActivity(intent);
                    }
                });
            }
            if (type == 2 || type == 3) {
                Log.i(TAG, "getView "+type);
                directoryItem.setView(songcis.get(position).getTitle(),
                        songcis.get(position).getAuth(),
                        "");
                directoryItem.setOnClickListener(null);
                directoryItem.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, SongciActivity.class);
                        intent.putExtra("id", songcis.get(position).getId());
                        mContext.startActivity(intent);
                    }
                });
            }
            convertView = directoryItem;
            return convertView;
        }
    }
}