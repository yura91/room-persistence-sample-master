
package com.nagarro.persistence.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.nagarro.persistence.R;
import com.nagarro.persistence.database.AppDatabase;
import com.nagarro.persistence.databinding.ActivityDetailBinding;
import com.nagarro.persistence.entity.VersionInfo;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        Intent intent = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        VersionInfo versionInfo = intent.getParcelableExtra("versionInfo");
        if (savedInstanceState == null) {
            DetailFragment detailFragment = new DetailFragment();
            Bundle data = new Bundle();//Use bundle to pass data
            data.putParcelable("versionInfo", versionInfo);
            detailFragment.setArguments(data);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.add(R.id.container, detailFragment, TAG);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}
