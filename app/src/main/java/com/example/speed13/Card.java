package com.example.speed13;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.speed13.Location;

public class Card extends Location
{

    private int value;
    private boolean isopen;
    private Bitmap pic;


    public Card( int color, int value) {
        super(0, 0);

        this.pic = pic;
        this.isopen = false;
        this.value = value;


    }





    public Bitmap getPic() {
        return pic;
    }

    public boolean isIsopen() {
        return isopen;
    }

    public int getValue() {
        return value;
    }






    public void setIsopen(boolean isopen) {
        this.isopen = isopen;
    }

    @Override
    public void Draw(Canvas canvas) {
        if (pic!=null )
            canvas.drawBitmap(pic,getX(),getY(),null);

    }

}
