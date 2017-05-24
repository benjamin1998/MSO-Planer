package com.randerath.johannes.msoplaner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Stack;

/**
 * Activity for the user to be able to modify specific lessons.
 * Accessed by performing long press on LessonFragment.
 */

public class EditLessonActivity extends AppCompatActivity {

    Button save;
    Button cancel;
    Button delete;
    Spinner subjectSpinner;
    Spinner timeSpinner;

    int dayIndex;
    int lessonIndex;

    Day day;
    Lesson[] lessons;
    Lesson lesson;
    Logic logic;
    Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lesson);

        gson = new Gson();
        String logicS = getIntent().getStringExtra("logic");
        logic = gson.fromJson(logicS, Logic.class); //get Application logic from Intent

        //Vector(day, lesson) to identify modified lesson
        dayIndex = getIntent().getIntExtra("dayIndex", 0);
        lessonIndex = getIntent().getIntExtra("lessonIndex", 0);

        //get actual lesson to modify
        day = logic.getDay(dayIndex);
        lessons = day.getLessons();
        lesson = lessons[lessonIndex];

        //Initializing UI elements
        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);
        delete = (Button) findViewById(R.id.delete);

        save.setOnClickListener(listener());
        cancel.setOnClickListener(listener());
        delete.setOnClickListener(listener());

        subjectSpinner = (Spinner) findViewById(R.id.subject);
        timeSpinner = (Spinner) findViewById(R.id.time);

        Stack<Subject> subjects = logic.getSubjects();
        String[] subjectNames = new String[subjects.size()];
        for(int i = 0; i < subjects.size(); i++) {
            subjectNames[i] = subjects.get(i).getName();
        }

        subjectSpinner.setAdapter(new ArrayAdapter<String>(EditLessonActivity.this, android.R.layout.simple_spinner_dropdown_item, subjectNames));
        timeSpinner.setAdapter(new ArrayAdapter<String>(EditLessonActivity.this, android.R.layout.simple_spinner_dropdown_item, new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));

        subjectSpinner.setSelection(Arrays.asList(subjectNames).indexOf(lesson.getSubject().getName()), false);
        timeSpinner.setSelection(lesson.getTime()-1, false);
    }

    /**
     * Handles actions for all UI Buttons.
     */
    private View.OnClickListener listener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditLessonActivity.this, TimeTableActivity.class);
                if(v == save) { //Save button pressed
                    if(!subjectSpinner.getSelectedItem().toString().equals(lesson.getSubject().getName())) { //if subject changed
                        lesson.setSbjct(logic.findSubject(subjectSpinner.getSelectedItem().toString())); //change subject
                    }
                    int time = Integer.parseInt(timeSpinner.getSelectedItem().toString());
                    if(time != lesson.getTime()) { //if time changed
                        boolean existing = false;
                        for (Lesson i : lessons) { //test if time is used
                            if(i.getTime() != time) {
                                existing = true;
                                Toast.makeText(EditLessonActivity.this, R.string.lessonExistingMessage, Toast.LENGTH_SHORT).show();
                            }
                        }
                        if(!existing) {
                            lesson.setTime(Integer.parseInt(timeSpinner.getSelectedItem().toString())); //change time
                            day.bubbleSortLessonsByTime(); //resort lessons
                        }
                    }
                }else if(v == delete) {
                    day.removeLesson(lessonIndex);
                    Toast.makeText(EditLessonActivity.this, R.string.lessonDeletedMessage, Toast.LENGTH_SHORT).show();
                } //else cancel
                intent.putExtra("lastActivity", "editLesson");
                intent.putExtra("logic", new Gson().toJson(logic));
                startActivity(intent);
            }
        };
    }
}