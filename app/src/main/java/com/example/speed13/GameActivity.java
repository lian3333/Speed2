package com.example.speed13;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameActivity extends AppCompatActivity {

    public Board board;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_game);
        //Intent intent=getIntent();
        board = new Board(this);
        setContentView(board);
    }

    public void newValFromFbToOpen1(FbCard fbCard) {
        board.newValFromFbToOpen1(fbCard);
    }
}