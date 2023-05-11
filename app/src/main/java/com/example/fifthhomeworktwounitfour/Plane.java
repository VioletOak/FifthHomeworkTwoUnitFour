package com.example.fifthhomeworktwounitfour;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Plane {
    private int x = 0, y = 0;
    private int width, height;
    private int wingCounter = 0;
    private Bitmap plane1, plane2;
    private boolean isGoingUp = false;

    public Plane(int screenX, int screenY, Resources resources) {
        plane1 = BitmapFactory.decodeResource(resources, R.drawable.plane);
        plane2 = BitmapFactory.decodeResource(resources, R.drawable.plane_two);
        width = plane1.getWidth() / 3;
        height = plane1.getHeight() / 3;
        width = (int) (width * 1920f / screenX);
        height = (int) (height * 1080f / screenY);
        plane1 = Bitmap.createScaledBitmap(plane1, width, height, false);
        plane2 = Bitmap.createScaledBitmap(plane2, width, height, false);
        y = screenY / 2;
        x = screenX / 21;
    }
    public Bitmap getPlane() {
        if (wingCounter == 0) {
            wingCounter++;
            return plane1;
        } else if (wingCounter > 0) {
            wingCounter--;
            return plane2;
        }
        return null;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isGoingUp() {
        return false;
    }

    public void setGoingUp(boolean b) {
    }

    public int getHeight() {
        return 0;
    }
}
