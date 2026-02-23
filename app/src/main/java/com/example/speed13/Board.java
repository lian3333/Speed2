package com.example.speed13;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;

public class
Board extends View {
    //private ArrayList<Card> deck;
    private Player player1;
    private Player player2;
    private Card openCard1, openCard2 ;
    private boolean isInitialized = false;
    private Bitmap kupa1, kupa2;
    private int xmid,ymid;
    public GameActivity gameActivity;
    private Bitmap boardBitmap;
    private Context context;
    //חדש23.2
    private int pic;
    private String value;
    private Card selectedPlayerCard = null;
    private int selectedIndex = -1;


    // בנאי יחיד - מצוין לשימוש שלך ב-Start Activity
    public Board(Context context) {
        super(context);
        this.context = context;
        // רק יוצרים את האובייקטים, לא ממקמים אותם עדיין
        //deck = new ArrayList<>();
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
        //deck.clear();
        player1.getDeck().clear();
        player2.getDeck().clear();
        for(int n=1; n<=13; n++) {
            for (int c = 1; c <= 2; c++) {
                player1.getDeck().add(new Card(context, c, n));
                player2.getDeck().add(new Card(context, c, n));
            }
        }
        Collections.shuffle(player1.getDeck());
        Collections.shuffle(player2.getDeck());

        this.xmid=(screenW / 2) -150 ;
        this.ymid=(screenH / 2) -150 ;
        // 2. חישוב מיקומים לפי גודל המסך (screenW, screenH)
        int cardGap = (xmid/2)+70; // המרווח בין קלפים (תלוי ברוחב הקלף שלך)


        // מיקום התחלתי לשחקן 1 (למעלה)
        int x1 = (screenW / 2) - (2 * cardGap)+20;
        int y1 = screenH / 5; // 10% מלמעלה


        // מיקום התחלתי לשחקן 2 (למטה)
        // רבע מלמטה
        int x2 = (screenW / 2) - (2 * cardGap)+20;
        int y2 = screenH - (screenH / 3);

        // 3. חלוקה
        for (int i = 0; i < 4; i++) {
            // שחקן 1
            Card c1 = player1.getDeck().remove(0);
            c1.setX(x1);
            c1.setY(y1);


            //c1.setIsOpen(true);
            player1.AddCard(c1);

            // שחקן 2
            Card c2 = player2.getDeck().remove(0);
            c2.setX(x2);
            c2.setY(y2);
            //c2.setIsOpen(true);
            player2.AddCard(c2);

            x1 += cardGap;
            x2 += cardGap;
        }


        // קלף פתוח במרכז המסך
        openCard1 = player1.getDeck().remove(0);
        openCard2 = player2.getDeck().remove(0);

        //this.xmid=(screenW / 2) -150 ;
        //this.ymid=(screenH / 2) -150 ;
        openCard1.setX(xmid + (xmid/2)); // נניח חצי רוחב קלף
        openCard1.setY(ymid );

        openCard2.setX(xmid - (xmid/2)); // נניח חצי רוחב קלף
        openCard2.setY(ymid);
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


        for (int i=0; i<player1.getHand().size(); i++) {
            player1.getHand().get(i).Draw(canvas);
        }
        for (int i=0; i<player2.getHand().size(); i++) {
            player2.getHand().get(i).Draw(canvas);
        }
        if (openCard1 != null) {
            openCard1.Draw(canvas);
        }
        if (openCard2 != null) {
           openCard2.Draw(canvas);
        }
        kupa1=Bitmap.createScaledBitmap(kupa1,260,380,true);
        kupa2=Bitmap.createScaledBitmap(kupa2,260,380,true);
        canvas.drawBitmap(kupa1, 0,0, null);
        canvas.drawBitmap(kupa2, 830,2020, null);

    }

    //חדש23.2
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float tx = event.getX();
            float ty = event.getY();
// בדיקה: האם לחצו על קלף של שחקן 2?
            for (int i = 0; i < player2.getHand().size(); i++) {
                Card card = player2.getHand().get(i);
                if (card.isTouched(tx, ty)) {
                    selectedPlayerCard = card; // שומרים את הקלף שנבחר
                    selectedIndex = i;         // שומרים את המיקום שלו ביד
                    Toast.makeText(context, "נבחר: " + card.toString(), Toast.LENGTH_SHORT).show();
                    return true;
                }
            }

            // בדיקה: האם לחצו על קלף מרכזי (ורק אם כבר נבחר קלף מהיד קודם)
            if (selectedPlayerCard != null) {
                // בדיקה מול קלף מרכז 1
                if (openCard1.isTouched(tx, ty)) {
                    checkAndMove(openCard1, 1);
                    return true;
                }
                // בדיקה מול קלף מרכז 2
                if (openCard2.isTouched(tx, ty)) {
                    checkAndMove(openCard2, 2);
                    return true;
                }
            }
            // בדיקה לשחקן 2
            for (int i = 0; i < player2.getHand().size(); i++) {
                Card card = player2.getHand().get(i);
                if (card.isTouched(tx, ty)) {
                    // הצגת Toast עם שם הקלף
                    Toast.makeText(context, "שחקן 2: " + card.toString(), Toast.LENGTH_SHORT).show();

                    handleCardClick(card, player2, i);
                    invalidate();
                    return true;
                }
            }

            // בדיקה לשחקן 1
            for (int i = 0; i < player1.getHand().size(); i++) {
                Card card = player1.getHand().get(i);
                if (card.isTouched(tx, ty)) {
                    // הצגת Toast עם שם הקלף
                    Toast.makeText(context, "שחקן 1: " + card.toString(), Toast.LENGTH_SHORT).show();

                    handleCardClick(card, player1, i);
                    invalidate();
                    return true;
                }
            }
            // 3. בדיקה לקלף פתוח 1 (במרכז)
            /*if (openCard1 != null && openCard1.isTouched(tx, ty)) {
                Toast.makeText(context, "מרכז 1: " + openCard1.toString(), Toast.LENGTH_SHORT).show();
                return true;
            }

            // 4. בדיקה לקלף פתוח 2 (במרכז)
            if (openCard2 != null && openCard2.isTouched(tx, ty)) {
                Toast.makeText(context, "מרכז 2: " + openCard2.toString(), Toast.LENGTH_SHORT).show();
                return true;
            }*/
        }
        return super.onTouchEvent(event);
    }

    private void handleCardClick(Card card, Player player2, int i) {
    }

    private void checkAndMove(Card centerCard, int centerSlot) {
        int valHand = selectedPlayerCard.getValue();
        int valCenter = centerCard.getValue();

        // בדיקת התנאי: הפרש של 1 (כולל טיפול באס ומלך)
        boolean isValid = (Math.abs(valHand - valCenter) == 1) ||
                (valHand == 1 && valCenter == 13) ||
                (valHand == 13 && valCenter == 1);

        if (isValid) {
            // 1. מעדכנים את המרכז (שומרים על המיקום המקורי של המרכז)
            int oldX = centerCard.getX();
            int oldY = centerCard.getY();

            selectedPlayerCard.setX(oldX);
            selectedPlayerCard.setY(oldY);

            if (centerSlot == 1) openCard1 = selectedPlayerCard;
            else openCard2 = selectedPlayerCard;

            // 2. מסירים מהיד ושולפים קלף חדש מהקופה
            player2.getHand().remove(selectedIndex);
            if (!player2.getDeck().isEmpty()) {
                Card newCard = player2.getDeck().remove(0);
                // כאן כדאי לתת ל-newCard את ה-X וה-Y המקוריים של הקלף שיצא מהיד
                player2.getHand().add(selectedIndex, newCard);
            }

            Toast.makeText(context, "מהלך מצוין!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "הקלף אינו מתאים", Toast.LENGTH_SHORT).show();
        }

        // איפוס הבחירה בסוף התהליך
        selectedPlayerCard = null;
        selectedIndex = -1;
        invalidate(); // ציור מחדש של הלוח
    }


/*


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
