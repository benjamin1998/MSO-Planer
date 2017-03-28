package com.randerath.johannes.msoplaner;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import layout.LessonFragment;

public class Main2Activity extends AppCompatActivity {

    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        /*container = (LinearLayout) findViewById(R.id.linearContainer);
        LinearLayout layout;
        FragmentTransaction ft;
        for(int i = 0; i < 20; i++) {
            layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setId(View.generateViewId());
            ft = getSupportFragmentManager().beginTransaction();
            ft.add(layout.getId(), LessonFragment.newInstance(12));
            ft.commit();
            container.addView(layout);
        }*/
    }
}
