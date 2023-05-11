package com.example.fifthhomeworktwounitfour;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying;
    private Background background1, background2;
    private  int screenX, screenY;
    private Paint paint;
    private float screenRatioX, screenRatioY;
    private Plane plane;

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;
        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());
        background2.setX(screenX);
        paint = new Paint();
        plane = new Plane(screenX, screenY, getResources());
    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep();
        }
    }
    private void update() {
        background1.setX(background1.getX() - (int)(10 * screenRatioX));
        background2.setX(background2.getX() - (int)(10 * screenRatioX));

        if ((background1.getX() + background1.getBackground().getWidth()) <= 0) {
            background1.setX(screenX);
        }
        if ((background2.getX() + background2.getBackground().getWidth()) <= 0) {
            background2.setX(screenX);
        }
        if (plane.isGoingUp()) {
            plane.setY(plane.getY() - (int) (30 * screenRatioY));
        } else {
            plane.setY(plane.getY() + (int) (30 * screenRatioY));
        }
        if (plane.getY() < 0) {
            plane.setY(0);
        } else if (plane.getY() >= screenY - plane.getHeight()) {
            plane.setY(screenY - plane.getHeight());
        }
    }
    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.getBackground(), background1.getX(), background1.getY(), paint);
            canvas.drawBitmap(background2.getBackground(), background2.getX(), background2.getY(), paint);
            canvas.drawBitmap(plane.getPlane(), plane.getX(), plane.getY(), paint);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }
    private void sleep() {
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resumeThread() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }
    public void pauseThread(){
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < (screenX / 2)) {
                    plane.setGoingUp(true);
                } else if (event.getX() >= (screenX / 2)) {

                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                plane.setGoingUp(false);
                break;
        }
        return true;
    }
}
