package com.nagarro.persistence.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.nagarro.persistence.R;
import com.nagarro.persistence.database.AppDatabase;
import com.nagarro.persistence.databinding.ActivityMainBinding;
import com.nagarro.persistence.entity.VersionInfo;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    public static boolean isFavourite;
    private boolean tabletSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        tabletSize = getResources().getBoolean(R.bool.isTablet);
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.add(R.id.container, new MainFragment(), TAG);
            fragmentTransaction.commit();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        //U can find item set icon and stuff...
        //MenuItem item= menu.findItem(R.id.action_search);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.tab_all:
                //do stuff
                MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(TAG);
                mainFragment.getmPresenter().getAllData(this);
                isFavourite = false;
                if (tabletSize)
                    ((FrameLayout) findViewById(R.id.container2)).removeAllViews();
                break;
            case R.id.tab_favourite:
                //do stuff
                mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(TAG);
                mainFragment.getmPresenter().getFavouriteData(this);
                isFavourite = true;
                if (tabletSize)
                    ((FrameLayout) findViewById(R.id.container2)).removeAllViews();
                break;

            case R.id.tab_distr:
                //do stuff
                mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(TAG);
                mainFragment.getmPresenter().getLessDestribution(this);
                isFavourite = false;
                if (tabletSize)
                    ((FrameLayout) findViewById(R.id.container2)).removeAllViews();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    public void showDetail(VersionInfo versionInfo) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment newDetailFragment = null;
        Bundle data = new Bundle();//Use bundle to pass data
        data.putParcelable("versionInfo", versionInfo);
        newDetailFragment = new DetailFragment();
        newDetailFragment.setArguments(data);
        transaction
                .replace(R.id.container2, newDetailFragment, DetailFragment.TAG)
                .commit();
    }

}
