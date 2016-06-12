package com.dmallcott.library.progress_indicator;

import android.support.v4.view.ViewPager;

/**
 * @author dmallcott
 */
public class ProgressIndicatorOnPageChangeListener implements ViewPager.OnPageChangeListener {

    private ProgressIndicator mProgressIndicator;

    public ProgressIndicatorOnPageChangeListener(ProgressIndicator progressIndicator) {
        mProgressIndicator = progressIndicator;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        mProgressIndicator.update(position, positionOffset);

        if (position == 0) position = 1;
        else if (position == 1) position = 0;
        else position = 1 - position;

        mProgressIndicator.setTranslationX(
                (position - positionOffset - ((mProgressIndicator.getCount() % 2 == 0) ? 0.5f : 0 )) * CircleView.DEFAULT_VIEW_SIZE
        );
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
