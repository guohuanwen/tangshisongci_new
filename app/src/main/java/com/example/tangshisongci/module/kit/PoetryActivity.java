package com.example.tangshisongci.module.kit;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.tangshisongci.R;
import com.example.tangshisongci.bace.BaceActivity;
import com.example.tangshisongci.module.main.CustomActionBar;
import com.example.tangshisongci.module.main.DirectoryView;

/**
 * Created by bigwen on 2016/3/21.
 */
public class PoetryActivity extends BaceActivity {

    private DirectoryView directoryView;
    private FrameLayout frameLayout;
    private CustomActionBar customActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        directoryView = new DirectoryView(this, 4);
        setContentView(R.layout.activity_poetry);
        customActionBar = (CustomActionBar) findViewById(R.id.activity_cab);
        customActionBar.setTitle("古体诗");
        frameLayout = (FrameLayout) findViewById(R.id.add_view);
        frameLayout.addView(directoryView);

    }
}
