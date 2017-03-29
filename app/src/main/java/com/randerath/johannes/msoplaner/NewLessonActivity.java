package com.randerath.johannes.msoplaner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Stack;

public class NewLessonActivity extends AppCompatActivity {

    private LinearLayout container;
    private String[] aDropdownDays;
    private String[] aDropdownHours;
    private Logic logic;
    private String[] names;
    private Button cancel;
    private Button done;
    private int indexDay;
    private int intLesson;



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

        Gson gson = new Gson();
        logic = gson.fromJson(getIntent().getStringExtra("logic"), Logic.class);

        // Fächer für Dropdown
        Subject[] subjects = logic.getSubjects().toArray(new Subject[logic.getSubjects().size()]);
        names = new String[subjects.length];
        for(int i = 0; i <subjects.length; i++){
            names[i] = subjects[i].getName();
        }

        Spinner t = (Spinner) findViewById(R.id.sLessonSubjects);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(NewLessonActivity.this,
                android.R.layout.simple_spinner_item, names);
        t.setAdapter(adapter4);

        //Stunden für Dropdown
        this.aDropdownDays = new String[] {
                "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"
        };
        Spinner p = (Spinner) findViewById(R.id.sLessonsDay);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, aDropdownDays);
        p.setAdapter(adapter2);


    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                Spinner s = (Spinner) findViewById(R.id.sLessonSubjects);
                String text = s.getSelectedItem().toString();

                logic.getSubjects().toArray(cache);

                for (logic){
                    logic.getSubjects().toArray(

                }

                Spinner t = (Spinner) findViewById(R.id.sLessonsDay);
                String text1 = t.getSelectedItem().toString();

                if(text1 == "Montag"){
                    indexDay = 0;
                }
                else if(text1 == "Dienstag"){
                    indexDay = 1;
                }
                else if(text1 == "Mittwoch"){
                    indexDay =2;
                }
                else if(text1 == "Donnerstag"){
                    indexDay =3;
                }
                else if(text1 == "Freitag"){
                    indexDay =4;
                }
                else if(text1 == "Samstag"){
                    indexDay =5;
                }

                Spinner u = (Spinner) findViewById(R.id.sLessonsTime);
                String text2 = u.getSelectedItem().toString();

                intLesson = Integer.parseInt(text2);


                if (view == done) {
                    logic.getDay(indexDay).addLesson( ,intLesson);
                    intent = new Intent(NewLessonActivity.this, TimeTableActivity.class);

                    //Toast User Feedback
                    Context context = getApplicationContext();
                    CharSequence text = "Dein Entwurf wurde gespeichert.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                } else if (view == cancel){

                    //Toast User Feedback
                    Context context = getApplicationContext();
                    CharSequence text = "Dein Entwurf wurde verworfen.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();


                    intent = new Intent(NewLessonActivity.this, TimeTableActivity.class);
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
