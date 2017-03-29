package com.randerath.johannes.msoplaner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.Stack;

public class NewLessonActivity extends AppCompatActivity {

    private LinearLayout container;
    private String[] aDropdownDays;
    private String[] aDropdownHours;
    private Logic logic;
    private String[] names;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lesson);
        container = (LinearLayout) findViewById(R.id.newLessonContainer);

        //Wochentage für Dropdown
        this.aDropdownHours = new String[] {
                "1", "2", "3", "4", "5", "6","7","8","9","10","11","12"
        };
        Spinner s = (Spinner) findViewById(R.id.sLessonsTime);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, aDropdownHours);
        s.setAdapter(adapter);


//        // Fächer für Dropdown
//        Subject[] subjects = logic.getSubjects().toArray(new Subject[logic.getSubjects().size()]);
//        names = new String[subjects.length];
//        for(int i = 0; i <subjects.length; i++){
//            names[i] = subjects[i].getName();
//        }
//
//        Spinner t = (Spinner) findViewById(R.id.sLessonSubjects);
//        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(NewLessonActivity.this,
//                android.R.layout.simple_spinner_item, names);
//        t.setAdapter(adapter4);


        //Stunden für Dropdown
        this.aDropdownDays = new String[] {
                "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"
        };
        Spinner p = (Spinner) findViewById(R.id.sLessonsDay);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, aDropdownDays);
        p.setAdapter(adapter2);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.savemenue, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.activity_new_subject:
                break;
        }
        return super.onOptionsItemSelected(item); //To change body of generated methods, choose Tools | Templates.
    }

    public void addAnotherLessonButton(View view) {
        LinearLayout newContainer = new LinearLayout(NewLessonActivity.this);
        newContainer.setOrientation(LinearLayout.HORIZONTAL);
        newContainer.setId(View.generateViewId());

    }

}
