package com.randerath.johannes.msoplaner;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class DayFragmentPagerAdapater extends FragmentPagerAdapter {

    final int PAGE_COUNT = 6;
    private String tabTitles[] = new String[] {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
    private Context context;
    private Logic logic;

    public DayFragmentPagerAdapater(FragmentManager fm, Context context, Logic pLogic) {
        super(fm);
        this.context = context;
        logic = pLogic;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return DayFragment.newInstance(position, logic);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}
