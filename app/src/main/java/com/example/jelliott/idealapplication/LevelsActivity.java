package com.example.jelliott.idealapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by jelliott on 10/10/2016.
 */

public class LevelsActivity extends AppCompatActivity {
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private MainActivity mainActivity = new MainActivity(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels_layout);
        initInstancesDrawer();
        GridView levelGridView = (GridView) findViewById(R.id.level_gridView);
        levelGridView.setAdapter(new LevelAdapter(this));
        levelGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //init();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getBaseContext(), "You click on Level " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initInstancesDrawer() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout androidDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_design_support_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, androidDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        androidDrawerLayout.addDrawerListener(actionBarDrawerToggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.mainGame_menu_Item:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.level_menu_Item:
                        Toast.makeText(getApplicationContext(), "You are already on the Levels Activity", Toast.LENGTH_SHORT).show();
                        androidDrawerLayout.closeDrawers();
                        break;
                    case R.id.settings_menu_Item:
                        Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                        androidDrawerLayout.closeDrawers();
                        break;
                    case R.id.about_menu_Item:
                        Toast.makeText(getApplicationContext(), "About IDEAL", Toast.LENGTH_SHORT).show();
                        androidDrawerLayout.closeDrawers();
                        break;

                }
                return true;
            }
        });

        assert getSupportActionBar() != null;//Make sure the supportBar return not null
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.levels_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return super.onOptionsItemSelected(item);
        }
// Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            mainActivity.init();
        }
        return true;
    }
}
