package com.example.speed13;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> deck; // קופה אישית
    private ArrayList<Card> hand; // הקלפים הפתוחים
    private Context context;


    public Player(String name, Context context)
    {
        this.name=name;
        deck=new ArrayList<Card>(); // קופה אישית
        hand=new ArrayList<Card>() ;// קלפים של שחקן
        this.context = context;
     /*  for (int i = 1; i <= 13; i++) {
            String resourceName = "b" + i;
            int num = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
            if (num != 0) {
                // Decode the bitmap from the resource
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), num);
                if (bitmap != null) {
                    bitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
                    deck.add(new Card(bitmap, i));
                    }
                }
            }

        for (int i = 1; i <= 13; i++) {
            String resourceName = "r" + i;
            int num = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
            if (num != 0) {
                // Decode the bitmap from the resource
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), num);
                if (bitmap != null) {
                    bitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
                    deck.add(new Card(bitmap, i));
                    }
                }
            }

        for (int i=1; i<=4; i++)
        {
            hand.add(deck.remove(0));

        }*/
    }

    public boolean isEmptyDeck()
    {
        return deck.isEmpty();
    }
    public void AddCard(Card c)
    {
        hand.add(c);
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
    public String getName(){return name;}
    /*public  void Draw(Canvas canvas)
{

}*/
// לעשות לכולם draw
}
