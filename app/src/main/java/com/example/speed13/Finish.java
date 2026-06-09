package com.example.speed13;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Finish extends AppCompatActivity implements View.OnClickListener {
    private TextView tvf;
    private Button btnf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_finish);


            btnf=findViewById(R.id.btnf);
            tvf=findViewById(R.id.tvf);

            btnf.setOnClickListener(this);
        int x=getIntent().getIntExtra("x",1);
            if(x==1)
            {tvf.setText("Player 1 won!");}
        if(x==2)
        {tvf.setText("Player 2 won!");}
        if(x==3)
        {tvf.setText("You both won!");}

        }

    @Override
    public void onClick(View view) {
        if (view==btnf){
            Intent intentmain=new Intent(this,MainActivity.class);
            startActivity(intentmain);
        }

    }
}
