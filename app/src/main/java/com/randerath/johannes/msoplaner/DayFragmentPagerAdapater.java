package com.randerath.johannes.msoplaner;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Adapter used to populate the ViewPager adapter with the right DayFragment for each day.
 */

public class DayFragmentPagerAdapater extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 6;
    private String tabTitles[] = new String[] {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
    private Context context;
    private Logic logic;

    /**
     * Using parameters to be able to act as the parent could.
     * @param fm Parent's fragment manager
     * @param context Context of parent to call methods from the parent's point of view
     * @param pLogic Application logic. Passed to DayFragments to get Lessons at each day.
     */

    public DayFragmentPagerAdapater(FragmentManager fm, Context context, Logic pLogic) {
        super(fm);
        this.context = context;
        logic = pLogic;
    }

    /**
     * @return Number of pages (= days) available.
     */

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    /**
     * Creates DayFragments for each page and assigns them.
     * Application logic and day is passed.
     * @param position Day (as index)
     * @return DayFragment for that day
     */

    @Override
    public Fragment getItem(int position) {
        return DayFragment.newInstance(position, logic);
    }

    /**
     * Translating page index to name of day.
     * @param position index of a page
     * @return name of day at that page
     */

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}