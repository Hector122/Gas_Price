package com.android.gaspricerd.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.gaspricerd.R;
import com.android.gaspricerd.reposiroty.RepositoryImp;
import com.android.gaspricerd.ui.fragments.MapFragment;
import com.android.gaspricerd.ui.fragments.PriceFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Container activity class.
 */
public class DashboardActivity extends AppCompatActivity {
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_container);

        toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle(getString(R.string.app_name));
        }

        //
        RepositoryImp repositoryImp = new RepositoryImp();
        repositoryImp.getLastWeekJSON();

        // Set initial fragment.
        Fragment fragment = new PriceFragment();
        replaceFragment(fragment);

        initBottomNavigationView();
    }

    /**
     * Initializer the bottom navigation fragment.
     */
    private void initBottomNavigationView() {
        BottomNavigationView.OnNavigationItemSelectedListener onItemSelected = item -> {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nv_price:
                    fragment = new PriceFragment();
                    replaceFragment(fragment);

                    toolbar.setTitle(DashboardActivity.this.getString(R.string.price));
                    return true;

                case R.id.nv_gas_station:
                    fragment = new MapFragment();
                    replaceFragment(fragment);

                    toolbar.setTitle(DashboardActivity.this.getString(R.string.gas_station));
                    return true;
            }
            return false;
        };

        BottomNavigationView navigationView = findViewById(R.id.bn_dashboard);
        navigationView.setOnNavigationItemSelectedListener(onItemSelected);
    }

    /**
     * Make the fragment transaction.
     *
     * @param fragment fragment to replace the current fragment.
     */
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
