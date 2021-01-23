package com.android.gaspricerd.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.gaspricerd.R;
import com.android.gaspricerd.map.MapFragment;
import com.android.gaspricerd.prices.PriceFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Container main activity class.
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

        // Set initial fragment.
        replaceFragment(new PriceFragment());

        initBottomNavigationView();
    }

    /**
     * Initializer the bottom navigation fragment.
     */
    private void initBottomNavigationView() {
        BottomNavigationView.OnNavigationItemSelectedListener onItemSelected = item -> {
            switch (item.getItemId()) {
                case R.id.nv_price:
                    toolbar.setTitle(getString(R.string.price));
                    replaceFragment(PriceFragment.newInstance());
                    return true;

                case R.id.nv_gas_station:
                    toolbar.setTitle(getString(R.string.gas_station));
                    replaceFragment( new MapFragment());
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
