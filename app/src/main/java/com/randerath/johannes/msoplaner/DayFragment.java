package com.randerath.johannes.msoplaner;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import layout.LessonFragment;

public class DayFragment extends Fragment {
    private static final int ARG_DAY = 0; //saves the index of the day

    private int mDay;
    private Logic mLogic;

    public DayFragment() {
        // Required empty public constructor
    }


    public static DayFragment newInstance(int day, Logic pLogic) {
        DayFragment fragment = new DayFragment();
        Bundle args = new Bundle();
        Gson gson = new Gson();
        String logicString = gson.toJson(pLogic);
        args.putString("logic", logicString);
        args.putInt("ARG_DAY", day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDay = getArguments().getInt("ARG_DAY");
            String logicString = getArguments().getString("logic");
            Gson gson = new Gson();
            mLogic = gson.fromJson(logicString, Logic.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_day, container, false);
        LinearLayout linearContainer = (LinearLayout) parent.findViewById(R.id.dayContainer);

        FragmentTransaction ft;
        LinearLayout layout;

        Day day = mLogic.getDay(mDay);
        Lesson[] lessons = day.getLessons();

        for (Lesson lesson : lessons) {
            layout = new LinearLayout(parent.getContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setId(View.generateViewId());
            ft = getFragmentManager().beginTransaction();
            ft.add(layout.getId(), LessonFragment.newInstance(mDay, lesson.getSubject().getName(), lesson.getSubject().getPlace(), lesson.getSubject().getTeacher(), lesson.getStartHour(), lesson.getStartMin(), lesson.getEndHour(), lesson.getEndMin()));
            ft.commit();
            linearContainer.addView(layout);
        }

        return parent;
    }

}
