package com.example.tangshisongci.module.tangshi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by bigwen on 2016/1/29.
 */
public class TangshiDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int id = intent.getIntExtra("parentID", -1);
        if (id == -1) {
            return;
        }
        setContentView(new TangshiDetailView(this,id));
    }
}
