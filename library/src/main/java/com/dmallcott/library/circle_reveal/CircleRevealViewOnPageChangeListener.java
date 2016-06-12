package com.dmallcott.library.circle_reveal;

import android.support.v4.view.ViewPager;

import com.dmallcott.library.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmallcott
 */
public class CircleRevealViewOnPageChangeListener implements ViewPager.OnPageChangeListener {

    private CircleRevealView mCircleRevealView;

    public CircleRevealViewOnPageChangeListener(CircleRevealView circleRevealView) {
        mCircleRevealView = circleRevealView;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mCircleRevealView.update(positionOffset * mCircleRevealView.getWidth(), position);
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
