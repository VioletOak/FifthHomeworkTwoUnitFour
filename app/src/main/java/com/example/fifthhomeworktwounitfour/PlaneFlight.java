package com.example.fifthhomeworktwounitfour;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PlaneFlight extends View {
    private float pPlaneY;
    private float pTouchY;
    private float pMidY;

    public PlaneFlight(Context context, AttributeSet attrs) {
        super(context, attrs);
        pMidY = getHeight() / 2;
        pPlaneY = pMidY;
        pTouchY = pMidY;
        }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pTouchY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                float diffY = y - pTouchY;
                pPlaneY += diffY;
                if (pPlaneY < 0) {
                    pPlaneY = 0;
                } else if (pPlaneY > getHeight()) {
                    pPlaneY = getHeight();
                }
                pTouchY = y;
                break;
            case MotionEvent.ACTION_UP:
                pPlaneY = pMidY;
                break;
        }
        invalidate();
        return true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
    }
}
