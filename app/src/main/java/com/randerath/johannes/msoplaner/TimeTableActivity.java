package com.randerath.johannes.msoplaner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;

public class TimeTableActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionMenu fam;
    private FloatingActionButton fabEdit, fabAdd;
    private Logic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        String logicString = getIntent().getExtras().getString("logic");
        Gson gson = new Gson();
        logic = gson.fromJson(logicString, Logic.class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().findItem(R.id.nav_timetable).setChecked(true);

        logic.addSubject("Mathe", "Mathe", "R135", "Lecture", "Dohrn");
        logic.getDay(0).addLesson(logic.getSubjects().peek(), 1);
        logic.getDay(0).addLesson(logic.getSubjects().peek(), 2);
        logic.addSubject("SoWi", "SoWi", "R035", "Lecture", "Hammes");
        logic.getDay(0).addLesson(logic.getSubjects().peek(), 3);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

        DayFragmentPagerAdapater adapter = new DayFragmentPagerAdapater(getSupportFragmentManager(), TimeTableActivity.this, logic);

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        //Costum buttons
        fabAdd = (FloatingActionButton) findViewById(R.id.fab2);
        fabEdit = (FloatingActionButton) findViewById(R.id.fab1);
        fam = (FloatingActionMenu) findViewById(R.id.fab_menu);


        //handling each floating action button clicked
        fabEdit.setOnClickListener(onButtonClick());
        fabAdd.setOnClickListener(onButtonClick());


    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (view == fabAdd) {
                    intent = new Intent(TimeTableActivity.this, NewLessonActivity.class);
                } else {
                    intent = new Intent(TimeTableActivity.this, NewSubject.class);
                }
                Gson gson = new Gson();
                intent.putExtra("logic", gson.toJson(logic));
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                fam.close(true);
            }
        };
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(fam.isOpened()) {
            fam.close(true);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.time_table, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent = null;

        switch (id) {
            case R.id.nav_exams:
                intent = new Intent(TimeTableActivity.this, ExamsActivity.class);
                break;
            case R.id.nav_homework:
                intent = new Intent(TimeTableActivity.this, HomeworkActivity.class);
                break;
            case R.id.nav_main:
                intent = new Intent(TimeTableActivity.this, MainActivity.class);
                break;
        }

        if(intent != null) {
            Gson gson = new Gson();
            String logicString = gson.toJson(logic);
            intent.putExtra("logic", logicString);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}