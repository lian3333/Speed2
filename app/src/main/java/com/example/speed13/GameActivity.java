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
    private int player;

    //private FB instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_game);
        Intent intent=getIntent();


        player=getIntent().getIntExtra("player",1);
        //instance= FB.getInstance(this);

        board = new Board(this,player);

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
        //ArrayList<Card> deck = new ArrayList<>();
        for (int i = 0; i < fbDeck.size(); i++) {
            Card card = new Card(this, fbDeck.get(i).getColor(), fbDeck.get(i).getValue());
            board.player1.getDeck().add(card);
        }
        //board.setDeck1(deck);
        Toast.makeText(this, "new Deck1", Toast.LENGTH_SHORT).show();
    }
    public void newValFromFbToDeck2(ArrayList<FbCard> fbDeck)
    {
        ArrayList<Card> deck = new ArrayList<>();
        for (int i = 0; i < fbDeck.size(); i++) {
            Card card = new Card(this, fbDeck.get(i).getColor(), fbDeck.get(i).getValue());
            board.player2.getDeck().add(card);
        }
        //board.setDeck2(deck);
        Toast.makeText(this, "new Deck2", Toast.LENGTH_SHORT).show();
    }


    public void newValFromFbToHand1(ArrayList<FbCard> fbHand) {
        board.newValFromFbToHand1(fbHand);
/*
        ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < fbHand.size(); i++) {
            Card card = new Card(this, fbHand.get(i).getColor(), fbHand.get(i).getValue());
            board.player1.getHand().add(card);

        }
        //board.setHand1(hand);
        //board.setHand1(hand);
        Toast.makeText(this, "new Hand1", Toast.LENGTH_SHORT).show();
*/

    }

    public void newValFromFbToHand2(ArrayList<FbCard> fbHand) {
       /*ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < fbHand.size(); i++) {
            Card card = new Card(this, fbHand.get(i).getColor(), fbHand.get(i).getValue());
            board.player2.getHand().add(card);
        }
        //board.setHand2(hand);
        Toast.makeText(this, "new Hand2", Toast.LENGTH_SHORT).show();*/
        board.newValFromFbToHand2(fbHand);

    }
}