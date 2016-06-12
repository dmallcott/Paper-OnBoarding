package com.dmallcott.library.onboarding;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmallcott.library.R;

/**
 * @author dmallcott
 */
public class OnBoardingPageTransformer implements ViewPager.PageTransformer {

    private static final int DEFAULT_DISTANCE = 60;

    private TextView mTitle;
    private TextView mContent;
    private ImageView mImage;

    @Override
    public void transformPage(View page, float position) {

        if (position <= 0) { // [-1,0]

            // SO INEFFICIENT
            mTitle = (TextView) page.findViewById(R.id.onboarding_fragment_title);
            mContent = (TextView) page.findViewById(R.id.onboarding_fragment_content);
            mImage = (ImageView) page.findViewById(R.id.onboarding_fragment_image);

            if (mTitle != null) {
                mTitle.setAlpha(1 + position);
                mTitle.setTranslationY(-DEFAULT_DISTANCE * (position));
            }

            if (mContent != null) {
                mContent.setAlpha(1 + position);
                mContent.setTranslationY((-DEFAULT_DISTANCE - 10) * (position));
            }

            if (mImage != null) {
                mImage.setAlpha(1 + position);
                mImage.setTranslationY((-DEFAULT_DISTANCE - 20) * (position));
            }

        } else if (position <= 1) { // (0,1]

            // SO INEFFICIENT
            mTitle = (TextView) page.findViewById(R.id.onboarding_fragment_title);
            mContent = (TextView) page.findViewById(R.id.onboarding_fragment_content);
            mImage = (ImageView) page.findViewById(R.id.onboarding_fragment_image);

            if (mTitle != null) {
                mTitle.setAlpha(1 - position);
                mTitle.setTranslationY(DEFAULT_DISTANCE * (position));
            }

            if (mContent != null) {
                mContent.setAlpha(1 - position);
                mContent.setTranslationY((DEFAULT_DISTANCE + 10) * (position));
            }

            if (mImage != null) {
                mImage.setAlpha(1 - position);
                mImage.setTranslationY((DEFAULT_DISTANCE + 20) * (position));
            }
        }
    }
}
