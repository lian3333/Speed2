package com.example.speed13;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.speed13.Location;

public class Card extends Location
{

    private int value, color;
    private boolean isopen;
    private Bitmap pic;


    public Card(Context context, int color, int value) {
        super(0, 0);

        this.color = color;
        this.isopen = false;
        this.value = value;


            String resourceName="";
            if (color==1)
            resourceName= "r" + value;
            else
                resourceName="b"+value;
            int num = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());

        this.pic = BitmapFactory.decodeResource(context.getResources(), num);
/*
            }*/
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
