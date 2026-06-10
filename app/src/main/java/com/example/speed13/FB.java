package com.example.speed13;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

// google explanations
// https://firebase.google.com/docs/database/android/lists-of-data#java_1


public class FB {
    private static FB instance;

    FirebaseDatabase database;
    private static Context context;

    private FB() {
        //database = FirebaseDatabase.getInstance("https://fbrecordssingletone-default-rtdb.firebaseio.com/");
        database = FirebaseDatabase.getInstance();

        listenerToOpen1();
        listenerToOpen2();
        listenerToDeck1();
        listenerToDeck2();
        listenerToHand1();
        listenerToHand2();
        listenerToWin();


    }



    public static FB getInstance(Context context1) {
        if (null == instance) {
            instance = new FB();
            context = context1;  // context to GameActivity הפניה
        }
        return instance;
    }

    public void setOpen1(Card card)
    {
        // Write a message to the database
        DatabaseReference myRef = database.getReference("open1"); // push adds new node with unique value
        FbCard fbCard = new FbCard(card.getValue(), card.getIntColor());

        myRef.setValue(fbCard);
    }

    private void listenerToOpen1() {
        DatabaseReference myRef = database.getReference("open1"); // push adds new node with unique value

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FbCard fBcard = snapshot.getValue(FbCard.class);
                if (fBcard != null) {
                    ((GameActivity)context).newValFromFbToOpen1(fBcard);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void setOpen2(Card card)
    {
        // Write a message to the database
        DatabaseReference myRef = database.getReference("open2"); // push adds new node with unique value
        FbCard fbCard = new FbCard(card.getValue(), card.getIntColor());

        myRef.setValue(fbCard);
    }
    private void listenerToOpen2() {
        DatabaseReference myRef = database.getReference("open2"); // push adds new node with unique value

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FbCard fBcard = snapshot.getValue(FbCard.class);
                if (fBcard != null) {
                    ((GameActivity)context).newValFromFbToOpen2(fBcard);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setDeck1(ArrayList<Card> deck)
    {
        DatabaseReference myRef = database.getReference("player1/deck1"); // push adds new node with unique value
        myRef.setValue(null);
        ArrayList<FbCard> fbDeck = new ArrayList<>();
        for (int i = 0; i < deck.size(); i++) {
            fbDeck.add(new FbCard(deck.get(i).getValue(),deck.get(i).getIntColor()));
        }

        myRef.setValue(fbDeck);
    }




    private void listenerToDeck1() {
        DatabaseReference myRef = database.getReference("player1/deck1");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    ArrayList<FbCard> deck = new ArrayList<>();
                    for(DataSnapshot dataSnapshot: snapshot.getChildren())
                    {
                        FbCard fBcard = dataSnapshot.getValue(FbCard.class);
                        deck.add(fBcard);
                    }
                    ((GameActivity)context).newValFromFbToDeck1(deck);
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setDeck2(ArrayList<Card> deck)
    {
        DatabaseReference myRef = database.getReference("player2/deck2"); // push adds new node with unique value
        myRef.setValue(null);
        ArrayList<FbCard> fbDeck = new ArrayList<>();
        for (int i = 0; i < deck.size(); i++) {
            fbDeck.add(new FbCard(deck.get(i).getValue(),deck.get(i).getIntColor()));
        }

        // Write a message to the database

        myRef.setValue(fbDeck);
    }


    private void listenerToDeck2() {
        DatabaseReference myRef = database.getReference("player2/deck2");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    ArrayList<FbCard> deck = new ArrayList<>();
                    for(DataSnapshot dataSnapshot: snapshot.getChildren())
                    {
                        FbCard fBcard = dataSnapshot.getValue(FbCard.class);
                        deck.add(fBcard);
                    }
                    ((GameActivity)context).newValFromFbToDeck2(deck);
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void setHand1(ArrayList<Card> hand)
    {
        ArrayList<FbCard> fbHand = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            fbHand.add(new FbCard(hand.get(i).getValue(),hand.get(i).getIntColor()));
        }

        // Write a message to the database
        DatabaseReference myRef = database.getReference("player1/hand1"); // push adds new node with unique value
        myRef.setValue(fbHand);
    }

    private void listenerToHand1() {
        DatabaseReference myRef = database.getReference("player1/hand1");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    ArrayList<FbCard> hand = new ArrayList<>();
                    for(DataSnapshot dataSnapshot: snapshot.getChildren())
                    {
                        FbCard fBcard = dataSnapshot.getValue(FbCard.class);
                        hand.add(fBcard);
                    }
                    ((GameActivity)context).newValFromFbToHand1(hand);
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setHand2(ArrayList<Card> hand)
    {
        ArrayList<FbCard> fbHand = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            fbHand.add(new FbCard(hand.get(i).getValue(),hand.get(i).getIntColor()));
        }

        // Write a message to the database
        DatabaseReference myRef = database.getReference("player2/hand2"); // push adds new node with unique value
        myRef.setValue(fbHand);
    }

    private void listenerToHand2() {
        DatabaseReference myRef = database.getReference("player2/hand2");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    ArrayList<FbCard> hand = new ArrayList<>();
                    for(DataSnapshot dataSnapshot: snapshot.getChildren())
                    {
                        FbCard fBcard = dataSnapshot.getValue(FbCard.class);
                        hand.add(fBcard);
                    }
                    ((GameActivity)context).newValFromFbToHand2(hand);
                    Board.gotDecks=true;
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void listenerToWin() {
        DatabaseReference myRef = database.getReference("win"); // push adds new node with unique value

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() != null) {
                    // מתייחסים לערך כמספר, ומושכים ממנו את ה-int
                    int win = ((Number) snapshot.getValue()).intValue();
                    ((GameActivity) context).EndGame(win);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void setWin(int x)
    {
        // Write a message to the database
        DatabaseReference myRef = database.getReference("win"); // push adds new node with unique value
        int win=x;

        myRef.setValue(win);
    }

    public void FbClear(){
        DatabaseReference myRef = database.getReference("open1"); // push adds new Arrylist with unique value
        myRef.removeValue();
        DatabaseReference myRef2 = database.getReference("open2"); // push adds new Arrylist with unique value
        myRef2.removeValue();
        DatabaseReference myRef3 = database.getReference("player1"); // push adds new Arrylist with unique value
        myRef3.removeValue();
        DatabaseReference myRef4 = database.getReference("player2"); // push adds new Arrylist with unique value
        myRef4.removeValue();
        DatabaseReference myRef5 = database.getReference("win"); // push adds new Arrylist with unique value
        myRef5.removeValue();

    }

}
