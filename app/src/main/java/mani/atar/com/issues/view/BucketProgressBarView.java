package mani.atar.com.issues.view;

/**
 * Created by atarmanipandey on 30/1/17.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import mani.atar.com.issues.R;


public class BucketProgressBarView extends View {
    String mText1 = "";
    String mText2 = "";
    float mValue;
    Paint mText2BgPaint = new Paint();
    Paint mText1BgPaint = new Paint();
    Paint mText2Paint = new Paint();
    Paint mText1Paint = new Paint();

    Rect mBarRect = new Rect();
    Rect mText1Rect = new Rect();
    Rect mText2Rect = new Rect();
    int mHalfStrokeWidth;

    int mText2BgColor;
    int mText1BgColor;
    int mText2TextColor;
    int mText1TextColor;
    int mTextSize = 15;
    int mText1Width = 0;
    int mText2Width = 0;
    int mTextPadding = mTextSize;
    private int text1Right;


    public BucketProgressBarView(Context context) {
        super(context);
        init();
    }

    public BucketProgressBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BucketProgressBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.BucketProgressBarView,
                0, 0
        );

        String fontPath = null;

        try {
            // Retrieve the values from the TypedArray and store into
            // fields of this class.
            //
            // The R.styleable.PieChart_* constants represent the index for
            // each custom attribute in the R.styleable.PieChart array.
            mText2BgColor = a.getColor(R.styleable.BucketProgressBarView_bar_text2BgColor, getResources().getColor(R.color.holo_red_dark));
            mText1BgColor = a.getColor(R.styleable.BucketProgressBarView_bar_text1BgColor, getResources().getColor(R.color.holo_green_dark));
            mText2TextColor = a.getColor(R.styleable.BucketProgressBarView_bar_text1_TextColor, Color.WHITE);
            mText1TextColor = a.getColor(R.styleable.BucketProgressBarView_bar_text2_TextColor, Color.WHITE);
            mTextSize = a.getDimensionPixelSize(R.styleable.BucketProgressBarView_bar_textSize, mTextSize);
            mText1 = a.getString(R.styleable.BucketProgressBarView_bar_text1);
            mText2 = a.getString(R.styleable.BucketProgressBarView_bar_text2);
            mTextPadding = a.getDimensionPixelSize(R.styleable.BucketProgressBarView_bar_textPadding, mTextPadding);
            mValue = a.getFloat(R.styleable.BucketProgressBarView_bar_fillPercentage, mValue);
            fontPath = a.getString(R.styleable.BucketProgressBarView_bar_fontPath);
            if (mText1 == null) {
                mText1 = "";
            }
            if (mText2 == null) {
                mText2 = "";
            }

        } finally {
            // release the TypedArray so that it can be reused.
            a.recycle();
        }


        mText2BgPaint.setColor(mText2BgColor);
        mText2BgPaint.setStyle(Paint.Style.FILL);

        mText1BgPaint.setColor(mText1BgColor);
        mText1BgPaint.setStyle(Paint.Style.FILL);

        mText2Paint.setColor(mText2TextColor);
        mText2Paint.setStyle(Paint.Style.FILL);
        mText2Paint.setTextSize(mTextSize);
        mText2Paint.setAntiAlias(true);

        mText1Paint.setColor(mText1TextColor);
        mText1Paint.setStyle(Paint.Style.FILL);
        mText1Paint.setTextSize(mTextSize);
        mText1Paint.setAntiAlias(true);

        if (fontPath != null && fontPath.length() > 0) {
            Typeface t = Typeface.createFromAsset(context.getAssets(), fontPath);
            mText2Paint.setTypeface(t);
            mText1Paint.setTypeface(t);
        }

        mText1Width = (int) mText1Paint.measureText(mText1);
        mText2Width = (int) mText2Paint.measureText(mText2);

    }

    private void init() {

        mText2BgPaint.setColor(getResources().getColor(R.color.holo_red_dark));
        mText2BgPaint.setStyle(Paint.Style.FILL);

        mText1BgPaint.setColor(getResources().getColor(R.color.holo_green_dark));
        mText1BgPaint.setStyle(Paint.Style.FILL);

        mText1Paint.setColor(Color.WHITE);
        mText1Paint.setAntiAlias(true);

        mText2Paint.setColor(Color.WHITE);
        mText2Paint.setAntiAlias(true);

    }


    public void setText(String text1, String text2) {
        this.mText1 = text1;
        this.mText2 = text2;
        int v1 = Integer.parseInt(text1);
        int v2 = Integer.parseInt(text2);
        if (v1 == 0 && v2 == 0) {
            mText1BgPaint.setColor(getResources().getColor(R.color.disable_btn_color));
            mText1Paint.setColor(getResources().getColor(R.color.black));
            setPercentage(100);
        } else {
            mText1BgPaint.setColor(getResources().getColor(R.color.holo_green_dark));
            mText2BgPaint.setColor(getResources().getColor(R.color.holo_red_dark));
            mText1Paint.setColor(getResources().getColor(R.color.white));
            mText2Paint.setColor(getResources().getColor(R.color.white));
            setPercentage(v1 * 100 / (v1 + v2));
        }
        mText1Width = (int) mText1Paint.measureText(text1);
        mText2Width = (int) mText2Paint.measureText(text2);
//        invalidate();
    }

    public void setPercentage(float value) {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("value is a percentage and should be between 0 and 100 found -> " + value);
        }
        mValue = value;
        invalidate();
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        int minHeight = super.getSuggestedMinimumHeight();
        if (minHeight <= 0) {
            return mTextSize + (mTextPadding * 2);
        } else {
            return minHeight;
        }

    }

    @Override
    protected int getSuggestedMinimumWidth() {
        int minWidth = super.getSuggestedMinimumHeight();
        if (minWidth <= 0) {
            return mTextSize + (mTextPadding * 4);
        } else {
            return minWidth;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("atar", "onSizeChanged: ");
        //stroke is always centered, we want to bring it within the bounds
        //of the drawable area.
        mHalfStrokeWidth = mText2BgPaint.getStrokeWidth() < 2 ? (int) mText2BgPaint.getStrokeWidth() : (int) mText2BgPaint.getStrokeWidth() / 2;

        int ypad = getPaddingTop() + getPaddingBottom();
        int xpad = getPaddingLeft() + getPaddingRight();


        mBarRect.left = getPaddingLeft() + mHalfStrokeWidth;
        mBarRect.top = getPaddingTop() + mHalfStrokeWidth;
        mBarRect.right = getWidth() - getPaddingRight() - mHalfStrokeWidth;
        mBarRect.bottom = getHeight() - getPaddingBottom() - mHalfStrokeWidth;


        mText1Paint.getTextBounds(mText1, 0, mText1.length(), mText1Rect);
        mText1Rect.left = getPaddingLeft() + mHalfStrokeWidth; //recalculate this on draw, so it's close to the fill
        if (isInEditMode()) {
            mText1Rect.top = mBarRect.centerY() + mText1Rect.bottom + mHalfStrokeWidth;
        } else {
            mText1Rect.top = mBarRect.centerY() + mText1Rect.height() / 2 + mHalfStrokeWidth;
        }

        mText2Paint.getTextBounds(mText2, 0, mText2.length(), mText2Rect);
        mText2Rect.left = getPaddingLeft() + mHalfStrokeWidth; //recalculate this on draw, so it's close to the fill
        if (isInEditMode()) {
            mText2Rect.top = mBarRect.centerY() + mText2Rect.bottom + mHalfStrokeWidth;
        } else {
            mText2Rect.top = mBarRect.centerY() + mText2Rect.height() / 2 + mHalfStrokeWidth;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // Try for a width based on our minimum
        int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int w = resolveSizeAndState(minw, widthMeasureSpec, 1);

        // Whatever the width ends up being, ask for a height that would let the pie
        // get as big as it can
        int minh = getPaddingBottom() + getPaddingTop() + getSuggestedMinimumHeight();
        int h = resolveSizeAndState(minh, heightMeasureSpec, 0);

        setMeasuredDimension(w, h);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Build the filled part first

        if (mValue == 0) {
            text1Right = (int) (mBarRect.right * (mValue / 100f));
            mBarRect.left = text1Right;
            mBarRect.right = getWidth() - getPaddingRight() - mHalfStrokeWidth;
            canvas.drawRect(mBarRect, mText2BgPaint);
            canvas.drawText(mText2, mBarRect.left + (mBarRect.right - mBarRect.left) / 2 - (mText2Rect.right - mText2Rect.left) / 2, mText2Rect.top, mText2Paint);

        } else if (mValue == 100) {
            text1Right = (int) (mBarRect.right * (mValue / 100f));
            mBarRect.right = text1Right;
            canvas.drawRect(mBarRect, mText1BgPaint);
            canvas.drawText(mText1, mBarRect.left + (mBarRect.right - mBarRect.left) / 2 - (mText1Rect.right - mText1Rect.left) / 2, mText1Rect.top, mText1Paint);
        } else {
            Log.d("atar", "onDraw: mValue="+mValue);
            if (mValue > 0 && mValue < 20 || mValue < 100 && mValue > 80) {
                if (mValue < 20)
                    mValue = 20;
                if (mValue > 80)
                    mValue = 80;

            }
            text1Right = (int) (mBarRect.right * (mValue / 100f));
            mBarRect.right = text1Right;
            canvas.drawRect(mBarRect, mText1BgPaint);
            canvas.drawText(mText1, mBarRect.left + (mBarRect.right - mBarRect.left) / 2 - (mText1Rect.right - mText1Rect.left) / 2, mText1Rect.top, mText1Paint);

            mBarRect.left = text1Right;
            mBarRect.right = getWidth() - getPaddingRight() - mHalfStrokeWidth;
            canvas.drawRect(mBarRect, mText2BgPaint);
            canvas.drawText(mText2, mBarRect.left + (mBarRect.right - mBarRect.left) / 2 - (mText2Rect.right - mText2Rect.left) / 2, mText2Rect.top, mText2Paint);

        }

    }
}
