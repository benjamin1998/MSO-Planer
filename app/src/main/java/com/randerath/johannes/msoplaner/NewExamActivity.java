package com.randerath.johannes.msoplaner;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;

/**
 * Activity as GUI for creating new Exam objects.
 */

public class NewExamActivity extends AppCompatActivity {

    private Logic logic;
    private Button cancel;
    private Button done;
    private LinearLayout container;
    private String[] names;
    private Spinner subjectSpinner;
    private Button setDateB;
    private String date;
    private final Calendar cal = Calendar.getInstance();
    private EditText notesET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exam2);
        container = (LinearLayout) findViewById(R.id.newLessonContainer);

        //get logic from intent
        Gson gson = new Gson();
        String s = getIntent().getStringExtra("logic");
        logic = gson.fromJson(getIntent().getStringExtra("logic"), Logic.class);

        //Initialize UI elements
        notesET = (EditText) findViewById(R.id.tNotes);

        // Get subject names to feed Spinner
        Subject[] subjects = logic.getSubjects().toArray(new Subject[logic.getSubjects().size()]);
        names = new String[subjects.length];
        for(int i = 0; i <subjects.length; i++){
            names[i] = subjects[i].getName();
        }

        subjectSpinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(NewExamActivity.this,
                android.R.layout.simple_spinner_item, names);
        subjectSpinner.setAdapter(adapter4);

        done = (Button) findViewById(R.id.done);
        cancel = (Button) findViewById(R.id.cancel);
        setDateB = (Button) findViewById(R.id.setDateB);

        done.setOnClickListener(onButtonClick());
        cancel.setOnClickListener(onButtonClick());
        setDateB.setOnClickListener(onButtonClick());

        date = cal.get(Calendar.DAY_OF_MONTH) + "." + cal.get(Calendar.MONTH) + "." + cal.get(Calendar.YEAR);
        setDateB.setText(date);
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
                Intent intent = new Intent(NewExamActivity.this, ExamsActivity.class);
                Gson gson = new Gson();
                intent.putExtra("logic", gson.toJson(logic));
                intent.putExtra("lastActivity", "newExam");

                if (view == done) {
                    //save exam and intent back to ExamsActivity
                    Toast.makeText(NewExamActivity.this, R.string.examSavedMessage, Toast.LENGTH_SHORT).show();
                    logic.addExam(date, logic.findSubject(subjectSpinner.getSelectedItem().toString()), notesET.getText().toString());
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out); //use custom animation

                } else if (view == cancel) {
                    //Intent back to ExamsActivity without saving
                    Toast.makeText(NewExamActivity.this, R.string.notSavedMessage, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out); // use custom animation
                } else if (view == setDateB) {
                    //Call DatePickerDialog as UI to set date.
                    final Calendar cal = Calendar.getInstance();
                    Dialog datePicker = new DatePickerDialog(NewExamActivity.this, dateSetListener(), cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                    datePicker.show();
                }


            }
        };
    }

    /**
     * saves date as result of DatePickerDialog
     * @return date as String with correct format.
     */
    private DatePickerDialog.OnDateSetListener dateSetListener() {
        return new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date = year + "." + month + "." + dayOfMonth;
                setDateB.setText(date);
            }
        };
    }

}
