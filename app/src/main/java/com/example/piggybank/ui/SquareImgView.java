package com.example.piggybank.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heihgt = getMeasuredHeight();
        setMeasuredDimension(heihgt, heihgt);
    }

}
