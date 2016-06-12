package com.dmallcott.library.progress_indicator;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Circle view for progress indicator.
 *
 * @author dmallcott
 */
public class CircleView extends View {

    private static final int DEFAULT_COLOR = Color.parseColor("#AAFFFFFF");
    private static final int DEFAULT_PADDING = 8;
    private static final int DEFAULT_STROKE = 6;
    private static final int MAX_ALPHA = 255;
    public static final int DEFAULT_VIEW_SIZE = 64;
    private static final int DEFAULT_EXPANDED_VIEW_SIZE = Double.valueOf(DEFAULT_VIEW_SIZE * 2).intValue();

    public enum STATUS {
        UNVISITED, VISITING, VISITED;
    }

    private int mPosition;
    private float mNormalRadius, mExpandedRadius, mCurrentRadius;
    private Paint mPaint;
    private Paint mVisitingPaint;
    private Paint mBitmapPaint;
    private Bitmap mBitmap;
    private STATUS mStatus;

    public CircleView(Context context, STATUS status, int resId, int iconColor, int position) {
        this(context);

        setBitmap(context, resId, iconColor);
        setStatus(status);
        mPosition = position;
    }

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mStatus = STATUS.UNVISITED;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setStrokeWidth(DEFAULT_STROKE);
        mPaint.setStyle(Paint.Style.STROKE);

        mVisitingPaint = new Paint();
        mVisitingPaint.setAntiAlias(true);
        mVisitingPaint.setColor(DEFAULT_COLOR);
        mVisitingPaint.setStrokeWidth(DEFAULT_STROKE);
        mVisitingPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mVisitingPaint.setAlpha(0);

        mBitmapPaint = new Paint();
        mBitmapPaint.setAlpha(0);
        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setStyle(Paint.Style.FILL);

        mNormalRadius = DEFAULT_VIEW_SIZE / 2;
        mExpandedRadius = DEFAULT_EXPANDED_VIEW_SIZE / 2;

        mCurrentRadius = mNormalRadius;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(
                Float.valueOf(mCurrentRadius * 2).intValue(),
                Float.valueOf(mCurrentRadius * 2).intValue()
        );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(canvas.getClipBounds().centerX(), canvas.getClipBounds().centerY(), mCurrentRadius - DEFAULT_PADDING, mPaint);
        canvas.drawCircle(canvas.getClipBounds().centerX(), canvas.getClipBounds().centerY(), mCurrentRadius - DEFAULT_PADDING, mVisitingPaint);
        canvas.drawBitmap(mBitmap, (canvas.getClipBounds().width() - mBitmap.getWidth()) / 2, (canvas.getClipBounds().height() - mBitmap.getHeight()) / 2, mBitmapPaint);
    }

    public void setStatus(STATUS status) {
        if (!mStatus.equals(status)) {
            mStatus = status;

            switch (mStatus) {
                case UNVISITED:
                    mPaint.setStyle(Paint.Style.STROKE);
                    break;

                case VISITING:
                case VISITED:
                    mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                    break;
            }
        }
    }

    private void updateStatus(int position) {
        if (position > mPosition) {
            setStatus(STATUS.VISITED);
        } else if (position == mPosition) {
            setStatus(STATUS.VISITING);
        } else if (position < mPosition) {
            setStatus(STATUS.UNVISITED);
        }
    }

    public void expand(float offset, int position) {
        updateStatus(position);

        mCurrentRadius = mNormalRadius + offset * mNormalRadius;
        mVisitingPaint.setAlpha(Float.valueOf(offset * MAX_ALPHA).intValue());
        mBitmapPaint.setAlpha(Float.valueOf(offset * MAX_ALPHA).intValue());
        requestLayout();
    }

    private void setBitmap(Context context, int resId, int iconColor) {
        mBitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        mBitmap = Bitmap.createScaledBitmap(changeImageColor(mBitmap, iconColor), Float.valueOf(mExpandedRadius).intValue(), Float.valueOf(mExpandedRadius).intValue(), false);
    }

    public static Bitmap changeImageColor(Bitmap sourceBitmap, int color) {
        Bitmap resultBitmap = sourceBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Paint p = new Paint();
        ColorFilter filter = new LightingColorFilter(color, 1);
        p.setColorFilter(filter);

        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(resultBitmap, 0, 0, p);
        return resultBitmap;
    }
}