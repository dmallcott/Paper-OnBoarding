package com.dmallcott.library.progress_indicator;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.dmallcott.library.Page;
import com.dmallcott.library.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmallcott
 */
public class ProgressIndicator extends LinearLayout {

    private List<CircleView> mProgressViews;
    private LinearLayout llContainer;

    public ProgressIndicator(Context context) {
        this(context, null);
    }

    public ProgressIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        mProgressViews = new ArrayList<>();

        View mRootView = inflate(getContext(), R.layout.progress_indicator, this);
        llContainer = (LinearLayout) mRootView.findViewById(R.id.progress_indicator_linearlayout_container);

        if (isInEditMode()) {
            addPage(android.R.drawable.ic_dialog_email, Color.GRAY);
            addPage(android.R.drawable.ic_dialog_email, Color.GRAY);
            addPage(android.R.drawable.ic_dialog_email, Color.GRAY);

            setTranslationX(CircleView.DEFAULT_VIEW_SIZE);
        }
    }

    // 0..1 - 1..0
    public void update(int position, float offset) {
        mProgressViews.get(position).expand(1 - offset, position);
        if (position + 1 < mProgressViews.size()) mProgressViews.get(position + 1).expand(offset, position);
    }

    public int getCount() {
        return mProgressViews.size();
    }

    // TODO : Remove
    private void addPage(int resId, int pageColor) {
        CircleView pvNew = new CircleView(
                getContext(),
                (mProgressViews.size() < 1) ? CircleView.STATUS.VISITING : CircleView.STATUS.UNVISITED,
                resId,
                pageColor,
                mProgressViews.size()
        );

        mProgressViews.add(pvNew);
        llContainer.addView(pvNew);
    }

    public void addPage(Page page) {
        CircleView pvNew = new CircleView(
                getContext(),
                (mProgressViews.size() < 1) ? CircleView.STATUS.VISITING : CircleView.STATUS.UNVISITED,
                page.getIcon(),
                page.getColor(),
                mProgressViews.size()
        );

        mProgressViews.add(pvNew);
        llContainer.addView(pvNew);
    }
}
