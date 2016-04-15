package com.ilyasavin.yandexartists.views;

import android.app.Activity;
import android.view.View;

import com.ilyasavin.yandexartists.BaseActivity;
import com.ilyasavin.yandexartists.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;


public class MaterialDrawer extends BaseActivity {

    public void initDrawer(android.support.v7.widget.Toolbar toolbar, Activity activity) {

        String developerName = activity.getString(R.string.developer_name);

        PrimaryDrawerItem itemHome = new PrimaryDrawerItem().withName(developerName).withIcon(R.drawable.ic_music_note_black_36dp);

        AccountHeader headerResult;


        headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.color.primary_dark)
                .addProfiles(
                        new ProfileDrawerItem().withName(developerName).withIcon(activity.getResources().getDrawable(R.drawable.ic_account_circle_white_24dp))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {


                        return false;
                    }
                })
                .withSelectionListEnabledForSingleProfile(false)
                .build();

        Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        itemHome


                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {

                            case 1:

                                break;
                            case 2:

                                break;

                        }
                        return false;
                    }
                })
                .build();
    }

}
