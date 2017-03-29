package com.randerath.johannes.msoplaner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class NewLessonActivity extends AppCompatActivity {

    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lesson);
        container = (LinearLayout) findViewById(R.id.newLessonContainer);
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
