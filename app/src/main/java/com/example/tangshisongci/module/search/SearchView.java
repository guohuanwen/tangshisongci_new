package com.example.tangshisongci.module.search;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.tangshisongci.R;
import com.example.tangshisongci.model.ChinaTang;
import com.example.tangshisongci.model.Poetry;
import com.example.tangshisongci.model.Songci;
import com.example.tangshisongci.model.base.BaseModel;
import com.example.tangshisongci.model.base.MyDataBase;
import com.example.tangshisongci.model.base.MyDataBaseSong;
import com.example.tangshisongci.module.kit.PoetryDetailActivity;
import com.example.tangshisongci.module.main.DirectoryItem;
import com.example.tangshisongci.module.songci.SongciActivity;
import com.example.tangshisongci.tool.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bigwen on 2016/2/1.
 */
public class SearchView extends LinearLayout {

    private String TAG = SearchView.class.getName();
    private TextView typeTextView;
    private EditText searchText;
    private ImageView search;
    private Context mContext;
    private ListView searchResult;
    private SearchAdapter searchAdapter;
    private List<DirectoryInfo> directoryInfos = new ArrayList<>();
    private int type = 0;//0：诗词名字搜索    1：人名

    public SearchView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.search_view, this);
        typeTextView = (TextView) findViewById(R.id.search_view_text);
        searchText = (EditText) findViewById(R.id.search_view_edit);
        search = (ImageView) findViewById(R.id.search_view_edit_img);
        searchResult = (ListView) findViewById(R.id.search_view_result);
        searchAdapter = new SearchAdapter();
        searchResult.setAdapter(searchAdapter);

        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchT = searchText.getText().toString();
                directoryInfos.clear();
                searchAdapter.notifyDataSetChanged();
                if (searchT.isEmpty()) {
                    return;
                }
                //title
                if (type == 0) {
                    initDateShici(searchT, 0);
                }
                //auther
                if (type == 1) {
                    initDateShici(searchT, 1);
                }
            }
        });

        typeTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });
    }

    private void initDateShici(String searchText, int type) {
        String sqlTangShi = "";
        String sqlSongci = "";
        String sTang = "";
        String sSong = "";
        String sPoetry = "";
        searchText = "%"+ searchText + "%";
        //唐诗宋词元曲诗名
        if (type == 0) {
            sqlTangShi = "select * from " + new ChinaTang().getTableName() +
                    " where title like " + " '%" + searchText + "%'";
            sqlSongci = "select * from " + new Songci().getTableName() +
                    " where title like " + " '%" + searchText + "%'";
            sTang = "select * from "+new ChinaTang().getTableName()+" where title like ? ";
            sSong = "select * from "+new Songci().getTableName()+" where title like ? ";
            sPoetry = "select * from "+new Poetry().getTableName() + " where Title like ? ";
        }
        if (type == 1) {
            sqlTangShi = "select * from " + new ChinaTang().getTableName() +
                    " where author like " + " '%" + searchText + "%'";
            sqlSongci = "select * from " + new Songci().getTableName() +
                    " where auth like " + " '%" + searchText + "%'";
            sTang = "select * from "+new ChinaTang().getTableName()+" where author like ? ";
            sSong = "select * from "+new Songci().getTableName()+" where auth like ? ";
            sPoetry = "select * from "+ new Poetry().getTableName() + " where Author like ? ";
        }
        if (type == 2) {

        }
//        List<BaseModel> baseModels = MyDataBase.getInstence().loadFromDB(sTang, new String[]{searchText}, new ChinaTang());
//        Log.i(TAG, "initDateShici: "+baseModels.size());
//        if (baseModels != null && baseModels.size() > 0) {
//            for (BaseModel baseModel : baseModels) {
//                DirectoryInfo directoryInfo = new DirectoryInfo();
//                ChinaTang chinaTang = (ChinaTang) baseModel;
//                directoryInfo.setAuther(chinaTang.getAuthor());
//                directoryInfo.setName(chinaTang.getTitle());
//                directoryInfo.setType(3);
//                directoryInfo.setId(chinaTang.getParentID());
//                directoryInfos.add(directoryInfo);
//                searchAdapter.notifyDataSetChanged();
//            }
//        }

        List<BaseModel> baseModelss = MyDataBaseSong.getInstence().loadFromDB(sSong, new String[]{searchText}, new Songci());
        if (baseModelss != null && baseModelss.size() > 0) {
            for (BaseModel baseModel : baseModelss) {
                DirectoryInfo directoryInfo = new DirectoryInfo();
                Songci chinaTang = (Songci) baseModel;
                directoryInfo.setAuther(chinaTang.getAuth());
                directoryInfo.setName(chinaTang.getTitle());
                directoryInfo.setType(2);
                directoryInfo.setId(chinaTang.getId());
                directoryInfos.add(directoryInfo);
                searchAdapter.notifyDataSetChanged();
            }
        }

        List<BaseModel> baseModel3 = MyDataBase.getInstence().loadFromDB(sPoetry, new String[]{searchText}, new Poetry());
        if (baseModel3 != null && baseModel3.size() > 0) {
            for (BaseModel baseModel : baseModel3) {
                DirectoryInfo directoryInfo = new DirectoryInfo();
                Poetry chinaTang = (Poetry) baseModel;
                directoryInfo.setAuther(chinaTang.getAuthor());
                directoryInfo.setName(chinaTang.getTitle());
                directoryInfo.setId(chinaTang.get_id());
                directoryInfo.setType(3);
                directoryInfos.add(directoryInfo);
                searchAdapter.notifyDataSetChanged();
            }
        }

//        MyDataBase.getInstence().loadFromDBAsyn(
//                sqlTangShi, null, new ChinaTang(), new MyDataBase.FindFinish() {
//                    @Override
//                    public void success(List<BaseModel> baseModels) {
//                        Log.i(TAG, "success " + baseModels.size());
//                        for (BaseModel baseModel : baseModels) {
//                            DirectoryInfo directoryInfo = new DirectoryInfo();
//                            ChinaTang chinaTang = (ChinaTang) baseModel;
//                            directoryInfo.setAuther(chinaTang.getAuthor());
//                            directoryInfo.setName(chinaTang.getTitle());
//                            directoryInfo.setType(1);
//                            directoryInfo.setId(chinaTang.getParentID());
//                            directoryInfos.add(directoryInfo);
//                            searchAdapter.notifyDataSetChanged();
//                        }
//
//                    }
//
//                    @Override
//                    public void error(Exception e) {
//                        Log.e(TAG, "error " + e.toString());
//                    }
//                }
//        );
//        MyDataBaseSong.getInstence().loadFromDBAsyn(
//                sqlSongci, null, new Songci(), new MyDataBaseSong.FindFinish() {
//                    @Override
//                    public void success(List<BaseModel> baseModels) {
//                        Log.i(TAG, "success " + baseModels.size());
//                        for (BaseModel baseModel : baseModels) {
//                            DirectoryInfo directoryInfo = new DirectoryInfo();
//                            Songci chinaTang = (Songci) baseModel;
//                            directoryInfo.setAuther(chinaTang.getAuth());
//                            directoryInfo.setName(chinaTang.getTitle());
//                            directoryInfo.setType(2);
//                            directoryInfo.setId(chinaTang.getId());
//                            directoryInfos.add(directoryInfo);
//                            searchAdapter.notifyDataSetChanged();
//                        }
//
//                    }
//
//                    @Override
//                    public void error(Exception e) {
//                        Log.e(TAG, "error " + e.toString());
//                    }
//                }
//        );
    }

    private void showPopup() {
        final PopupWindow popupWindow = new PopupWindow(mContext);
        ListView listView = (ListView) LayoutInflater.from(mContext).inflate(R.layout.search_popup_listview, null);
        final String[] params = new String[]{"诗名", "诗人"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(mContext, R.layout.search_popup_list_item, R.id.search_popup_list_item_text, params);
        listView.setAdapter(arrayAdapter);
        popupWindow.setContentView(listView);
        popupWindow.setWidth(ScreenUtil.dip2px(mContext, 56));
        popupWindow.setHeight(LayoutParams.WRAP_CONTENT);
//        popupWindow.setWidth(LayoutParams.WRAP_CONTENT);
//        popupWindow.setHeight(LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(typeTextView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                type = position;
                typeTextView.setText(params[position]);
                popupWindow.dismiss();
            }
        });
    }

    public class SearchAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return directoryInfos.size();
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
            directoryItem.setView(directoryInfos.get(position).getName(),
                    directoryInfos.get(position).getAuther(), "");
            directoryItem.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (directoryInfos.get(position).getType() == 1) {
//                        INTENT intent = new INTENT(mContext, TangshiDetailActivity.class);
//                        intent.putExtra("parentID", directoryInfos.get(position).getId());
//                        mContext.startActivity(intent);
//                    }
                    if (directoryInfos.get(position).getType() == 2) {
                        Intent intent = new Intent(mContext, SongciActivity.class);
                        intent.putExtra("id", directoryInfos.get(position).getId());
                        mContext.startActivity(intent);
                    }
                    if (directoryInfos.get(position).getType() == 3){
                        Intent intent = new Intent(mContext, PoetryDetailActivity.class);
                        intent.putExtra(PoetryDetailActivity.INTENTTITLE, directoryInfos.get(position).getId());
                        mContext.startActivity(intent);
                    }
                }
            });
            convertView = directoryItem;
            return convertView;
        }
    }


}
