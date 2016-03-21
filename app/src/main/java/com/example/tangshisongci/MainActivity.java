package com.example.tangshisongci;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.tangshisongci.model.ChinaSong;
import com.example.tangshisongci.model.base.BaseModel;
import com.example.tangshisongci.model.base.MyDataBase;
import com.example.tangshisongci.module.kit.KitView;
import com.example.tangshisongci.module.main.CustomActionBar;
import com.example.tangshisongci.module.main.DirectoryView;
import com.example.tangshisongci.module.main.MainTab;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getName();
    private CustomActionBar customActionBar;
    private MainTab mainTab;
    private ViewPager viewPager;
    private DirectoryView tangshiView;
    private TabPagerAdapter tabPagerAdapter;
    private DirectoryView songciView;
    private DirectoryView yuanquView;
    private KitView kitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customActionBar = (CustomActionBar) findViewById(R.id.custom_action_bar);
        mainTab = (MainTab) findViewById(R.id.main_tab);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabPagerAdapter = new TabPagerAdapter();
        tangshiView = new DirectoryView(this, 1);
        songciView = new DirectoryView(this, 2);
        yuanquView = new DirectoryView(this, 3);
        kitView = new KitView(this);

        viewPager.setAdapter(tabPagerAdapter);
        initPager();
        MyDataBase myDataBase = MyDataBase.getInstence();
        List<BaseModel> myModelList = myDataBase.loadFromDB(
                "select * from " + new ChinaSong().getTableName() + " where parentID = ? ",
                new String[]{"3573"},
                new ChinaSong());
        Log.i(TAG, "onCreate " + myModelList.size() + "  " + myModelList.get(0).getTableName());
        if (myModelList.size() != 0 && myModelList.get(0) instanceof ChinaSong) {
            ChinaSong chinaSong = (ChinaSong) myModelList.get(0);
            Log.i(TAG, "onCreate " + chinaSong.getDescription());
        }
    }

    private void initPager() {
        mainTab.select(0);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mainTab.select(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mainTab.setOnTouch(new MainTab.OnTouch() {
            @Override
            public void click(int position) {
                viewPager.setCurrentItem(position);
            }
        });
    }

    private class TabPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (position == 0) {
                container.addView(tangshiView);
                return tangshiView;
            } else if (position == 1) {
                container.addView(songciView);
                return songciView;
            } else if (position == 2) {
                container.addView(yuanquView);
                return yuanquView;
            } else {
                container.addView(kitView);
                return kitView;
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
