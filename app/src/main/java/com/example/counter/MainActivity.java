package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean clickedOnce = false;
    private static final String SHAREDPREFRENCES = "Counter_SP";
    private static final String LAST_COUNT = "LAST_COUNT";
    private static final String HIGHEST_COUNT = "HIGHEST_COUNT";

    private TextView txtCounter;
    private TextView txtHighestCount;
    private Button btnIncrement ;
    private ImageButton btnReset;

    private Integer counter;
    private int highestCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCounter = (TextView) findViewById(R.id.txt_count);
        txtHighestCount = (TextView) findViewById(R.id.txt_highestCount);
        btnIncrement = (Button) findViewById(R.id.btn_increment);
        btnReset = (ImageButton) findViewById(R.id.ibtn_reset);

        loadData();


        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                if(counter>highestCount){
                    highestCount = counter;
                    txtHighestCount.setText("Highest Count: "+ highestCount);
                }
                txtCounter.setText(counter.toString());
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickedOnce){
                    counter =0;
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

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("hello", "onPause: ");
        SharedPreferences sharedPreferences = getSharedPreferences(SHAREDPREFRENCES,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(LAST_COUNT, counter);
        editor.putInt(HIGHEST_COUNT, highestCount);
        editor.apply();

    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHAREDPREFRENCES,MODE_PRIVATE);
        counter = sharedPreferences.getInt(LAST_COUNT, 0);
        highestCount = sharedPreferences.getInt(HIGHEST_COUNT,0);
        txtCounter.setText(counter.toString());
        txtHighestCount.setText("Highest Count: "+ highestCount);
    }
}