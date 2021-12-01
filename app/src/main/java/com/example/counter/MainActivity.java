package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean clickedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtCounter = (TextView) findViewById(R.id.textViewCounter);
        Button btnIncrement = (Button) findViewById(R.id.buttonIncrement);
        ImageButton btnReset = (ImageButton) findViewById(R.id.imageButtonReset);


        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer counter = Integer.parseInt(txtCounter.getText().toString());
                counter++;
                txtCounter.setText(counter.toString());
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickedOnce){
                    txtCounter.setText("0");
                }else{
                    Toast.makeText(MainActivity.this,"press again to reset the counter",Toast.LENGTH_SHORT).show();
                    clickedOnce = true;
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clickedOnce = false;
                        }
                    },2000);
                }

            }
        });

    }
}