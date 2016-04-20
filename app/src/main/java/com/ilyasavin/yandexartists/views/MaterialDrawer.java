package com.ilyasavin.yandexartists.views;

import android.app.Activity;
import android.view.View;

import com.ilyasavin.yandexartists.BaseActivity;
import com.ilyasavin.yandexartists.R;
import com.ilyasavin.yandexartists.fragments.FavoritesFragment;
import com.ilyasavin.yandexartists.navigator.FragmentNavigator;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;


public class MaterialDrawer extends BaseActivity {

    public void initDrawer(android.support.v7.widget.Toolbar toolbar, final Activity activity) {

        String developerName = activity.getString(R.string.developer_name);

        PrimaryDrawerItem itemAllArtists= new PrimaryDrawerItem().withName(R.string.top_artists).withIcon(R.drawable.ic_music_circle_black_24dp);
        PrimaryDrawerItem itemFavorites = new PrimaryDrawerItem().withName(R.string.favorite_artists).withIcon(R.drawable.ic_star_black_24dp);
        PrimaryDrawerItem itemHome = new PrimaryDrawerItem().withName(R.string.about_developer).withIcon(R.drawable.account_circle);

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
                        itemAllArtists,
                        itemFavorites,
                        new DividerDrawerItem(),
                        itemHome


                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {

                            case 1:
                                FragmentNavigator.removeFavoriteFragment(activity,new FavoritesFragment());
                                break;
                            case 2:
                                FragmentNavigator.showFavoriteFragment(activity,new FavoritesFragment());
                                break;

                        }
                        return false;
                    }
                })
                .build();
    }

}
