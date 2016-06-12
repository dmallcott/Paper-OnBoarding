package com.dmallcott.library.onboarding;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dmallcott.library.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 *
 * @author dmallcott
 */
public class OnBoardingPagerAdapter extends FragmentPagerAdapter {

    private List<Page> mPages;

    public OnBoardingPagerAdapter(FragmentManager fm, List<Page> pages) {
        super(fm);
        mPages = pages;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return OnBoardingFragment.newInstance(mPages.get(position));
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return mPages.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }
}