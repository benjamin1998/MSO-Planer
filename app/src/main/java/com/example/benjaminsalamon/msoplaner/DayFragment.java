package com.example.benjaminsalamon.msoplaner;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import layout.LessonFragment;

public class DayFragment extends Fragment {
    private static final int ARG_DAY = 0; //saves the index of the day

    private int mDay;


    public DayFragment() {
        // Required empty public constructor
    }


    public static DayFragment newInstance(int day) {
        DayFragment fragment = new DayFragment();
        Bundle args = new Bundle();
        args.putInt("ARG_DAY", day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDay = getArguments().getInt("ARG_DAY");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_day, container, false);
        GridLayout grid = (GridLayout) parent.findViewById(R.id.grid);
        /*FragmentTransaction transaction = getFragmentManager().beginTransaction();
        for(int i = 0; i < 15; i++) {
            transaction.add(R.id.grid, LessonFragment.newInstance(i));
        }
        transaction.add(R.id.grid, LessonFragment.newInstance(1));
        transaction.add(R.id.grid, LessonFragment.newInstance(2));
        transaction.add(R.id.grid, LessonFragment.newInstance(3));
        transaction.commit();*/

        //grid.addView(new LessonView(parent.getContext(), ), 0);

        return parent;
    }

}
