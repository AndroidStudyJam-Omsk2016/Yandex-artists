package com.ilyasavin.yandexartists;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * The base activity of application is used to set ButterKnife binding and create instance of Realm database
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
