package tech.tedybearblog.moviecatalogue4.adapter;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import tech.tedybearblog.moviecatalogue4.Fragment.FavoriteMoviesFragment;
import tech.tedybearblog.moviecatalogue4.Fragment.FavoriteTVShowFragment;

public class FavoriteTabAdapter extends FragmentPagerAdapter {


    public FavoriteTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return new FavoriteMoviesFragment();
        }
        return new FavoriteTVShowFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Movies";
        }
        return "TV Show";
    }

    @Override
    public int getCount() {
        return 2;
    }
}
