package com.example.piggybank.Util;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;

import com.example.piggybank.R;

/**
 * i used my own progressbar .its two circle inside of each other like a coin
 */
public class Progress extends View{
    Paint paint,paint1;
    private int mSize;

    public Progress(Context context) {
        super(context);
        init();
    }

    public Progress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Progress(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        paint = new Paint();
        paint1=new Paint();
        paint.setColor(getResources().getColor(R.color.colorAccent));
        paint1.setColor(getResources().getColor(R.color.colorAccentWithOpacity));
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        paint1.setStyle(Paint.Style.FILL);
    }
    /**
     * two circle
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(100, 100, 50, paint);
        canvas.drawCircle(100, 100, 50, paint1);
        setBlur(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int xPad = getPaddingLeft() + getPaddingRight();
        int yPad = getPaddingTop() + getPaddingBottom();
        int width = getMeasuredWidth() - xPad;
        int height = getMeasuredHeight() - yPad;
        mSize = (width < height) ? width : height;
        setMeasuredDimension(mSize + xPad, mSize + yPad);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        animation();
    //    setBlur(true);
    }

    /**
     * Rotate around a vertical axis
     */
    public void animation(){
       ObjectAnimator animation = ObjectAnimator.ofFloat(
               this, "rotationY", 0.0f, 360f, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
       animation.setDuration(2500);
       animation.setRepeatCount(ObjectAnimator.INFINITE);
       animation.setInterpolator(new AccelerateDecelerateInterpolator());
       animation.start();
   }
    public void setBlur(boolean bool){
        if(bool)
        getRootView().setAlpha(0.8f);
    }

}
