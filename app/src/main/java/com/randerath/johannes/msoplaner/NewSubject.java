package com.randerath.johannes.msoplaner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

public class NewSubject extends AppCompatActivity {

    private Logic logic;
    private Gson gson;

    private Button cancel;
    private Button done;
    private EditText name;
    private EditText abbreviation;
    private EditText place;
    private EditText type;
    private EditText teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_subject);

        gson = new Gson();
        String ls = getIntent().getStringExtra("logic");
        logic = gson.fromJson(ls, Logic.class);

        cancel = (Button) findViewById(R.id.cancel);
        done = (Button) findViewById(R.id.done);
        done.setEnabled(true);
        done.setOnClickListener(onButtonClick());
        cancel.setOnClickListener(onButtonClick());

        name = (EditText) findViewById(R.id.name);
        abbreviation = (EditText) findViewById(R.id.abbreviation);
        place = (EditText) findViewById(R.id.place);
        type = (EditText) findViewById(R.id.type);
        teacher = (EditText) findViewById(R.id.teacher);

        /*TextWatcher editTextListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!name.getText().toString().isEmpty() && name.getText().toString().length() > abbreviation.getText().toString().length()) {
                    done.setEnabled(true);
                }

            }

        };

        name.addTextChangedListener(editTextListener);
        abbreviation.addTextChangedListener(editTextListener);*/

    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                if (view == done) {
                    logic.addSubject(name.getText().toString(), abbreviation.getText().toString(), place.getText().toString(), "", teacher.getText().toString());
                    intent = new Intent(NewSubject.this, TimeTableActivity.class);

                    //Toast User Feedback
                    Context context = getApplicationContext();
                    CharSequence charseq1 = "Dein Entwurf wurde gespeichert.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, charseq1, duration);
                    toast.show();

                } else /*if (view == cancel)*/{

                    //Toast User Feedback
                    Context context = getApplicationContext();
                    CharSequence charseq2 = "Dein Entwurf wurde verworfen.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, charseq2, duration);
                    toast.show();


                    intent = new Intent(NewSubject.this, TimeTableActivity.class);
                }
                Gson gson = new Gson();
                intent.putExtra("logic", gson.toJson(logic));
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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

    public void cancelAction(View view) {

        String ls = getIntent().getStringExtra("logic");
        logic = gson.fromJson(ls, Logic.class);
        back();

    }

    public void doneAction(View view) {

        logic.addSubject(name.getText().toString(), abbreviation.getText().toString(), place.getText().toString(), type.getText().toString(), teacher.getText().toString());
        Log.i("subject", logic.getSubjects().peek().getName());
        back();

    }

    private void back() {

        Intent intent = new Intent();
        String logicString = gson.toJson(logic);
        intent.putExtra("logic", logicString);
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
