package tech.tedybearblog.moviecatalogue4.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tech.tedybearblog.moviecatalogue4.Fragment.FavoriteFragment;
import tech.tedybearblog.moviecatalogue4.Fragment.MovieFragment;
import tech.tedybearblog.moviecatalogue4.Fragment.TvShowFragment;
import tech.tedybearblog.moviecatalogue4.R;

public class MainActivity extends AppCompatActivity {

    private final String STATE_TITLE = "state_string";
    public String title = "Movie";
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;

            switch (item.getItemId()) {
                case R.id.navigation_movie:
                    title = getResources().getString(R.string.movie);
                    setActionBarTitle(title);
                    fragment = new MovieFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName()).commit();
                    return true;
                case R.id.navigation_tv_show:
                    title = getResources().getString(R.string.tv_show);
                    setActionBarTitle(title);
                    fragment = new TvShowFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName()).commit();
                    return true;
                case R.id.navigation_favorite:
                    title = getResources().getString(R.string.favorite);
                    setActionBarTitle(title);
                    fragment = new FavoriteFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_setting) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_TITLE, title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            setActionBarTitle(title);
            navView.setSelectedItemId(R.id.navigation_movie);
        } else {
            title = savedInstanceState.getString(STATE_TITLE);
            setActionBarTitle(title);
        }
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
