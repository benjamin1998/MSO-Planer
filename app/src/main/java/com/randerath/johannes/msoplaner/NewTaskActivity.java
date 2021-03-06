package com.randerath.johannes.msoplaner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * Activity as GUI for creating new Task objects.
 */

public class NewTaskActivity extends AppCompatActivity {
    private Logic logic;
    private Button cancel;
    private Button done;
    private LinearLayout container;
    private String[] names;
    private Spinner subjectSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        container = (LinearLayout) findViewById(R.id.newLessonContainer);

        //get logic from Intent
        Gson gson = new Gson();
        String s = getIntent().getStringExtra("logic");
        logic = gson.fromJson(getIntent().getStringExtra("logic"), Logic.class);

        //Initialize UI elements

        // get subject names to feed Spinner
        Subject[] subjects = logic.getSubjects().toArray(new Subject[logic.getSubjects().size()]);
        names = new String[subjects.length];
        for(int i = 0; i <subjects.length; i++){
            names[i] = subjects[i].getName();
        }

        subjectSpinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(NewTaskActivity.this,
                android.R.layout.simple_spinner_item, names);
        subjectSpinner.setAdapter(adapter4);

        done = (Button) findViewById(R.id.done);
        cancel = (Button) findViewById(R.id.cancel);

        done.setOnClickListener(onButtonClick());
        cancel.setOnClickListener(onButtonClick());
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

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                //get data from UI inputs
                EditText t = (EditText) findViewById(R.id.tHomeDate);
                String string1 = t.getText().toString();

                EditText z = (EditText) findViewById(R.id.tNote);
                String string2 = z.getText().toString();

                Subject s = logic.findSubject(subjectSpinner.getSelectedItem().toString());

                if (view == done) {
                    //save Task and intent back
                    logic.addTask(s, string1, string2);
                    intent = new Intent(NewTaskActivity.this, HomeworkActivity.class);

                    //Toast User Feedback
                    Context context = getApplicationContext();
                    CharSequence charseq1 = "Dein Entwurf wurde gespeichert.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, charseq1, duration);
                    toast.show();

                } else /*if (view == cancel)*/{
                    //intent back without saving
                    //Toast User Feedback
                    Context context = getApplicationContext();
                    CharSequence charseq2 = "Dein Entwurf wurde verworfen.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, charseq2, duration);
                    toast.show();


                    intent = new Intent(NewTaskActivity.this, HomeworkActivity.class);
                }
                //Pass logic as argument and perform Intent
                Gson gson = new Gson();
                intent.putExtra("logic", gson.toJson(logic));
                intent.putExtra("lastActivity", "newTask");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out); //use custom animation
            }
        };
    }
}
