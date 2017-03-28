package com.randerath.johannes.msoplaner;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Logic logic;
    private Button createSubject;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
        MenuItem item = menu.findItem(R.id.nav_main);
        item.setChecked(true);

        gson = new Gson();


        if(getIntent().getExtras() == null) {
            logic = new Logic();
        }else {
            String ls = getIntent().getStringExtra("logic");
            logic = gson.fromJson(ls, Logic.class);
        }

        logic.addSubject("Deutsch", "D", "R135", "Lecture", "Heise");

//        createSubject = (Button) findViewById(R.id.createSubject);
//
//        LessonView lessonView = (LessonView) findViewById(R.id.lessonView);
//        lessonView.setTeacher("HeiseZonsHerrmanns");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
                intent = new Intent(MainActivity.this, ExamsActivity.class);
                break;
            case R.id.nav_homework:
                intent = new Intent(MainActivity.this, HomeworkActivity.class);
                break;
            case R.id.nav_timetable:
                intent = new Intent(MainActivity.this, TimeTableActivity.class);
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

    public void createSubjectAction(View view) {

        String ls = gson.toJson(logic);

        Intent intent = new Intent(this, NewSubject.class);
        intent.putExtra("logic", ls);
        intent.putExtra("lastActivity", "main");
        startActivity(intent);

    }

    @Override
    public void onTrimMemory(final int level) {
        if(level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            Gson gson = new Gson();
            String gsonLogic = gson.toJson(logic);
            File file = new File(getFilesDir(), "logic");
            try {
                FileOutputStream outputStream = openFileOutput("logic", Context.MODE_PRIVATE);
                outputStream.write(gsonLogic.getBytes());
                outputStream.close();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
            Toast.makeText(this, "Background!", Toast.LENGTH_SHORT).show();
        }
    }

}
