package com.example.speed13;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;

public class
Board extends View {
    private ArrayList<Card> deck;
    private Player player1;
    private Player player2;
    private Card openCard1, openCard2 ;
    private boolean isInitialized = false;
    private Bitmap kupa1, kupa2;
    private int xmid,ymid;
    public GameActivity gameActivity;
    private Bitmap boardBitmap;



    // בנאי יחיד - מצוין לשימוש שלך ב-Start Activity
    public Board(Context context) {
        super(context);
        // רק יוצרים את האובייקטים, לא ממקמים אותם עדיין
        deck = new ArrayList<>();
        player1 = new Player("Player 1", context);
        player2 = new Player("Player 2", context);
        this.kupa1=BitmapFactory.decodeResource(getResources(),R.drawable.backcard);
        this.kupa2=BitmapFactory.decodeResource(getResources(),R.drawable.backcard);
        boardBitmap=BitmapFactory.decodeResource(getResources(),R.drawable.bgspeed);
    }

    // הפונקציה הזו רצה אוטומטית כשהמסך נטען ויודעים את הגודל שלו
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        boardBitmap = Bitmap.createScaledBitmap(boardBitmap, w, h, true);//התאמת גודל התמונה לגודל המסך

        // נריץ את החלוקה רק פעם אחת כשהמסך מוכן
        if (!isInitialized) {
            dealCards(w, h);
            isInitialized = true;
        }
    }

    private void dealCards(int screenW, int screenH) {
        // 1. איפוס ויצירת חפיסה
        deck.clear();
        player1.getDeck().clear();
        player2.getDeck().clear();
        for(int n=1; n<=13; n++){
            for(int c=1; c<=2; c++)
            {
                player1.getDeck().add(new Card(c,n));
            }
            for (int value = 0; value <= 10; value++) {
                for (int copy = 0; copy < 4; copy++) {
                    deck.add(new Card(copy, value));
                }
            }

        }
        Collections.shuffle(deck);

        // 2. חישוב מיקומים לפי גודל המסך (screenW, screenH)
        int cardGap = 160; // המרווח בין קלפים (תלוי ברוחב הקלף שלך)


        // מיקום התחלתי לשחקן 1 (למעלה)
        int x1 = (screenW / 2) - (2 * cardGap)-80;
        int y1 = screenH / 5; // 10% מלמעלה


        // מיקום התחלתי לשחקן 2 (למטה)
        // רבע מלמטה
        int x2 = (screenW / 2) - (2 * cardGap)-80;
        int y2 = screenH - (screenH / 3);

        // 3. חלוקה
        for (int i = 0; i < 5; i++) {
            // שחקן 1
            Card c1 = deck.remove(0);
            c1.setX(x1);
            c1.setY(y1);
            //c1.setIsOpen(true);
            player1.AddCard(c1);

            // שחקן 2
            Card c2 = deck.remove(0);
            c2.setX(x2);
            c2.setY(y2);
            //c2.setIsOpen(true);
            player2.AddCard(c2);

            x1 += cardGap;
            x2 += cardGap;
        }


        // קלף פתוח במרכז המסך
        openCard1 = deck.remove(0);
        openCard2 = deck.remove(0);
        this.xmid=(screenW / 2) -100 ;
        this.ymid=(screenH / 2) -150 ;
        openCard1.setX((screenW / 2) ); // נניח חצי רוחב קלף
        openCard1.setY(screenH / 2 );
        //openCard.setIsOpen(true);
    }
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        super.onDraw(canvas);
        int width = getWidth()*7;
        int height = getHeight();
        Rect dest = new Rect(-100,0,width,height);
        canvas.drawBitmap(boardBitmap,0,0,null);


        for (Card c : player1.getHand()) {
            c.Draw(canvas);
        }
        for (Card c : player2.getHand()) {
            c.Draw(canvas);
        }
        if (openCard1 != null) {
            openCard1.Draw(canvas);
        }
        if (openCard2 != null) {
            openCard2.Draw(canvas);
        }
        kupa1=Bitmap.createScaledBitmap(kupa1,200,290,true);
        kupa2=Bitmap.createScaledBitmap(kupa2,200,290,true);
        canvas.drawBitmap(kupa1, xmid-380,ymid-560, null);
        canvas.drawBitmap(kupa2, xmid+380,ymid+560, null);

    }





















/*
    private Player player1,player2;
    Card open1,open2;

    private Bitmap boardBitmap;
    public Board(Context context) {
        super(context);
        player1=new Player("Player 1", context);
        player2=new Player("Player 2", context);
       //open1=player1.AddCard(player1.getDeck().remove(0));
       //open2=player2.AddCard(player2.getDeck().remove(0));
        boardBitmap=BitmapFactory.decodeResource(getResources(),R.drawable.bg);


    }
    @Override
    protected void onSizeChanged(int w, int h,int oldw, int oldh){
        super.onSizeChanged(w,h,oldw,oldh);
        boardBitmap=Bitmap.createScaledBitmap(boardBitmap,w,h,true);

        }
    }
    private void dealCards (int screenw,int screenh){
        player1.getDeck().clear();
        player2.getDeck().clear();
        for(int n=1; n<=13; n++){
            for(int c=1; c<=2; c++)
            {
                player1.getDeck().add(new Card(c,n));
            }
        }



    }
   /* @Override
  // public void draw(Canvas canvas)
    {
    player1.getHand().
    }*
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth()*7;
        int height = getHeight();
        Rect dest = new Rect(-100,0,width,height);
        canvas.drawBitmap(boardBitmap,0,0,null);

    }********** */

}
