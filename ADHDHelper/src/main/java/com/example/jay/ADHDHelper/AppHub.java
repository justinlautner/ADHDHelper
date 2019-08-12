package com.example.jay.ADHDHelper;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class AppHub extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private String title = "Hub";
    //private static final String BACK_STACK_ROOT_TAG = "root_fragment";
    //TODO add loading wheel to prevent ugly

    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_hub);

        //Initialize toolbar & actionbar
        final Toolbar toolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        getSupportActionBar().setTitle(title);
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        //Initialize navigationview & itemselected listener
        final NavigationView navigationView = findViewById(R.id.nav_view);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new ContentHubFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_hub);
        }
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        switch(menuItem.getItemId()){
                            case R.id.nav_hub:
                                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                                        new ContentHubFragment()).commit();
                                title = "Hub";
                                break;
                            case R.id.nav_duties:
                                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                                        new DutiesFragment()).commit();
                                title = "Duties";
                                break;
                            case R.id.nav_calendar:
                                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                                        new CalendarFragment()).commit();
                                title = "Calendar";
                                break;
                            case R.id.nav_groups:
                                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                                        new GroupsFragment()).commit();
                                title = "Groups";
                                break;
                            case R.id.nav_settings:
                                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                                        new SettingsFragment()).commit();
                                title = "Settings";
                                break;
                        }
                        getSupportActionBar().setTitle(title);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.menu_filter:
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //Close drawer on back button pressed
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer((GravityCompat.START));
        }
        else{
            super.onBackPressed();
        }
    }

    public void addDuty(View view){
        /*FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragmentCurrent = new Fragment();
        fragmentTransaction.add(R.id.content_frame, fragmentCurrent);
        fragmentTransaction.commit();*/


        //Start add duty fragment on clicking floating action buttons
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                new AddDutyFragment()).commit();
    }

}
