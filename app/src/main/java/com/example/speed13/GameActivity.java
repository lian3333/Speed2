package com.example.speed13;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    //public static int player;
    public Board board;
    //private FB instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_game);
        //Intent intent=getIntent();


        //player=getIntent().getIntExtra("player",0);
        //instance= FB.getInstance(this);

        board = new Board(this);

        setContentView(board);
    }

    public void newValFromFbToOpen1(FbCard fbCard) {
        Card card = new Card(this,fbCard.getColor(), fbCard.getValue());
        board.newValFromFbToOpen1(card);
    }
    public void newValFromFbToOpen2(FbCard fbCard) {
        Card card = new Card(this,fbCard.getColor(), fbCard.getValue());
        board.newValFromFbToOpen2(card);
    }


    public void newValFromFbToDeck1(ArrayList<FbCard> fbDeck)
    {
        ArrayList<Card> deck = new ArrayList<>();
        for (int i = 0; i < deck.size(); i++) {
            Card card = new Card(this, fbDeck.get(i).getColor(), fbDeck.get(i).getValue());
        }
        Toast.makeText(this, "new Deck1", Toast.LENGTH_SHORT).show();
    }
    public void newValFromFbToDeck2(ArrayList<FbCard> fbDeck)
    {
        ArrayList<Card> deck = new ArrayList<>();
        for (int i = 0; i < deck.size(); i++) {
            Card card = new Card(this, fbDeck.get(i).getColor(), fbDeck.get(i).getValue());
        }
        Toast.makeText(this, "new Deck2", Toast.LENGTH_SHORT).show();
    }


    public void newValFromFbToHand1(ArrayList<FbCard> fbHand) {
        ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            Card card = new Card(this, fbHand.get(i).getColor(), fbHand.get(i).getValue());
        }
        Toast.makeText(this, "new Hand1", Toast.LENGTH_SHORT).show();

    }

    public void newValFromFbToHand2(ArrayList<FbCard> fbHand) {
        ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            Card card = new Card(this, fbHand.get(i).getColor(), fbHand.get(i).getValue());
        }
        Toast.makeText(this, "new Hand2", Toast.LENGTH_SHORT).show();

    }
}