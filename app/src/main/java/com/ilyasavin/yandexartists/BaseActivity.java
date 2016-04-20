package com.ilyasavin.yandexartists;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by ilyas on 4/15/2016.
 */
public class BaseActivity extends AppCompatActivity {
    public Realm realm;
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
    }

}
