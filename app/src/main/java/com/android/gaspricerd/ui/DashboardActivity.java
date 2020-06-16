package com.android.gaspricerd.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.android.gaspricerd.R;
import com.android.gaspricerd.ui.fragments.MapFragment;
import com.android.gaspricerd.ui.fragments.PriceFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        Fragment fragment = new PriceFragment();
        replaceFragment(fragment);

        initBottomNavigationView();

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

    /**
     * initializer the bottom navigation fragment.
     */
    private void initBottomNavigationView() {
        BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelected =
                item -> {
                    Fragment fragment;
                    switch (item.getItemId()) {
                        case R.id.nv_price:
                            fragment = new PriceFragment();
                            DashboardActivity.this.replaceFragment(fragment);

                            toolbar.setTitle(DashboardActivity.this.getString(R.string.price));
                            return true;

                        case R.id.nv_gas_station:
                            fragment = new MapFragment();
                            DashboardActivity.this.replaceFragment(fragment);

                            toolbar.setTitle(DashboardActivity.this.getString(R.string.gas_station));
                            return true;
                    }
                    return false;
                };

        BottomNavigationView navigationView = findViewById(R.id.bn_dashboard);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelected);
    }
}
