package com.example.speed13;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Setting extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] arrcolor = { "Choose a color", "Red", "Blue", "Pink", "Yellow"};
    Spinner spinner;
    boolean firstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,arrcolor);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa); //Setting the ArrayAdapter data on the Spinner
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(!firstTime)
        {
            Intent intent = new Intent();
            intent.putExtra("color",arrcolor[position]);
            setResult(RESULT_OK, intent);
            finish(); // return to MainActivity
        }
        firstTime = false;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "onNothingSelected", Toast.LENGTH_SHORT).show();
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting);
    }*/

}