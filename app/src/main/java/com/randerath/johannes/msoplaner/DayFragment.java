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

/**
 * Container for lessons at a specific day of the week.
 * Contained by page of DayFragmentPagerAdapter.
 */

public class DayFragment extends Fragment {

    private static final String ARG_DAY = "ARG_DAY"; //saves the index of the day

    private int mDay;
    private Logic mLogic;

    public DayFragment() {
        // Required empty public constructor
    }

    /**
     * Creates new DayFragment.
     * @param day Index of day of the week that should be modelled. Also index of the page of the ViewPagerAdapter. 0 = Monday, 1 = Tuesday, ...
     * @param pLogic Application logic. Used to get lessons at specified day.
     * @return Newly created instance of class DayFragment
     */

    public static DayFragment newInstance(int day, Logic pLogic) {
        DayFragment fragment = new DayFragment();
        Bundle args = new Bundle();
        Gson gson = new Gson();
        String logicString = gson.toJson(pLogic);
        args.putString("logic", logicString);
        args.putInt(ARG_DAY, day);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Assigns variables from arguments.
     */

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

    /**
     * Called when DayFragment is actually shown.
     * @return parent layout with new DayFragment inserted.
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_day, container, false);
        LinearLayout linearContainer = (LinearLayout) parent.findViewById(R.id.dayContainer);

        FragmentTransaction ft;
        LinearLayout layout;

        Day day = mLogic.getDay(mDay); //get data for relevant day from application logic. Data type: com.randerath.johannes.msoPlaner.Day
        Gson gson = new Gson();
        //String logicString = gson.toJson(mLogic);
        //Log.i("logic", logicString);
        Lesson[] lessons = day.getLessons(); //get lessons to show on DayFragment
        if(lessons.length > 0) { //if lessons exist
            for (Lesson lesson : lessons) { //insert every lesson into DayFragment
                layout = new LinearLayout(parent.getContext()); //use as container for FragmentTransaction => 'converts' Fragment to View
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setId(View.generateViewId());
                layout.setLongClickable(true);
                ft = getFragmentManager().beginTransaction(); //add DayFragment to Layout
                ft.add(layout.getId(), LessonFragment.newInstance(mDay, lesson.getSubject().getName(), lesson.getSubject().getPlace(), lesson.getSubject().getTeacher(),  lesson.getTime(), mDay, mLogic));
                ft.commit();
                linearContainer.addView(layout); //add view including Fragment to parent
            }
        }

        return parent;
    }

}
