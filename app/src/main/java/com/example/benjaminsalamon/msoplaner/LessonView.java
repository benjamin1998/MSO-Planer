package com.example.benjaminsalamon.msoplaner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class LessonView extends View {

    private int mIndex;
    private String mSubject;
    private String mTeacher;
    private String mTime;
    private String mPlace;

    public LessonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        TypedArray attributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.LessonView, 0, 0);

        try {
            mIndex = attributes.getInt(R.styleable.LessonView_index, 0);
            mSubject = attributes.getString(R.styleable.LessonView_subject);
            mTeacher = attributes.getString(R.styleable.LessonView_teacher);
            mTime = attributes.getString(R.styleable.LessonView_time);
            mPlace = attributes.getString(R.styleable.LessonView_place);
        } finally {
            attributes.recycle();
        }

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(mTeacher, 15, 15, new Paint());
    }

    public int getIndex() {
        return mIndex;
    }
    public String getSubject() {
        return mSubject;
    }
    public String getTeacher() {
        return mTeacher;
    }
    public String getTime() {
        return mTime;
    }
    public String getPlace() {
        return mPlace;
    }

    public void setIndex(int pIndex) {
        mIndex = pIndex;
        invalidate();
        requestLayout();
    }
    public void setSubject(String pSubject) {
        mSubject = pSubject;
        invalidate();
        requestLayout();
    }
    public void setTeacher(String pTeacher) {
        mTeacher = pTeacher;
        invalidate();
        requestLayout();
    }
    public void setTime(String pTime) {
        mTime = pTime;
        invalidate();
        requestLayout();
    }
    public void setPlace(String pPlace) {
        mPlace = pPlace;
        invalidate();
        requestLayout();
    }

}