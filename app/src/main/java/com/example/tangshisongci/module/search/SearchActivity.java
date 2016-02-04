package com.example.tangshisongci.module.search;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by bigwen on 2016/2/1.
 */
public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SearchView(this));
    }
}
