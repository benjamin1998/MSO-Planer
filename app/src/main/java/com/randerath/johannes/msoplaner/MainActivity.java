package com.randerath.johannes.msoplaner;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Logic logic;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize UI elements
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

        //Read Application logic from file
        gson = new Gson();
        File file = new File(getFilesDir(), "logic");
        if(getIntent().getExtras() == null) { //if App just started
            if(file.exists()) { //if logic saved and file found
                String s = readStringFromFile();
                logic = gson.fromJson(s, Logic.class);
            }else { // logic not found. Create logic and add some example data
                Log.i("logic not found", "Creating logic");
                logic = new Logic();
                /*logic.addSubject("SoWi", "sw", "R035", "Hammes");
                logic.addSubject("Englisch", "e", "R708", "Neuburger");
                logic.addSubject("Info", "i", "R323", "Zons");
                for(int i = 0; i < 6; i++) {
                    logic.getDay(i).addLesson(logic.getSubjects().peek(), 1);
                    logic.getDay(i).addLesson(logic.getSubjects().peek(), 2);
                }
                logic.addSubject("Latein", "l", "R025", "von Berg");
                logic.addTask(logic.getSubjects().peek(), "30/03/2017", "Übersetzung bis Zeile 10");
                logic.addSubject("Deutsch", "d", "R135", "Heise");
                logic.addTask(logic.getSubjects().peek(), "04/04/2017", "Analyse");
                logic.addSubject("Mathe", "m", "R135", "Dohrn");
                logic.addExam("30/03/2017", logic.getSubjects().peek(), "");*/


            }
        }else { //got here from navigation drawer. Get logic from Intent
            String ls = getIntent().getStringExtra("logic");
            logic = gson.fromJson(ls, Logic.class);
        }

    }

    private void writeStringToFile(String s) {
        try {
            new File(getFilesDir(), "logic").delete(); //clear logic file
            //write s to file
            FileOutputStream outputStream = openFileOutput("logic", Context.MODE_PRIVATE);
            outputStream.write(s.getBytes());
            outputStream.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }


    private String readStringFromFile() {
        File f = new File(getFilesDir(), "logic");
        try {
            FileInputStream stream = new FileInputStream(f);
            return IOUtils.toString(stream);
        }catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Close navigation drawer using back button
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

    /**
     * Implements functionality of navigation drawer.
     * @param item selected navigation item
     * @return successful?
     */
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
            //Pass Application logic as argument and perform Intent
            Gson gson = new Gson();
            String logicString = gson.toJson(logic);
            intent.putExtra("logic", logicString);
            intent.putExtra("lastActivity", "main");
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
        //close navigation drawer, if no Intent was performed (e.g. this Activity was selected)
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true; //use custom animation
    }

    //Save logic to file when Application is send to background
    @Override
    public void onTrimMemory(final int level) {
        if(level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            String gsonLogic = gson.toJson(logic);
            writeStringToFile(gsonLogic);
        }
    }

    /**
     * Delete logic file
     * @param view calling button
     */
    public void delete(View view) {
        File file = new File(getFilesDir(), "logic");
        file.delete();
        Toast.makeText(this, "shfj", Toast.LENGTH_SHORT).show();
    }

}
