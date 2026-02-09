package com.example.speed13;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private ActivityResultLauncher<Intent> activityResultLauncher; //
    private String backgroundColor = ""; // שמירת צבע הרקע


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            String str = data.getStringExtra("color");
                            Toast.makeText(MainActivity.this, ""+str, Toast.LENGTH_SHORT).show();
                            setBackgroundColor(str);

                        }
                    }
                }
        );

    }
    public void setBackgroundColor(String strColor) {
        // פונקציה זו מקבלת את צבע הרקע
        // שומרת אותו בתכונה backgroundColor
        // ומשנה את צבע הרקע של המסך

        // if Firebase empty srtColor == null
        if (strColor == null)
            strColor = "";

        backgroundColor = strColor;
        LinearLayout linearLayout = findViewById(R.id.main);
        switch (strColor) {
            case "Red":
                linearLayout.setBackgroundColor(Color.RED); // 0x = hexadecimal
                break;

            case "Blue":
                linearLayout.setBackgroundColor(Color.BLUE);
                break;

            case "Pink":
                linearLayout.setBackgroundColor(Color.argb(255, 255, 192, 203)); //Alfa Red Green Blue
                break;
            default:
                linearLayout.setBackgroundColor(Color.YELLOW);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        if (view==btn1){
            Intent intent=new Intent(this,GameActivity.class);
            startActivity(intent);
        }
        if (view==btn2){
            Intent intentinst=new Intent(this,Instructions.class);
            startActivity(intentinst);
        }
        if (view==btn3){
            Intent intentset=new Intent(this,Setting.class);
            //startActivity(intentset);
            activityResultLauncher.launch(intentset);
        }
    }
}