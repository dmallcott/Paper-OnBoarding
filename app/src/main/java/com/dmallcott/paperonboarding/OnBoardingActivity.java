package com.dmallcott.paperonboarding;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.dmallcott.library.Page;
import com.dmallcott.library.PageList;
import com.dmallcott.library.circle_reveal.CircleRevealView;
import com.dmallcott.library.circle_reveal.CircleRevealViewOnPageChangeListener;
import com.dmallcott.library.progress_indicator.ProgressIndicator;
import com.dmallcott.library.progress_indicator.ProgressIndicatorOnPageChangeListener;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Page> pages = new ArrayList<>();
        pages.add(new Page(
                "Hotels",
                "All hotels and hostels are sorted by hospitality rating",
                Color.parseColor("#678FB4"),
                R.drawable.ic_local_hotel_white_24dp,
                R.drawable.hotels

        ));
        pages.add(new Page(
                "Banks",
                "We carefully verify all banks before add them into the app",
                Color.parseColor("#65B0B4"),
                R.drawable.ic_account_balance_white_24dp,
                R.drawable.banks

        ));
        pages.add(new Page(
                "Stores",
                "All local stores are categorized for your convenience",
                Color.parseColor("#9B90BC"),
                R.drawable.ic_store_white_24dp,
                R.drawable.stores

        ));

        startActivity(com.dmallcott.library.onboarding.OnBoardingActivity.getIntent(getApplicationContext(), new PageList(pages)));
    }
}
