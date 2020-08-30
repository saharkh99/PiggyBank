package com.example.piggybank.Util;

import android.content.Context;
import android.util.AttributeSet;


public class SquareImgView extends androidx.appcompat.widget.AppCompatImageView {
    public SquareImgView(Context context) {
        super(context);
    }

    public SquareImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImgView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * this method gets height and sets a weight as the same as height
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heihgt = getMeasuredHeight();
        setMeasuredDimension(heihgt, heihgt);
    }

}
