package com.example.speed13;

import android.graphics.Canvas;

public abstract class Location {
    private int x, y;
    protected int width, height;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 225;
        this.height = 330;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public abstract void Draw(Canvas canvas);
    // בתוך מחלקת Location
    //חדש23.2
    public boolean isTouched(float touchX, float touchY) {
        return touchX >= x && touchX <= (x + width) &&
                touchY >= y && touchY <= (y + height);
    }


}
