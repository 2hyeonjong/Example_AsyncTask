package com.example.example_asynctask;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ProgressBar pgsBar;
    private int i = 0;
    private TextView txtView;
    private Handler hdlr = new Handler();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pgsBar = (ProgressBar) findViewById(R.id.pBar);
        txtView = (TextView) findViewById(R.id.tView);
        Button btn = (Button)findViewById(R.id.btnShow);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                i = pgsBar.getProgress();
                if(i ==100){
                    i=0;
                }
                new Thread(new Runnable() {
                    public void run() {
                        while (i < 100) {
                            i += 1;
                            hdlr.post(new Runnable() {
                                @SuppressLint("SetTextI18n")
                                public void run() {
                                    pgsBar.setProgress(i);
                                    txtView.setText(i + "/" + pgsBar.getMax());
                                }
                            });
                            try {
                                // Sleep for 100 milliseconds to show the progress slowly.
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }
}
