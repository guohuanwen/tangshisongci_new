package com.example.tangshisongci.module.songci;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by bigwen on 2016/1/30.
 */
public class SongciActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        if (id == -1) {
            return;
        }
        setContentView(new SongciDetailView(this, id));

    }
}
