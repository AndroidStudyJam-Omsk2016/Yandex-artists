package com.ilyasavin.yandexartists.views;

import android.app.Activity;
import android.view.View;

import com.ilyasavin.yandexartists.BaseActivity;
import com.ilyasavin.yandexartists.R;
import com.ilyasavin.yandexartists.fragments.FavoritesFragment;
import com.ilyasavin.yandexartists.navigator.ActivityNavigator;
import com.ilyasavin.yandexartists.navigator.FragmentNavigator;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


/**
 * A implementation of Navigaton Drawer using https://github.com/mikepenz/MaterialDrawer library.
 */
public class MaterialDrawer extends BaseActivity {

    public void initDrawer(android.support.v7.widget.Toolbar toolbar, final Activity activity) {

        PrimaryDrawerItem itemAllArtists = new PrimaryDrawerItem().withName(R.string.top_artists).withIcon(R.drawable.ic_music_circle_black_24dp);
        PrimaryDrawerItem itemFavorites = new PrimaryDrawerItem().withName(R.string.favorite_artists).withIcon(R.drawable.ic_star_black_24dp);
        PrimaryDrawerItem itemHome = new PrimaryDrawerItem().withName(R.string.about_developer).withIcon(R.drawable.ic_account_card_details_black_24dp);


        AccountHeader headerResult;


        headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.drawer_background)
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
                                FragmentNavigator.removeFavoriteFragment(activity, new FavoritesFragment());
                                break;
                            case 2:
                                FragmentNavigator.showFavoriteFragment(activity, new FavoritesFragment());
                                break;
                            case 4:
                                ActivityNavigator.startDeveloperActivity(activity);
                                break;


                        }
                        return false;
                    }
                })
                .build();
    }

}
