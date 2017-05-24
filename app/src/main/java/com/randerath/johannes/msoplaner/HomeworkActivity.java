package com.randerath.johannes.msoplaner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import layout.ExamFragment;
import layout.TaskFragment;

/**
 * Displays homework (as HomeworkFragments) in a list.
 */

public class HomeworkActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    LinearLayout homeworkContainer;
    Logic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get Applcation logic as Intent argument
        Gson gson = new Gson();
        logic = gson.fromJson(getIntent().getStringExtra("logic"), Logic.class);

        //Initialize UI elements
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeworkActivity.this, NewTaskActivity.class);
                Gson gson = new Gson();
                intent.putExtra("logic", gson.toJson(logic));
                intent.putExtra("lastActivity", "homework");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
        MenuItem item = menu.findItem(R.id.nav_homework);
        item.setChecked(true);

        homeworkContainer = (LinearLayout) findViewById(R.id.homeworkContainer);

        //show homework as Fragments
        refreshFragments();
    }

    /**
     * Use back button to close navigation drawer
     */

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
        getMenuInflater().inflate(R.menu.homework, menu);
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

    /**
     * Implemts functionality of navigation drawer
     * @param item selected navigation item
     * @return successful?
     */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent = null;

        switch (id) {
            case R.id.nav_exams:
                intent = new Intent(HomeworkActivity.this, ExamsActivity.class);
                break;
            case R.id.nav_main:
                intent = new Intent(HomeworkActivity.this, MainActivity.class);
                break;
            case R.id.nav_timetable:
                intent = new Intent(HomeworkActivity.this, TimeTableActivity.class);
                break;
        }

        if(intent != null) {
            //Pass Application logic as argument and perform Intent
            Gson gson = new Gson();
            String logicString = gson.toJson(logic);
            intent.putExtra("logic", logicString);
            intent.putExtra("lastActivity", "homework");
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out); // use custom animation
        }

        //close navigation drawer, if no Intent was performed (e.g. this Activity was selected)
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Redraw fragments
     */

    private void refreshFragments() {
        homeworkContainer.removeAllViews(); //clear all Fragments

        /*Same process as in DayFragment.
              Creating Fragment,
              converting it to a view using fragment transaction and a container layout
              and adding the container view to the parent.
              Repeat for every homework.
            */
        LinearLayout temp;
        FragmentTransaction ft;
        Task[] tasks = logic.getTasks();

        for(Task task : tasks) {
            temp = new LinearLayout(HomeworkActivity.this);
            temp.setOrientation(LinearLayout.VERTICAL);
            temp.setId(View.generateViewId());
            ft = getSupportFragmentManager().beginTransaction();
            ft.add(temp.getId(), TaskFragment.newInstance(task.getSubject().getName(), task.getDueDate(), task.getDescription()));
            ft.commit();
            homeworkContainer.addView(temp);
        }

    }

}
