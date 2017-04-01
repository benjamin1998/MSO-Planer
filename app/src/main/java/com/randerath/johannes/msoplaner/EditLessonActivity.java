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

public class EditLessonActivity extends AppCompatActivity {

    Button save;
    Button cancel;
    Button delete;
    Spinner subjectSpinner;
    Spinner timeSpinner;

    int dayIndex;
    int lessonIndex;

    Day day;
    Lesson lesson;
    Logic logic;
    Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lesson);

        gson = new Gson();
        String logicS = getIntent().getStringExtra("logic");
        logic = gson.fromJson(logicS, Logic.class);

        dayIndex = getIntent().getIntExtra("dayIndex", 0);
        lessonIndex = getIntent().getIntExtra("lessonIndex", 0);

        day = logic.getDay(dayIndex);
        lesson = day.getLessons()[lessonIndex];

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

    private View.OnClickListener listener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditLessonActivity.this, TimeTableActivity.class);
                if(v == save) {
                    if(!subjectSpinner.getSelectedItem().toString().equals(lesson.getSubject().getName())) {
                        lesson.setSbjct(logic.findSubject(subjectSpinner.getSelectedItem().toString()));
                        intent.putExtra("refresh", true);
                    }
                    if(!timeSpinner.getSelectedItem().toString().equals(String.valueOf(lesson.getTime()))) {
                        lesson.setTime(Integer.parseInt(timeSpinner.getSelectedItem().toString()));
                    }
                }else if(v == delete) {
                    day.removeLesson(lessonIndex);
                    Toast.makeText(EditLessonActivity.this, R.string.lessonDeletedMessage, Toast.LENGTH_SHORT).show();
                } //else cancel
                intent.putExtra("logic", new Gson().toJson(logic));
                startActivity(intent);
            }
        };
    }
}