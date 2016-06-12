package com.dmallcott.library.onboarding;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmallcott.library.Page;
import com.dmallcott.library.R;

/**
 * @author dmallcott
 */
public class OnBoardingFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_PAGE = "page";

    public OnBoardingFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static OnBoardingFragment newInstance(Page page) {
        OnBoardingFragment fragment = new OnBoardingFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PAGE, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_onboarding, container, false);

        TextView mTitle = (TextView) rootView.findViewById(R.id.onboarding_fragment_title);
        TextView mContent = (TextView) rootView.findViewById(R.id.onboarding_fragment_content);
        ImageView mImage = (ImageView) rootView.findViewById(R.id.onboarding_fragment_image);

        Page page = (Page) getArguments().get(ARG_PAGE);
        mTitle.setText(page.getTitle());
        mContent.setText(page.getContent());
        mImage.setImageResource(page.getImage());

        return rootView;
    }
}
