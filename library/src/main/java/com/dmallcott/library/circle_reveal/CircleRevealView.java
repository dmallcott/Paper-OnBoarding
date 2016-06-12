package com.dmallcott.library.circle_reveal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.dmallcott.library.progress_indicator.CircleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmallcott
 */
public class CircleRevealView extends View {

    private float mRadius;
    private float mMaxRadius;
    private float mCenterX;
    private float mCenterY;

    private List<Paint> mPreviousPaints;
    private int mCurrentPosition;

    public CircleRevealView(Context context) {
        this(context, null);
    }

    public CircleRevealView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleRevealView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setWillNotDraw(false);

        mCenterX = 0;
        mCenterY = 0;
        mRadius = 0;

        mPreviousPaints = new ArrayList<>();

        if (isInEditMode()) {
            mRadius = 250;
            mPreviousPaints.add(getPaint(Color.CYAN));
            mPreviousPaints.add(getPaint(Color.BLUE));
        }
    }

    public void addColor(int color) {
        mPreviousPaints.add(getPaint(color));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        mCenterX = (right - left) / 2;
        mCenterY = bottom - CircleView.DEFAULT_VIEW_SIZE;
        mMaxRadius = Math.max(right, bottom) * 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mCenterX, mCenterY, mMaxRadius, mPreviousPaints.get(mCurrentPosition));
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mPreviousPaints.get(mCurrentPosition + 1));
    }

    public void update(float radius, int position) {
        mCurrentPosition = position;

        if (radius != 0) {
            mRadius = radius * 2;
            invalidate();
        }
    }

    private Paint getPaint(int color) {

        Paint paint = new Paint();
        paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        return paint;
    }
}
