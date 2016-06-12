package com.dmallcott.library.onboarding;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.dmallcott.library.Page;
import com.dmallcott.library.PageList;
import com.dmallcott.library.R;
import com.dmallcott.library.circle_reveal.CircleRevealView;
import com.dmallcott.library.circle_reveal.CircleRevealViewOnPageChangeListener;
import com.dmallcott.library.progress_indicator.ProgressIndicator;
import com.dmallcott.library.progress_indicator.ProgressIndicatorOnPageChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmallcott
 */
public class OnBoardingActivity extends AppCompatActivity {

    private static final String EXTRA_PAGES = "pages";

    private OnBoardingPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private ProgressIndicator mProgressIndicator;
    private CircleRevealView mCircleRevealView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        PageList pages = (PageList) getIntent().getExtras().get(EXTRA_PAGES);

        mPagerAdapter = new OnBoardingPagerAdapter(getSupportFragmentManager(), pages.getPages());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mProgressIndicator = (ProgressIndicator) findViewById(R.id.progress_indicator);
        mCircleRevealView = (CircleRevealView) findViewById(R.id.circle_reveal_view);

        mViewPager.setAdapter(mPagerAdapter);

        for (Page page : pages.getPages()) {
            mProgressIndicator.addPage(page);
            mCircleRevealView.addColor(page.getColor());
        }

        mViewPager.addOnPageChangeListener(new ProgressIndicatorOnPageChangeListener(mProgressIndicator));
        mViewPager.addOnPageChangeListener(new CircleRevealViewOnPageChangeListener(mCircleRevealView));
        mViewPager.setPageTransformer(false, new OnBoardingPageTransformer());
    }

    public static Intent getIntent(Context context, PageList pages) {
        Intent intent = new Intent(context, OnBoardingActivity.class);
        intent.putExtra(EXTRA_PAGES, pages);
        return intent;
    }
}
