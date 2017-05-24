package com.randerath.johannes.msoplaner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * Activity as GUI for creating new Subject objects.
 */

public class NewSubject extends AppCompatActivity {

    private Logic logic;
    private Gson gson;

    private Button cancel;
    private Button done;
    private EditText name;
    private EditText abbreviation;
    private EditText place;
    private EditText teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_subject);

        //get logic from Intent
        gson = new Gson();
        String ls = getIntent().getStringExtra("logic");
        logic = gson.fromJson(ls, Logic.class);

        //Initialize UI elements
        cancel = (Button) findViewById(R.id.cancel);
        done = (Button) findViewById(R.id.done);
        done.setEnabled(true);
        done.setOnClickListener(onButtonClick());
        cancel.setOnClickListener(onButtonClick());

        name = (EditText) findViewById(R.id.name);
        abbreviation = (EditText) findViewById(R.id.abbreviation);
        place = (EditText) findViewById(R.id.place);
        teacher = (EditText) findViewById(R.id.teacher);

    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view == done) {
                    try {
                        //Save subject and intent back
                        logic.addSubject(name.getText().toString(), abbreviation.getText().toString(), place.getText().toString(), teacher.getText().toString());
                        Toast.makeText(NewSubject.this, R.string.subjectSavedMessage, Toast.LENGTH_SHORT).show();
                        back();
                    }catch (IllegalArgumentException ex) {
                        //Do not intent back if subject exists
                        Toast.makeText(NewSubject.this, R.string.subjectExistingMessage, Toast.LENGTH_SHORT).show();
                        Log.e("User input error", "Tried to add existing subject");
                        name.setText("");
                        abbreviation.setText("");
                        place.setText("");
                        teacher.setText("");
                    }

                } else /*if (view == cancel)*/{
                    //intent back without saving
                    String ls = getIntent().getStringExtra("logic");
                    logic = gson.fromJson(ls, Logic.class);
                    Toast.makeText(NewSubject.this, R.string.notSavedMessage, Toast.LENGTH_SHORT).show();
                    back();
                }
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


    private void back() {
        //Intent back to last Activity
        Intent intent = new Intent();
        String logicString = gson.toJson(logic);
        intent.putExtra("logic", logicString);
        intent.putExtra("lastActivity", "newSubject");
        String lastActivityString = getIntent().getStringExtra("lastActivity");
        switch(lastActivityString){
            case "main":
                intent.setClass(this, MainActivity.class);
                break;
            case "exams":
                intent.setClass(this, ExamsActivity.class);
                break;
            case "homework":
                intent.setClass(this, HomeworkActivity.class);
                break;
            case "timetable":
                intent.setClass(this, TimeTableActivity.class);
                break;
            default:
                intent.setClass(this, MainActivity.class);
                break;
        }
        startActivity(intent);

    }



}
